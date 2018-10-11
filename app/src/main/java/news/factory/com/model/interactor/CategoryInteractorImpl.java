package news.factory.com.model.interactor;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import news.factory.com.base.BaseInteractorImpl;
import news.factory.com.base.Constants;
import news.factory.com.base.ResultWrapper;
import news.factory.com.base.networking.APIServiceGenerator;

public class CategoryInteractorImpl extends BaseInteractorImpl implements CategoryInteractor {

    @SuppressWarnings("unchecked")
    @Override
    public void makeCall(String category, String id, String page, InteractorListener listener) {
        getDisposable().add(APIServiceGenerator.getAPI().getByCategory(category,id,page)
                .map(categories -> new ResultWrapper(categories, Constants.CATEGORIES_TYPE))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver(listener))
        );
    }
}
