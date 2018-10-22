package news.factory.com.single.category_fragment.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import news.factory.com.R;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.base.ResultWrapper;
import news.factory.com.model.data_model.Category;
import news.factory.com.model.data_model.News;
import news.factory.com.model.interactor.CategoryInteractor;
import news.factory.com.model.interactor.InteractorListener;
import news.factory.com.adapter.RecyclerAdapter;
import news.factory.com.single.category_fragment.CategoryFragmentContract;
import news.factory.com.single.view_holder.category_item.CategoryItemDataClass;

public class CategoryFragmentPresenter implements CategoryFragmentContract.Presenter, InteractorListener {

    private static final String TAG = CategoryFragmentPresenter.class.getSimpleName();

    private CategoryInteractor categoryInteractor;
    private CategoryFragmentContract.View categoryFragmentView;
    private String id;
    private String page;

    @Inject
    public Lazy<RecyclerAdapter> adapter;

    @Inject
    public CategoryFragmentPresenter(CategoryFragmentContract.View categoryFragmentView, CategoryInteractor categoryInteractor){
        this.categoryFragmentView = categoryFragmentView;
        this.categoryInteractor = categoryInteractor;
    }

    @Override
    public void getItemsByCategory(String category) {
        categoryInteractor.makeCall(category, id, page, this);
    }

    @Override
    public void initialize(String id, String page) {
        this.id = id;
        this.page = page;
    }

    @Override
    public void onSuccess(ResultWrapper result) {
        getItemsList((Category) result.getResult());
    }

    private void getItemsList(Category result) {
        List<RecyclerItemsWrapper> items = new ArrayList<>();
        for(News n : result.getArticles()){
            items.add(new RecyclerItemsWrapper(new CategoryItemDataClass(
                    n.getFeatured_image().getOriginal(),
                    n.getCategory(),
                    n.getTitle(),
                    n.getShares(),
                    n.getPublished_at_humans()
            ), R.layout.item_news));
        }
        adapter.get().setItems(items);
    }

    @Override
    public void onFailure() {
        Log.e(TAG, "Failed getting data");
    }

    @Override
    public void dispose() {
        categoryInteractor.dispose();
    }

}
