package news.factory.com;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.support.v4.app.Fragment;

import com.maradroid.dummyresponsegenerator.base.interactor.InteractorImpl;
import com.maradroid.dummyresponsegenerator.utils.SharedPerfRepo;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;
import dagger.android.support.HasSupportFragmentInjector;
import news.factory.com.base.dependency_injection.AppComponent;
import news.factory.com.base.dependency_injection.AppModule;
import news.factory.com.base.dependency_injection.DaggerAppComponent;

public class App extends Application implements HasActivityInjector, HasSupportFragmentInjector, HasServiceInjector {

    @Inject
    public DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;
    @Inject
    public DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    @Inject
    public DispatchingAndroidInjector<Service> serviceDispatchingAndroidInjector;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        new InteractorImpl(this).generateResponses(true);
        new SharedPerfRepo(this).setDummyResponse(true);

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return serviceDispatchingAndroidInjector;
    }
}
