package news.factory.com.single.activity;

import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import news.factory.com.base.dependency_injection.PerActivityScope;
import news.factory.com.base.networking.NewsAPI;
import news.factory.com.model.interactor.ArticleInteractor;
import news.factory.com.model.interactor.ArticleInteractorImpl;
import news.factory.com.single.activity.presenter.SinglePresenter;
import news.factory.com.single.activity.view.SingleActivity;
import news.factory.com.single.adapter.SinglePagerAdapter;

@Module
public class SingleActivityModule {

    @Provides
    @PerActivityScope
    public SinglePagerAdapter provideSinglePagerAdapter(FragmentManager fragmentManager){
        return new SinglePagerAdapter(fragmentManager);
    }

    @Provides
    @PerActivityScope
    public SingleContract.Presenter provideSinglePresenter(SinglePresenter presenter){
        return presenter;
    }

    @Provides
    @PerActivityScope
    public SingleContract.View provideSingleView(SingleActivity singleActivity){
        return singleActivity;
    }

    @Provides
    @PerActivityScope
    public FragmentManager provideFragmentManager(SingleActivity singleActivity){
        return singleActivity.getSupportFragmentManager();
    }

    @Provides
    @PerActivityScope
    public ArticleInteractor provideArticleInteractor(NewsAPI newsAPI){
        return new ArticleInteractorImpl(newsAPI);
    }
}
