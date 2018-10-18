package news.factory.com.base.dependency_injection;


import javax.inject.Singleton;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import news.factory.com.App;
import news.factory.com.single.activity.di.FragmentModule;


@Singleton
@Component(modules = {
        AppModule.class,
        ServiceModule.class,
        ActivityModule.class,
        AndroidInjectionModule.class,
        FragmentModule.class
})
public interface AppComponent {

    void inject(App app);

}
