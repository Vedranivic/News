package news.factory.com.single.fragment_single.di;

import dagger.Module;
import dagger.Provides;
import news.factory.com.base.ObjectWrapper;
import news.factory.com.base.dependency_injection.PerFragmentScope;
import news.factory.com.model.interactor.ArticleInteractor;
import news.factory.com.model.interactor.ArticleInteractorImpl;
import news.factory.com.adapter.CategoryPagerAdapter;
import news.factory.com.adapter.RecyclerAdapter;
import news.factory.com.adapter.RecyclerAdapterImpl;
import news.factory.com.single.fragment_single.SingleFragmentContract;
import news.factory.com.single.fragment_single.presenter.SingleFragmentPresenter;
import news.factory.com.single.fragment_single.view.SingleFragment;

@Module
public class SingleFragmentModule {

    @Provides
    @PerFragmentScope
    public SingleFragmentContract.View provideSingleFragmentView(SingleFragment singleFragment){
        return singleFragment;
    }

    @Provides
    @PerFragmentScope
    public SingleFragmentContract.Presenter provideSingleFragmentPresenter(SingleFragmentPresenter singleFragmentPresenter){
        return singleFragmentPresenter;
    }

    @Provides
    @PerFragmentScope
    public ArticleInteractor provideArticleInteractor(ArticleInteractorImpl articleInteractor){
        return articleInteractor;
    }

    @Provides
    @PerFragmentScope
    public RecyclerAdapter provideRecyclerAdapter(RecyclerAdapterImpl recyclerAdapter){
        return recyclerAdapter;
    }

    @Provides
    @PerFragmentScope
    public ObjectWrapper provideObjectWrapper(SingleFragmentContract.Presenter presenter, SingleFragmentContract.View view, CategoryPagerAdapter categoryPagerAdapter){
        return new ObjectWrapper(presenter, view, categoryPagerAdapter);
    }

    @Provides
    @PerFragmentScope
    public CategoryPagerAdapter provideCategoryPagerAdapter(SingleFragment singleFragment){
        return new CategoryPagerAdapter(singleFragment.getChildFragmentManager(), singleFragment.getContext());
    }

}
