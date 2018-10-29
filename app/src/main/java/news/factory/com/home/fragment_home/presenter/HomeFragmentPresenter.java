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
import news.factory.com.model.data_model.News;
import news.factory.com.model.interactor.HomeInteractor;
import news.factory.com.model.interactor.InteractorListener;
import news.factory.com.base.view_holder.top_block.TopBlockDataClass;

public class HomeFragmentPresenter implements HomeFragmentContract.Presenter, InteractorListener {

    private static final String TAG = HomeFragmentPresenter.class.getSimpleName();

    private HomeInteractor homeInteractor;
    private HomeFragmentContract.View homeFragmentView;

    @Inject
    public Lazy<RecyclerAdapter> adapter;

    @Inject
    public HomeFragmentPresenter(HomeFragmentContract.View homeFragmentView, HomeInteractor homeInteractor) {
        this.homeInteractor = homeInteractor;
        this.homeFragmentView = homeFragmentView;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void getHomeItems() {
        homeInteractor.getHomeItems(this);
    }


    @SuppressWarnings("unchecked")
    @Override
    public void onSuccess(ResultWrapper result) {
        switch (result.getType()) {
            case Constants.HOME_ITEMS_TYPE:
                getItemList((List<Category>)result.getResult());
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
