package news.factory.com.single.fragment_single.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import news.factory.com.R;
import news.factory.com.base.Constants;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.base.ResultWrapper;
import news.factory.com.model.interactor.ArticleInteractor;
import news.factory.com.model.interactor.InteractorListener;
import news.factory.com.model.data_model.Content;
import news.factory.com.model.data_model.News;
import news.factory.com.common.adapter.RecyclerAdapter;
import news.factory.com.single.fragment_single.SingleFragmentContract;
import news.factory.com.common.view_holder.category.CategoryDataClass;
import news.factory.com.common.view_holder.feature.FeatureDataClass;
import news.factory.com.common.view_holder.image.ImageDataClass;
import news.factory.com.common.view_holder.indicator.IndicatorDataClass;
import news.factory.com.common.view_holder.published.PublishedDataClass;
import news.factory.com.common.view_holder.text.TextDataClass;
import news.factory.com.common.view_holder.title.TitleDataClass;
import news.factory.com.common.view_holder.uppertitle.UppertitleDataClass;

public class SingleFragmentPresenter implements SingleFragmentContract.Presenter, InteractorListener {

    private static final String textType = "text";
    private static final String imageType = "image";
    private static final String TAG = SingleFragmentPresenter.class.getSimpleName();

    private ArticleInteractor articleInteractor;
    private SingleFragmentContract.View singleFragmentView;
    private String articleID;
    private String page;

    @Inject
    public Lazy<RecyclerAdapter> adapter;

    @Inject
    public SingleFragmentPresenter(SingleFragmentContract.View singleFragmentView, ArticleInteractor articleInteractor) {
        this.singleFragmentView = singleFragmentView;
        this.articleInteractor = articleInteractor;
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
                getItemList((News)result.getResult());
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


    private void getItemList(News news) {
        List<RecyclerItemsWrapper> items = new ArrayList<>();
        if (!news.getNo_featured_image()) {
            //feature image
            items.add(new RecyclerItemsWrapper(new FeatureDataClass(
                    news.getCategory(),
                    news.getFeatured_image_source(),
                    news.getFeatured_image_caption(),
                    news.getFeatured_image().getOriginal(),
                    news.getCategory_color()
                ), R.layout.item_feature_image));
        }
        else{
            items.add(new RecyclerItemsWrapper(new FeatureDataClass(
                    news.getCategory(),
                    news.getFeatured_image_source(),
                    news.getFeatured_image_caption(),
                    "",
                    news.getCategory_color()
            ), R.layout.item_feature_image));
        }
        //uppertitle
        items.add(new RecyclerItemsWrapper(new UppertitleDataClass(
                news.getUppertitle()
            ), R.layout.item_uppertitle));
        //published
        items.add(new RecyclerItemsWrapper(new PublishedDataClass(
                news.getPublished_at_humans(),
                news.getAuthor(),
                news.getShares()
            ), R.layout.item_published));
        //title
        items.add(new RecyclerItemsWrapper(new TitleDataClass(
                news.getTitle()
            ),R.layout.item_title));
        for (Content c : news.getContent()) {
            if (c.getType().equals(textType)) {
                //text
                items.add(new RecyclerItemsWrapper(new TextDataClass(
                        c.getData()
                    ),R.layout.item_text));
            }
            if (c.getType().equals(imageType)) {
                //image
                items.add(new RecyclerItemsWrapper(new ImageDataClass(
                        c.getImage().getOriginal()
                    ), R.layout.item_image));
            }
        }
        items.add(new RecyclerItemsWrapper(new IndicatorDataClass(
                news.getContent().get(0).getPage(),
                news.getPages_no()
            ),R.layout.item_indicator));

        items.add(new RecyclerItemsWrapper(new CategoryDataClass(
                "0",
                "1"
            ), R.layout.item_category));

        //singleFragmentView.displayArticleItems(items);
        adapter.get().setItems(items);
    }
}
