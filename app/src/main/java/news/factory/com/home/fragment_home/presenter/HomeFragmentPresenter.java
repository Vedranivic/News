package news.factory.com.home.fragment_home.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import news.factory.com.R;
import news.factory.com.adapter.RecyclerAdapter;
import news.factory.com.base.Constants;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.base.ResultWrapper;
import news.factory.com.home.fragment_home.HomeFragmentContract;
import news.factory.com.model.data_model.Category;
import news.factory.com.model.data_model.News;
import news.factory.com.model.interactor.HomeInteractor;
import news.factory.com.model.interactor.InteractorListener;
import news.factory.com.view_holder.category_block.CategoryBlockDataClass;
import news.factory.com.view_holder.top_block.TopBlockDataClass;

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

    @Override
    public void dispose() {
        homeInteractor.dispose();
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
            items.add(new RecyclerItemsWrapper(new CategoryBlockDataClass(
                    c.getName(),
                    c.getColor(),
                    c.getArticles()
                ), R.layout.item_category_block
            ));
        }
        homeFragmentView.displayHomeItems(items);
    }

    @Override
    public void onFailure() {
        Log.e(TAG, "Failed getting data");
    }
}
