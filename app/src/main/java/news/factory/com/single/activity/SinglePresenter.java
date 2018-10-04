package news.factory.com.single.activity;

import android.content.Context;

import news.factory.com.model.ArticleInteractor;

public class SinglePresenter implements SinglePresenterInterface{

    private ArticleInteractor articleInteractor;

    private SingleActivityInterface singleActivityInterface;

    public SinglePresenter(SingleActivityInterface singleActivityInterface) {
        this.singleActivityInterface = singleActivityInterface;
    }

    @Override
    public void getArticle() {
        articleInteractor = new ArticleInteractor(this);
        articleInteractor.makeCall(singleActivityInterface.getArticleID());
    }

    @Override
    public void setArticle(int pagesNumber) {
        singleActivityInterface.displayArticle(singleActivityInterface.getArticleID(), pagesNumber);
    }

    public void cancelCall(){
        articleInteractor.cancelCall();
    }

}
