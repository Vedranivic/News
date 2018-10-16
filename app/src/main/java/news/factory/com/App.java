package news.factory.com;

import android.app.Application;

import com.maradroid.dummyresponsegenerator.base.interactor.InteractorImpl;
import com.maradroid.dummyresponsegenerator.utils.SharedPerfRepo;

import news.factory.com.base.dependency_injection.AppComponent;
import news.factory.com.base.dependency_injection.AppModule;
import news.factory.com.base.dependency_injection.DaggerAppComponent;
import news.factory.com.base.networking.NewsAPI;

public class App extends Application {

    private static AppComponent component;


    @Override
    public void onCreate() {
        super.onCreate();
        new InteractorImpl(this).generateResponses(true);
        new SharedPerfRepo(this).setDummyResponse(true);

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public static AppComponent getComponent() {
        return component;
    }
}
