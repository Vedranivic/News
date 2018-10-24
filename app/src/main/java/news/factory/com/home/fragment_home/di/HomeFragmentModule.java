package news.factory.com.home.fragment_home.di;

import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import news.factory.com.adapter.RecyclerAdapter;
import news.factory.com.adapter.RecyclerAdapterImpl;
import news.factory.com.adapter.TopBlockPagerAdapter;
import news.factory.com.base.ObjectWrapper;
import news.factory.com.base.dependency_injection.PerFragmentScope;
import news.factory.com.home.fragment_home.HomeFragmentContract;
import news.factory.com.home.fragment_home.presenter.HomeFragmentPresenter;
import news.factory.com.home.fragment_home.view.HomeFragment;
import news.factory.com.model.interactor.HomeInteractor;
import news.factory.com.model.interactor.HomeInteractorImpl;


@Module
public class HomeFragmentModule {
    @Provides
    @PerFragmentScope
    public HomeFragmentContract.View provideHomeFragmentView(HomeFragment homeFragment){
        return homeFragment;
    }

    @Provides
    @PerFragmentScope
    public HomeFragmentContract.Presenter provideHomeFragmentPresenter(HomeFragmentPresenter homeFragmentPresenter){
        return homeFragmentPresenter;
    }

    @Provides
    @PerFragmentScope
    public HomeInteractor provideHomeInteractor(HomeInteractorImpl homeInteractor){
        return homeInteractor;
    }

    @Provides
    @PerFragmentScope
    public RecyclerAdapter provideRecyclerAdapter(RecyclerAdapterImpl recyclerAdapter){
        return recyclerAdapter;
    }

    @Provides
    @PerFragmentScope
    public ObjectWrapper provideObjectWrapper(HomeFragmentContract.Presenter presenter, HomeFragmentContract.View view, TopBlockPagerAdapter topBlockPagerAdapter){
        return new ObjectWrapper(presenter, view, topBlockPagerAdapter);
    }
}
