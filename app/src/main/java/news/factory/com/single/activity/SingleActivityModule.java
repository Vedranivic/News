package news.factory.com.single.activity;

import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import news.factory.com.base.dependency_injection.PerActivityScope;
import news.factory.com.model.interactor.ArticleInteractor;
import news.factory.com.model.interactor.ArticleInteractorImpl;
import news.factory.com.single.activity.presenter.SinglePresenter;
import news.factory.com.single.adapter.SinglePagerAdapter;

@Module
public class SingleActivityModule {

    private final SingleContract.View singleView;

    private final FragmentManager singleViewFragmentManager;

    public SingleActivityModule(SingleContract.View singleView, FragmentManager singleViewFragmentManager) {
        this.singleView = singleView;
        this.singleViewFragmentManager = singleViewFragmentManager;
    }

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
    public SingleContract.View provideSingleView(){
        return singleView;
    }

    @Provides
    @PerActivityScope
    public FragmentManager provideFragmentManager(){
        return singleViewFragmentManager;
    }

    @Provides
    @PerActivityScope
    public ArticleInteractor provideArticleInteractor(){
        return new ArticleInteractorImpl();
    }
}
