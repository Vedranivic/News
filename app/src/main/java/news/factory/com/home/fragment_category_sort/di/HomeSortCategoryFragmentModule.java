package news.factory.com.home.fragment_category_sort.di;

import dagger.Module;
import dagger.Provides;
import news.factory.com.base.adapter.RecyclerAdapter;
import news.factory.com.base.adapter.RecyclerAdapterImpl;
import news.factory.com.base.ObjectWrapper;
import news.factory.com.base.dependency_injection.PerFragmentScope;
import news.factory.com.home.fragment_category_sort.HomeSortCategoryFragmentContract;
import news.factory.com.home.fragment_category_sort.presenter.HomeSortCategoryFragmentPresenter;
import news.factory.com.home.fragment_category_sort.view.HomeSortCategoryFragment;
import news.factory.com.model.interactor.CategoryInteractor;
import news.factory.com.model.interactor.CategoryInteractorImpl;

@Module
public class HomeSortCategoryFragmentModule {

    @Provides
    @PerFragmentScope
    public HomeSortCategoryFragmentContract.View provideCategoryFragmentView(HomeSortCategoryFragment homeSortCategoryFragment, CategoryInteractor categoryInteractor){
        homeSortCategoryFragment.getLifecycle().addObserver(categoryInteractor);
        return homeSortCategoryFragment;
    }

    @Provides
    @PerFragmentScope
    public HomeSortCategoryFragmentContract.Presenter provideCategoryFragmentPresenter(HomeSortCategoryFragmentPresenter homeSortCategoryFragmentPresenter){
        return homeSortCategoryFragmentPresenter;
    }

    @Provides
    @PerFragmentScope
    public CategoryInteractor provideCategoryInteractor(CategoryInteractorImpl categoryInteractor){
        return categoryInteractor;
    }

    @Provides
    @PerFragmentScope
    public RecyclerAdapter provideRecyclerAdapter(RecyclerAdapterImpl recyclerAdapter){
        return recyclerAdapter;
    }

    @Provides
    @PerFragmentScope
    public ObjectWrapper provideObjectWrapper(HomeSortCategoryFragmentContract.Presenter presenter, HomeSortCategoryFragmentContract.View view) {
        return new ObjectWrapper(presenter, view);
    }

}
