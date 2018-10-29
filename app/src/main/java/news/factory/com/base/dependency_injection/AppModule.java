package news.factory.com.base.dependency_injection;


import android.app.Application;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import news.factory.com.App;


@Module
public class AppModule {

    private App applicationContext;

    public AppModule(App applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Provides
    @Singleton
    @Named("ApplicationContext")
    public App getApplicationContext() {
        return applicationContext;
    }
}
