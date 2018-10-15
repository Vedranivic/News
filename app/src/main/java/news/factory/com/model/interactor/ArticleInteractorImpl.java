package news.factory.com.model.interactor;

import android.annotation.SuppressLint;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import news.factory.com.base.BaseInteractorImpl;
import news.factory.com.base.Constants;
import news.factory.com.base.ResultWrapper;
import news.factory.com.base.networking.APIServiceGenerator;


public class ArticleInteractorImpl extends BaseInteractorImpl implements ArticleInteractor {

    private static final String TAG = ArticleInteractorImpl.class.getSimpleName();

    public ArticleInteractorImpl() {
    }

    @SuppressLint("CheckResult")
    @SuppressWarnings("unchecked")
    @Override
    public void makeCall(String articleID, String page, final InteractorListener listener) {

        getDisposable().add(APIServiceGenerator.getAPI().getNews(articleID,page)
                .map(news -> new ResultWrapper(news, Constants.NEWS_TYPE))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver(listener))
        );
    }
}
