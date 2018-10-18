package news.factory.com.single.fragment.di;

import dagger.Module;
import dagger.Provides;
import news.factory.com.base.dependency_injection.PerFragmentScope;
import news.factory.com.model.interactor.ArticleInteractor;
import news.factory.com.model.interactor.ArticleInteractorImpl;
import news.factory.com.single.adapter.CategoryPagerAdapter;
import news.factory.com.single.adapter.RecyclerAdapter;
import news.factory.com.single.fragment.SingleFragmentContract;
import news.factory.com.single.fragment.presenter.SingleFragmentPresenter;
import news.factory.com.single.fragment.view.SingleFragment;

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
    public RecyclerAdapter provideRecyclerAdapter(SingleFragment singleFragment){
        return new RecyclerAdapter(singleFragment.getContext());
    }

    @Provides
    @PerFragmentScope
    public CategoryPagerAdapter provideCategoryPagerAdapter(SingleFragment singleFragment){
        return new CategoryPagerAdapter(singleFragment.getChildFragmentManager(), singleFragment.getContext());
    }

}
