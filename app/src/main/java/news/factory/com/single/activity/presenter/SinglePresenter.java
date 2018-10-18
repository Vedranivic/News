package news.factory.com.single.activity.presenter;

import android.util.Log;

import javax.inject.Inject;

import news.factory.com.base.ResultWrapper;
import news.factory.com.model.interactor.ArticleInteractor;
import news.factory.com.model.interactor.InteractorListener;
import news.factory.com.base.Constants;
import news.factory.com.model.data_model.News;
import news.factory.com.single.activity.SingleContract;

public class SinglePresenter implements SingleContract.Presenter, InteractorListener {

    private static final String TAG = SinglePresenter.class.getSimpleName();

    public ArticleInteractor articleInteractor;
    public SingleContract.View singleView;
    private String articleID;

    @Inject
    public SinglePresenter(SingleContract.View view, ArticleInteractor articleInteractor) {
        this.singleView = view;
        this.articleInteractor = articleInteractor;
    }

    @Override
    public void initialize(String articleID) {
        this.articleID = articleID;
    }

    @Override
    public void getArticle() {
        articleInteractor.makeCall(articleID, Constants.FIRST_PAGE_VALUE, this);
    }

    @Override
    public void onSuccess(ResultWrapper result) {
        switch (result.getType()) {
            case Constants.NEWS_TYPE:
                News news = (News) result.getResult();
                singleView.displayArticle(articleID, Integer.parseInt(news.getPages_no()));
        }
    }

    @Override
    public void onFailure() {
        Log.e(TAG, "Failed getting data");
    }

    @Override
    public void dispose() {
        articleInteractor.dispose();
    }
}
