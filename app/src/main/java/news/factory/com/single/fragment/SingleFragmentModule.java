package news.factory.com.single.fragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import news.factory.com.base.dependency_injection.PerFragmentScope;
import news.factory.com.model.interactor.ArticleInteractor;
import news.factory.com.single.adapter.CategoryPagerAdapter;
import news.factory.com.single.adapter.RecyclerAdapter;
import news.factory.com.single.fragment.presenter.SingleFragmentPresenter;
import news.factory.com.single.fragment.view.SingleFragment;

@Module
public class SingleFragmentModule {

    @Provides
    @PerFragmentScope
    public SingleFragmentContract.Presenter provideSingleFragmentPresenter(SingleFragmentContract.View singleFragmentView, ArticleInteractor articleInteractor){
        return new SingleFragmentPresenter(singleFragmentView, articleInteractor);
    }

    @Provides
    @PerFragmentScope
    public SingleFragmentContract.View provideSingleFragmentView(SingleFragment singleFragment){
        return singleFragment;
    }

    @Provides
    @PerFragmentScope
    public RecyclerAdapter provideRecyclerAdapter(@Named("SingleFragmentContext")Context context ){
        return new RecyclerAdapter(context);
    }

    @Provides
    @PerFragmentScope
    @Named("SingleFragmentContext")
    public Context provideContext(SingleFragment singleFragment){
        return singleFragment.getContext();
    }

    @Provides
    @PerFragmentScope
    public CategoryPagerAdapter provideCategoryPagerAdapter(FragmentManager fragmentManager, @Named("SingleFragmentContext")Context context){
        return new CategoryPagerAdapter(fragmentManager, context);
    }

    @Provides
    @PerFragmentScope
    public FragmentManager provideFragmentManager(SingleFragment singleFragment){
        return singleFragment.getChildFragmentManager();
    }

}
