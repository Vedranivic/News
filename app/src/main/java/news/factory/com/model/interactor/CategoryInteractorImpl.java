package news.factory.com.model.interactor;


import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import news.factory.com.base.BaseInteractorImpl;
import news.factory.com.base.Constants;
import news.factory.com.base.ResultWrapper;
import news.factory.com.model.data_model.Category;

public class CategoryInteractorImpl extends BaseInteractorImpl implements CategoryInteractor {

    @Inject
    public CategoryInteractorImpl() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public void makeCall(String category, String id, String page, InteractorListener listener) {
        getDisposable().add(newsAPI.getByCategory(category,id,page)
                .map((Function<Category, Object>) category1 -> {
                    category1.setCompoundID();
                    return new ResultWrapper(category1, Constants.CATEGORIES_TYPE);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver(listener))
        );
    }

    @SuppressWarnings("unchecked")
    @Override
    public void makeCallFromDatabase(String category, String id, String page, InteractorListener listener) {
        Category categoryList = realm.where(Category.class)
                .equalTo("compoundID",id+category)
                .equalTo("page",page)
                .findFirst();
        if (categoryList != null) {
            getDisposable().add(Single.just(categoryList)
                    .map(categoryList1 -> new ResultWrapper(categoryList1, Constants.CATEGORIES_TYPE))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(getObserver(listener))
            );
        }
        else {
            makeCall(category, id, page, listener);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void writeToDatabase(ResultWrapper result) {
        switch (result.getType()) {
            case Constants.CATEGORIES_TYPE:
                Category category = (Category)result.getResult();
                realm.executeTransactionAsync(
                        realm -> realm.insertOrUpdate(category)
                );
                Log.d("fatal","Written to database - Category Sort" + ((Category)result.getResult()).getName());
                break;
        }
    }
}
