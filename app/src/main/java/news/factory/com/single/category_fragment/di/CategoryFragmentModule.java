package news.factory.com.single.category_fragment.di;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import news.factory.com.base.dependency_injection.PerFragmentScope;
import news.factory.com.base.networking.NewsAPI;
import news.factory.com.model.interactor.CategoryInteractor;
import news.factory.com.model.interactor.CategoryInteractorImpl;
import news.factory.com.single.adapter.RecyclerAdapter;
import news.factory.com.single.category_fragment.CategoryFragmentContract;
import news.factory.com.single.category_fragment.presenter.CategoryFragmentPresenter;
import news.factory.com.single.category_fragment.view.CategoryFragment;


@Module
public class CategoryFragmentModule {

    @Provides
    @PerFragmentScope
    public CategoryFragmentContract.Presenter provideCategoryFragmentPresenter(CategoryFragmentPresenter categoryFragmentPresenter){
        return categoryFragmentPresenter;
    }

    @Provides
    @PerFragmentScope
    public CategoryFragmentContract.View provideCategoryFragmentView(CategoryFragment categoryFragment){
        return categoryFragment;
    }

    @Provides
    @PerFragmentScope
    public CategoryInteractor provideCategoryInteractor(NewsAPI newsAPI){
        return new CategoryInteractorImpl(newsAPI);
    }

    @Provides
    @PerFragmentScope
    public RecyclerAdapter provideRecyclerAdapter(@Named("CategoryFragmentContext")Context context){
        return new RecyclerAdapter(context);
    }

    @Provides
    @PerFragmentScope
    @Named("CategoryFragmentContext")
    public Context provideContext(CategoryFragment categoryFragment){
        return categoryFragment.getContext();
    }

}
