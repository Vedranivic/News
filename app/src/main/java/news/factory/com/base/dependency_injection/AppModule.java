package news.factory.com.base.dependency_injection;


import android.app.Application;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {

    private Application applicationContext;

    public AppModule(Application applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Provides
    @Singleton
    @Named("ApplicationContext")
    public Application getApplicationContext() {
        return applicationContext;
    }
}
