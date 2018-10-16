package news.factory.com.single.category_fragment;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import news.factory.com.base.dependency_injection.PerFragmentScope;
import news.factory.com.model.interactor.CategoryInteractor;
import news.factory.com.model.interactor.CategoryInteractorImpl;
import news.factory.com.single.adapter.RecyclerAdapter;
import news.factory.com.single.category_fragment.presenter.CategoryFragmentPresenter;


@Module
public class CategoryFragmentModule {

    private final CategoryFragmentContract.View categoryFragmentView;

    private final Context categoryFragmentContext;

    public CategoryFragmentModule(CategoryFragmentContract.View categoryFragmentView, Context categoryFragmentContext) {
        this.categoryFragmentView = categoryFragmentView;
        this.categoryFragmentContext = categoryFragmentContext;
    }

    @Provides
    @PerFragmentScope
    public CategoryFragmentContract.Presenter provideCategoryFragmentPresenter(CategoryFragmentPresenter categoryFragmentPresenter){
        return categoryFragmentPresenter;
    }

    @Provides
    @PerFragmentScope
    public CategoryFragmentContract.View provideCategoryFragmentView(){
        return categoryFragmentView;
    }

    @Provides
    @PerFragmentScope
    public CategoryInteractor provideCategoryInteractor(){
        return new CategoryInteractorImpl();
    }

    @Provides
    @PerFragmentScope
    public RecyclerAdapter provideRecyclerAdapter(Context context){
        return new RecyclerAdapter(context);
    }

    @Provides
    @PerFragmentScope
    public Context provideContext(){
        return categoryFragmentContext;
    }

}
