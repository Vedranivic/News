package news.factory.com.single.fragment;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import news.factory.com.model.ArticleInteractor;
import news.factory.com.model.data_model.BaseItem;
import news.factory.com.model.data_model.Content;
import news.factory.com.model.data_model.News;


public class SingleFragmentPresenter implements SingleFragmentPresenterInterface {
    private SingleFragmentInterface singleFragmentInterface;

    public SingleFragmentPresenter(SingleFragmentInterface singleFragmentInterface) {
        this.singleFragmentInterface = singleFragmentInterface;
    }

    @Override
    public void getArticleItems() {
        ArticleInteractor articleInteractor = new ArticleInteractor(this);
        articleInteractor.makeCall(singleFragmentInterface.getArticleID(), singleFragmentInterface.getPage());
    }

    @Override
    public void setArticleItems(News news) {
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

        singleFragmentInterface.displayArticleItems(items);
    }

    @Override
    public Context getContext() {
        return singleFragmentInterface.getContext();
    }


}
