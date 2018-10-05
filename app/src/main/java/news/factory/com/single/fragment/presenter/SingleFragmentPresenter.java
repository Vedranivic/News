package news.factory.com.single.fragment.presenter;


import java.util.ArrayList;
import java.util.List;

import news.factory.com.base.Constants;
import news.factory.com.model.ArticleInteractor;
import news.factory.com.model.ArticleInteractorImpl;
import news.factory.com.model.ArticleListener;
import news.factory.com.base.BaseItem;
import news.factory.com.model.data_model.Content;
import news.factory.com.model.data_model.News;
import news.factory.com.single.fragment.SingleFragmentContract;


public class SingleFragmentPresenter implements SingleFragmentContract.Presenter, ArticleListener {

    private static final String textType = "text";
    private static final String imageType = "image";

    private ArticleInteractor articleInteractor;
    private SingleFragmentContract.View singleFragmentView;
    private String articleID;
    private String page;


    public SingleFragmentPresenter(SingleFragmentContract.View singleFragmentView) {
        this.singleFragmentView = singleFragmentView;
        articleInteractor = new ArticleInteractorImpl();
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
            items.add(new News(news.getFeatured_image(),
                    news.getFeatured_image_caption(),
                    news.getFeatured_image_source(),
                    news.getCategory(),
                    Constants.FEATURE_TYPE)
            );                                  //feature image
        }

        items.add(new News(news.getUppertitle(),Constants.UPPERTITLE_TYPE)); //uppertitle

        items.add(new News(
                news.getPublished_at_humans(),                    //published
                news.getAuthor(),
                news.getShares(),
                Constants.PUBLISHED
                )
        );

        news.setViewType(Constants.TITLE_TYPE);
        items.add(news);                        //title
        for(Content c : news.getContent()) {
            if(c.getType().equals(textType)) {
                items.add(c);                   //text
            }
            if(c.getType().equals(imageType)){
                items.add(c.getImage());        //image
            }
        }

        items.add(new News(                     //indicator
                news.getPages_no(),
                news.getContent(),
                Constants.INDICATOR)
        );

        singleFragmentView.displayArticleItems(items);
    }

    @Override
    public void onFailure() {

    }

    public void cancelCall(){
        articleInteractor.cancelCall();
    }
}
