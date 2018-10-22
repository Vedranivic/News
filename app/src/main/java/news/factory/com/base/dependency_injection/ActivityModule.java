package news.factory.com.base.dependency_injection;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import news.factory.com.home.activity.view.HomeActivity;
import news.factory.com.home.activity.di.HomeActivityModule;
import news.factory.com.single.activity.di.SingleActivityModule;
import news.factory.com.single.activity.view.SingleActivity;

//Mapping of activities
@Module
public abstract class ActivityModule {

    @PerActivityScope
    @ContributesAndroidInjector(modules = {SingleActivityModule.class})
    abstract SingleActivity bindSingleActivity();

    @PerActivityScope
    @ContributesAndroidInjector(modules = HomeActivityModule.class)
    abstract HomeActivity bindMainActivity();

}
