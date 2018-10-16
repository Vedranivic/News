package news.factory.com;

import android.app.Activity;
import android.app.Application;

import com.maradroid.dummyresponsegenerator.base.interactor.InteractorImpl;
import com.maradroid.dummyresponsegenerator.utils.SharedPerfRepo;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import news.factory.com.base.dependency_injection.AppComponent;
import news.factory.com.base.dependency_injection.AppModule;
import news.factory.com.base.dependency_injection.DaggerAppComponent;
import news.factory.com.base.networking.NewsAPI;

public class App extends Application implements HasActivityInjector {

    private static AppComponent component;

    @Inject
    public DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        new InteractorImpl(this).generateResponses(true);
        new SharedPerfRepo(this).setDummyResponse(true);

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        component.inject(this);

    }

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
