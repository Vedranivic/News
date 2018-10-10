package news.factory.com.single.fragment.presenter;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import news.factory.com.R;
import news.factory.com.base.Constants;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.base.ResultWrapper;
import news.factory.com.model.interactor.ArticleInteractor;
import news.factory.com.model.interactor.ArticleInteractorImpl;
import news.factory.com.model.interactor.InteractorListener;
import news.factory.com.model.data_model.Content;
import news.factory.com.model.data_model.News;
import news.factory.com.single.fragment.SingleFragmentContract;


public class SingleFragmentPresenter implements SingleFragmentContract.Presenter, InteractorListener {

    private static final String textType = "text";
    private static final String imageType = "image";
    private static final String TAG = SingleFragmentPresenter.class.getSimpleName();

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
    public void onSuccess(ResultWrapper result) {
        switch (result.getType()) {
            case Constants.NEWS_TYPE:
                News news = (News) result.getResult();
                List<RecyclerItemsWrapper> items = new ArrayList<>();
                if (!news.getNo_featured_image()) {
                    //feature image
                    items.add(new RecyclerItemsWrapper(news, R.layout.item_feature_image));
                }
                //uppertitle
                items.add(new RecyclerItemsWrapper(news, R.layout.item_uppertitle));
                //published
                items.add(new RecyclerItemsWrapper(news, R.layout.item_published));
                //title
                items.add(new RecyclerItemsWrapper(news,R.layout.item_title));
                for (Content c : news.getContent()) {
                    if (c.getType().equals(textType)) {
                        //text
                        items.add(new RecyclerItemsWrapper(c,R.layout.item_text));
                    }
                    if (c.getType().equals(imageType)) {
                        //image
                        items.add(new RecyclerItemsWrapper(c.getImage(), R.layout.item_image));
                    }
                }

                items.add(new RecyclerItemsWrapper(news,R.layout.item_indicator));

                singleFragmentView.displayArticleItems(items);
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
