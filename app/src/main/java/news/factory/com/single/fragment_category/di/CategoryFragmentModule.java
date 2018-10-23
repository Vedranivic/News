package news.factory.com.single.fragment_category.di;

import dagger.Module;
import dagger.Provides;
import news.factory.com.base.ObjectWrapper;
import news.factory.com.base.dependency_injection.PerFragmentScope;
import news.factory.com.model.interactor.CategoryInteractor;
import news.factory.com.model.interactor.CategoryInteractorImpl;
import news.factory.com.adapter.RecyclerAdapter;
import news.factory.com.adapter.RecyclerAdapterImpl;
import news.factory.com.single.fragment_category.CategoryFragmentContract;
import news.factory.com.single.fragment_category.presenter.CategoryFragmentPresenter;
import news.factory.com.single.fragment_category.view.CategoryFragment;


@Module
public class CategoryFragmentModule {

    @Provides
    @PerFragmentScope
    public CategoryFragmentContract.View provideCategoryFragmentView(CategoryFragment categoryFragment){
        return categoryFragment;
    }

    @Provides
    @PerFragmentScope
    public CategoryFragmentContract.Presenter provideCategoryFragmentPresenter(CategoryFragmentPresenter categoryFragmentPresenter){
        return categoryFragmentPresenter;
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
    public ObjectWrapper provideObjectWrapper(CategoryFragmentContract.Presenter presenter, CategoryFragmentContract.View view){
        return new ObjectWrapper(presenter, view);
    }

}
