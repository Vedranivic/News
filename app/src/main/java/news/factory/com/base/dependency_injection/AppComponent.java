package news.factory.com.base.dependency_injection;


import javax.inject.Singleton;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import news.factory.com.App;


@Singleton
@Component(modules = {AppModule.class, ServiceModule.class, ActivityBuilder.class, AndroidInjectionModule.class})
public interface AppComponent {

    void inject(App app);

}
