package news.factory.com.model.interactor;


import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import news.factory.com.base.BaseInteractorImpl;
import news.factory.com.base.Constants;
import news.factory.com.base.ResultWrapper;
import news.factory.com.base.networking.NewsAPI;
import news.factory.com.model.data_model.Category;

public class CategoryInteractorImpl extends BaseInteractorImpl implements CategoryInteractor {

    @Inject
    public CategoryInteractorImpl() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public void makeCall(String category, String id, String page, InteractorListener listener) {
        getDisposable().add(newsAPI.getByCategory(category,id,page)
                .map((Function<Category, Object>) category1 -> new ResultWrapper(category1, Constants.CATEGORIES_TYPE))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver(listener))
        );
    }

    @SuppressWarnings("unchecked")
    @Override
    public void makeCallFromDatabase(String category, String id, String page, InteractorListener listener) {
        Category category1 = realm.where(Category.class)
                .equalTo("name",category)
                .equalTo("category_id",id)
                .equalTo("page",page)
                .findFirst();
        if (category1 != null) {
            getDisposable().add(Single.just(category1)
                    .map(menuList1 -> new ResultWrapper(menuList1, Constants.CATEGORIES_TYPE))
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
                realm.executeTransactionAsync(
                        realm -> realm.insertOrUpdate((Category)result.getResult())
                );
                break;

        }
    }
}
