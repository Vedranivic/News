package news.factory.com.home.fragment_category_sort.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import news.factory.com.R;
import news.factory.com.base.adapter.RecyclerAdapter;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.base.ResultWrapper;
import news.factory.com.home.fragment_category_sort.HomeSortCategoryFragmentContract;
import news.factory.com.home.fragment_category_sort.view.HomeSortCategoryFragment;
import news.factory.com.model.data_model.Category;
import news.factory.com.model.data_model.News;
import news.factory.com.model.interactor.CategoryInteractor;
import news.factory.com.model.interactor.InteractorListener;
import news.factory.com.base.view_holder.category_item.CategoryItemDataClass;

public class HomeSortCategoryFragmentPresenter implements HomeSortCategoryFragmentContract.Presenter, InteractorListener {

    private final String TAG = HomeSortCategoryFragment.class.getSimpleName();

    private CategoryInteractor categoryInteractor;
    private HomeSortCategoryFragmentContract.View view;
    private String id;
    private String page;
    private String color;

    @Inject
    public Lazy<RecyclerAdapter> adapter;

    @Inject
    public HomeSortCategoryFragmentPresenter(HomeSortCategoryFragmentContract.View view, CategoryInteractor categoryInteractor){
        this.view = view;
        this.categoryInteractor = categoryInteractor;
    }

    @Override
    public void initialize(String id, String page, String color) {
        this.id = id;
        this.page = page;
        this.color = color;
    }

    @Override
    public void getItemsByCategory(String category) {
        categoryInteractor.makeCall(category,id,page,this);
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
                    n.getSubtitle(),
                    n.getShares(),
                    n.getPublished_at_humans(),
                    color,
                    n.getId()
            ), R.layout.item_category_sort));
        }
        items.add(new RecyclerItemsWrapper(color, R.layout.item_button));

        adapter.get().setItems(items);
    }

    @Override
    public void onFailure() {
        Log.e(TAG, "Failed getting data");
    }
}
