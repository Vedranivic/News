package news.factory.com.single.activity.di;


import dagger.Module;
import dagger.Provides;
import news.factory.com.base.dependency_injection.PerActivityScope;
import news.factory.com.model.interactor.ArticleInteractor;
import news.factory.com.model.interactor.ArticleInteractorImpl;
import news.factory.com.single.activity.SingleContract;
import news.factory.com.single.activity.presenter.SinglePresenter;
import news.factory.com.single.activity.view.SingleActivity;
import news.factory.com.adapter.SinglePagerAdapter;

@Module
public class SingleActivityModule {

    @Provides
    @PerActivityScope
    public SingleContract.View provideSingleView(SingleActivity singleActivity){
        return singleActivity;
    }

    @Provides
    @PerActivityScope
    public SingleContract.Presenter provideSinglePresenter(SinglePresenter presenter){
        return presenter;
    }

    @Provides
    @PerActivityScope
    public ArticleInteractor provideArticleInteractor(ArticleInteractorImpl articleInteractor){
        return articleInteractor;
    }

    @Provides
    @PerActivityScope
    public SinglePagerAdapter provideSinglePagerAdapter(SingleActivity singleActivity){
        return new SinglePagerAdapter(singleActivity.getSupportFragmentManager());
    }
}
