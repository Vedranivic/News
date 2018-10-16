package news.factory.com.base.dependency_injection;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import news.factory.com.single.activity.FragmentBuilder;
import news.factory.com.single.activity.SingleActivityModule;
import news.factory.com.single.activity.view.SingleActivity;

//Mapping of activities
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {SingleActivityModule.class, FragmentBuilder.class})
    abstract SingleActivity bindSingleActivity();
}
