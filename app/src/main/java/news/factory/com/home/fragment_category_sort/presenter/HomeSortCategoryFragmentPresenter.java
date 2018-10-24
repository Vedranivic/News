package news.factory.com.home.fragment_category_sort.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import news.factory.com.R;
import news.factory.com.adapter.RecyclerAdapter;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.base.ResultWrapper;
import news.factory.com.home.fragment_category_sort.HomeSortCategoryFragmentContract;
import news.factory.com.home.fragment_category_sort.view.HomeSortCategoryFragment;
import news.factory.com.model.data_model.Category;
import news.factory.com.model.data_model.News;
import news.factory.com.model.interactor.CategoryInteractor;
import news.factory.com.model.interactor.InteractorListener;
import news.factory.com.single.fragment_category.CategoryFragmentContract;
import news.factory.com.view_holder.category_item.CategoryItemDataClass;

public class HomeSortCategoryFragmentPresenter implements HomeSortCategoryFragmentContract.Presenter, InteractorListener {

    private CategoryInteractor categoryInteractor;
    private HomeSortCategoryFragmentContract.View view;
    private String id;
    private String page;

    @Inject
    public Lazy<RecyclerAdapter> adapter;

    @Inject
    public HomeSortCategoryFragmentPresenter(HomeSortCategoryFragmentContract.View view, CategoryInteractor categoryInteractor){
        this.view = view;
        this.categoryInteractor = categoryInteractor;
    }

    @Override
    public void dispose() {
        categoryInteractor.dispose();
    }

    @Override
    public void initialize(String id, String page) {
        this.id = id;
        this.page = page;
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
                    n.getShares(),
                    n.getPublished_at_humans(),
                    n.getCategory_color(),
                    n.getId()
            ), R.layout.item_news));
        }
        adapter.get().setItems(items);
    }


    @Override
    public void onFailure() {

    }
}
