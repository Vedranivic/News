package news.factory.com.single.fragment;


import java.util.ArrayList;
import java.util.List;

import news.factory.com.model.ArticleInteractor;
import news.factory.com.model.ArticleListener;
import news.factory.com.base.BaseItem;
import news.factory.com.model.data_model.Content;
import news.factory.com.model.data_model.News;


public class SingleFragmentPresenter implements SingleFragmentContract.Presenter, ArticleListener {

    private ArticleInteractor articleInteractor;
    private SingleFragmentContract.View singleFragmentView;
    private String articleID;
    private String page;


    public SingleFragmentPresenter(SingleFragmentContract.View singleFragmentView) {
        this.singleFragmentView = singleFragmentView;
        articleInteractor = new ArticleInteractor();
    }

    @Override
    public void initialize(String articleID, String page) {
        this.articleID = articleID;
        this.page = page;
    }

    @Override
    public void getArticleItems() {
        articleInteractor.makeCall(articleID, page, this);
    }

    @Override
    public void onSuccess(News news) {
        List<BaseItem> items = new ArrayList<>();
        if(!news.getNo_featured_image()){
            items.add(news.getFeatured_image());//feature image
        }
        items.add(news);                        //title
        for(Content c : news.getContent()) {
            if(c.getType().equals("text")) {
                items.add(c);                   //text
            }
            if(c.getType().equals("image")){
                items.add(c.getImage());        //image
            }
        }

        singleFragmentView.displayArticleItems(items);
    }

    @Override
    public void onFailure() {

    }

    public void cancelCall(){
        articleInteractor.cancelCall();
    }
}
