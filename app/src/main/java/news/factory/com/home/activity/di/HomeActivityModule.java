package news.factory.com.home.activity.di;

import dagger.Module;
import dagger.Provides;
import news.factory.com.adapter.HomePagerAdapter;
import news.factory.com.base.dependency_injection.PerActivityScope;
import news.factory.com.home.activity.HomeContract;
import news.factory.com.home.activity.view.HomeActivity;
import news.factory.com.home.activity.presenter.HomePresenter;
import news.factory.com.model.interactor.CategoryInteractor;
import news.factory.com.model.interactor.CategoryInteractorImpl;
import news.factory.com.model.interactor.HomeInteractor;
import news.factory.com.model.interactor.HomeInteractorImpl;


@Module
public class HomeActivityModule {

    @Provides
    @PerActivityScope
    public HomeContract.View provideSingleView(HomeActivity homeActivity){
        return homeActivity;
    }

    @Provides
    @PerActivityScope
    public HomeContract.Presenter provideSinglePresenter(HomePresenter presenter){
        return presenter;
    }

    @Provides
    @PerActivityScope
    public HomeInteractor provideCategoryInteractor(HomeInteractorImpl homeInteractor){
        return homeInteractor;
    }

    @Provides
    @PerActivityScope
    public HomePagerAdapter provideHomePagerAdapter(HomeActivity homeActivity){
        return new HomePagerAdapter(homeActivity.getSupportFragmentManager());
    }

}
