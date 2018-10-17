package news.factory.com.single.activity.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import news.factory.com.base.dependency_injection.PerFragmentScope;
import news.factory.com.single.category_fragment.di.CategoryFragmentModule;
import news.factory.com.single.category_fragment.view.CategoryFragment;
import news.factory.com.single.fragment.di.SingleFragmentModule;
import news.factory.com.single.fragment.view.SingleFragment;

@Module
public abstract class FragmentBuilder {

    @PerFragmentScope
    @ContributesAndroidInjector(modules = {SingleFragmentModule.class})
    abstract SingleFragment provideSingleFragmentFactory();

    @PerFragmentScope
    @ContributesAndroidInjector(modules = {CategoryFragmentModule.class})
    abstract CategoryFragment provideCategoryFragmentFactory();

}
