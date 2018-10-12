package news.factory.com.single.category_fragment.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import news.factory.com.R;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.base.ResultWrapper;
import news.factory.com.model.data_model.Category;
import news.factory.com.model.data_model.News;
import news.factory.com.model.interactor.CategoryInteractor;
import news.factory.com.model.interactor.CategoryInteractorImpl;
import news.factory.com.model.interactor.InteractorListener;
import news.factory.com.single.category_fragment.CategoryFragmentContract;
import news.factory.com.single.view_holder.category_item.CategoryItemDataClass;

public class CategoryFragmentPresenter implements CategoryFragmentContract.Presenter, InteractorListener {

    private static final String TAG = CategoryFragmentPresenter.class.getSimpleName();

    private CategoryInteractor categoryInteractor;
    private CategoryFragmentContract.View categoryFragmentView;
    private String id;
    private String page;

    public CategoryFragmentPresenter(CategoryFragmentContract.View categoryFragmentView){
        this.categoryFragmentView = categoryFragmentView;
        this.categoryInteractor = new CategoryInteractorImpl();
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
        Category category = (Category) result.getResult();
        List<RecyclerItemsWrapper> items = new ArrayList<>();
        for(News n : category.getArticles()){
            items.add(new RecyclerItemsWrapper(new CategoryItemDataClass(
                    n.getFeatured_image().getOriginal(),
                    n.getCategory(),
                    n.getTitle(),
                    n.getShares()
            ), R.layout.item_news));
        }
        categoryFragmentView.displayItemsByCategory(items);
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
