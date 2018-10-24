package news.factory.com.base.dependency_injection;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import news.factory.com.home.fragment_category.di.HomeCategoryFragmentModule;
import news.factory.com.home.fragment_category.view.HomeCategoryFragment;
import news.factory.com.home.fragment_category_sort.di.HomeSortCategoryFragmentModule;
import news.factory.com.home.fragment_category_sort.view.HomeSortCategoryFragment;
import news.factory.com.home.fragment_home.di.HomeFragmentModule;
import news.factory.com.home.fragment_home.view.HomeFragment;
import news.factory.com.single.fragment_category.di.CategoryFragmentModule;
import news.factory.com.single.fragment_category.view.CategoryFragment;
import news.factory.com.single.fragment_single.di.SingleFragmentModule;
import news.factory.com.single.fragment_single.view.SingleFragment;

@Module
public abstract class FragmentModule {

    @PerFragmentScope
    @ContributesAndroidInjector(modules = {SingleFragmentModule.class})
    abstract SingleFragment provideSingleFragmentFactory();

    @PerFragmentScope
    @ContributesAndroidInjector(modules = {CategoryFragmentModule.class})
    abstract CategoryFragment provideCategoryFragmentFactory();

    @PerFragmentScope
    @ContributesAndroidInjector(modules = {HomeFragmentModule.class})
    abstract HomeFragment provideHomeFragmentFactory();

    @PerFragmentScope
    @ContributesAndroidInjector(modules = {HomeCategoryFragmentModule.class})
    abstract HomeCategoryFragment provideHomeCategoryFragmentFactory();

    @PerFragmentScope
    @ContributesAndroidInjector(modules = {HomeSortCategoryFragmentModule.class})
    abstract HomeSortCategoryFragment provideHomeSortCategoryFragmentFactory();

}
