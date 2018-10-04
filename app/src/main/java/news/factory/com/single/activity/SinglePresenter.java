package news.factory.com.single.activity;

import android.util.Log;

import news.factory.com.model.ArticleInteractor;
import news.factory.com.model.ArticleListener;
import news.factory.com.base.Constants;
import news.factory.com.model.data_model.News;

public class SinglePresenter implements SingleContract.Presenter, ArticleListener {

    private static final String TAG = SinglePresenter.class.getSimpleName();

    private ArticleInteractor articleInteractor;
    private SingleContract.View singleView;
    private String articleID;


    public SinglePresenter(SingleContract.View view) {
        this.singleView = view;
        articleInteractor = new ArticleInteractor();
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
    public void onSuccess(News news) {
        singleView.displayArticle(articleID,Integer.parseInt(news.getPages_no()));
    }

    @Override
    public void onFailure() {
        Log.e(TAG, "Failed getting data");
    }

    public void cancelCall(){
        articleInteractor.cancelCall();
    }

}
