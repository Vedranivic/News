package news.factory.com.single.activity.view;

import android.app.Activity;
import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import news.factory.com.base.dependency_injection.ApplicationScope;

@Module
public class SingleActivityModule {

    private final Activity context;

    public SingleActivityModule(Activity context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @Named("SingleActivityContext")
    public Context provideContext(){
        return context;
    }
}
