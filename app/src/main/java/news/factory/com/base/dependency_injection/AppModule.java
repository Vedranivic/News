package news.factory.com.base.dependency_injection;


import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    @ApplicationScope
    @Named("ApplicationContext")
    public Context getContext() {
        return mContext;
    }
}
