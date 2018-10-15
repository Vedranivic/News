package news.factory.com.base.dependency_injection;

import android.content.Context;

import javax.inject.Named;

import dagger.Component;
import news.factory.com.base.networking.NewsAPI;
import news.factory.com.single.activity.view.SingleActivityModule;


@ApplicationScope
@Component(modules = {AppModule.class, ServiceModule.class, SingleActivityModule.class})
public interface AppComponent {

    @Named("ApplicationContext") Context getAppContext();
    NewsAPI getNewsAPI();
}
