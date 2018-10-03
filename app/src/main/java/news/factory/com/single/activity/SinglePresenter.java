package news.factory.com.single.activity;

import android.content.Context;

import news.factory.com.model.ArticleInteractor;

public class SinglePresenter implements SinglePresenterInterface{
    private SingleActivityInterface singleActivityInterface;

    public SinglePresenter(SingleActivityInterface singleActivityInterface) {
        this.singleActivityInterface = singleActivityInterface;
    }

    @Override
    public void getArticle() {
        ArticleInteractor articleInteractor = new ArticleInteractor(this);
        articleInteractor.makeCall(singleActivityInterface.getArticleID());
    }

    @Override
    public void setArticle(int pagesNumber) {
        singleActivityInterface.displayArticle(singleActivityInterface.getArticleID(), pagesNumber);
    }

    @Override
    public Context getContext() {
        return singleActivityInterface.getContext();
    }

}
