package news.factory.com.model.interactor;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import news.factory.com.App;
import news.factory.com.base.BaseInteractorImpl;
import news.factory.com.base.Constants;
import news.factory.com.base.ResultWrapper;
import news.factory.com.model.data_model.Category;

public class CategoryInteractorImpl extends BaseInteractorImpl implements CategoryInteractor {

    @SuppressWarnings("unchecked")
    @Override
    public void makeCall(String category, String id, String page, InteractorListener listener) {
        getDisposable().add(App.getComponent().getNewsAPI().getByCategory(category,id,page)
                .map((Function<Category, Object>) category1 -> {
                    if(category1.getArticles().size()>=4) {
                        category1.setArticles(category1.getArticles().subList(0, 4));
                    }
                    return new ResultWrapper(category1, Constants.CATEGORIES_TYPE);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver(listener))
        );
    }
}
