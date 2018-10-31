package news.factory.com.home.fragment_home.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import news.factory.com.R;
import news.factory.com.base.adapter.RecyclerAdapter;
import news.factory.com.base.Constants;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.base.ResultWrapper;
import news.factory.com.base.view_holder.category_item.CategoryItemDataClass;
import news.factory.com.base.view_holder.category_title.CategoryTitleDataClass;
import news.factory.com.home.fragment_home.HomeFragmentContract;
import news.factory.com.model.data_model.Category;
import news.factory.com.model.data_model.HomeItemsList;
import news.factory.com.model.data_model.News;
import news.factory.com.model.interactor.HomeInteractor;
import news.factory.com.model.interactor.InteractorListener;
import news.factory.com.base.view_holder.top_block.TopBlockDataClass;

public class HomeFragmentPresenter implements HomeFragmentContract.Presenter, InteractorListener {

    private static final String TAG = HomeFragmentPresenter.class.getSimpleName();

    private HomeInteractor homeInteractor;
    private HomeFragmentContract.View homeFragmentView;
    private Boolean isNetworkConnected;

    @Inject
    public Lazy<RecyclerAdapter> adapter;

    @Inject
    public HomeFragmentPresenter(HomeFragmentContract.View homeFragmentView, HomeInteractor homeInteractor) {
        this.homeInteractor = homeInteractor;
        this.homeFragmentView = homeFragmentView;
    }

    @Override
    public void initialize(Boolean isNetworkConnected) {
        this.isNetworkConnected = isNetworkConnected;
    }

    @Override
    public void getHomeItems() {
        Log.d(TAG, "Connected to network: " + String.valueOf(isNetworkConnected.booleanValue()));
        if(isNetworkConnected) {
            homeInteractor.getHomeItems(this);
        }
        else {
            homeInteractor.getHomeItemsFromDataBase(this);
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    public void onSuccess(ResultWrapper result) {
        switch (result.getType()) {
            case Constants.HOME_ITEMS_TYPE:
                getItemList((List<Category>)result.getResult());
                homeInteractor.writeToDatabase(result);
                break;
            case Constants.HOME_ITEMS_DB_TYPE:
                getItemList(((HomeItemsList)result.getResult()).getItemList());
                Log.d(TAG, "HomeItems Loaded from database");
                break;
        }
    }

    private void getItemList(List<Category> result) {
        List<RecyclerItemsWrapper> items = new ArrayList<>();

        items.add(new RecyclerItemsWrapper(new TopBlockDataClass(
                result.get(0).getArticles()
            ), R.layout.item_top_block
        ));
        for(Category c : result.subList(1,result.size())) {
            items.add(new RecyclerItemsWrapper(new CategoryTitleDataClass(
                    c.getName(),
                    c.getColor()
                ), R.layout.item_category_title
            ));
            for(News article : c.getArticles()){
                items.add(new RecyclerItemsWrapper(new CategoryItemDataClass(
                        article.getFeatured_image().getOriginal(),
                        article.getCategory(),
                        article.getTitle(),
                        article.getSubtitle(),
                        article.getShares(),
                        article.getPublished_at_humans(),
                        c.getColor(),
                        article.getId()
                    ), R.layout.item_news
                ));
            }
            items.add(new RecyclerItemsWrapper(c.getName()
                    , R.layout.item_button_block
            ));
        }
        homeFragmentView.displayHomeItems(items);
    }

    @Override
    public void onFailure() {
        Log.e(TAG, "Failed getting data");
    }
}
