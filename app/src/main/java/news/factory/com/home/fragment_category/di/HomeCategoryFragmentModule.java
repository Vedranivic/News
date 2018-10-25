package news.factory.com.home.fragment_category.di;


import dagger.Module;
import dagger.Provides;
import news.factory.com.common.adapter.HomeCategoryPagerAdapter;
import news.factory.com.base.dependency_injection.PerFragmentScope;
import news.factory.com.home.fragment_category.HomeCategoryFragmentContract;
import news.factory.com.home.fragment_category.view.HomeCategoryFragment;

@Module
public class HomeCategoryFragmentModule {

    @Provides
    @PerFragmentScope
    public HomeCategoryFragmentContract.View provideHomeCategoryFragmentView(HomeCategoryFragment homeFragment){
        return homeFragment;
    }

    @Provides
    @PerFragmentScope
    public HomeCategoryPagerAdapter provideCategoryPagerAdapter(HomeCategoryFragment homeCategoryFragment){
        return new HomeCategoryPagerAdapter(homeCategoryFragment.getChildFragmentManager(), homeCategoryFragment.getContext());
    }

}
