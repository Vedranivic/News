package news.factory.com;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.maradroid.dummyresponsegenerator.base.interactor.InteractorImpl;
import com.maradroid.dummyresponsegenerator.utils.SharedPerfRepo;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;
import news.factory.com.base.dependency_injection.AppComponent;
import news.factory.com.base.dependency_injection.AppModule;
import news.factory.com.base.dependency_injection.DaggerAppComponent;
import news.factory.com.base.networking.MessagingService;

public class App extends Application implements HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    public DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;
    @Inject
    public DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

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
}
