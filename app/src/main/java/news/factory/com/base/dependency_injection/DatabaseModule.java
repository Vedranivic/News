package news.factory.com.base.dependency_injection;

import android.util.Log;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import news.factory.com.App;

@Module(includes = AppModule.class)
public class DatabaseModule {

    @Provides
    @Singleton
    public Realm provideRealm(@Named("ApplicationContext") App applicationContext){
        Realm.init(applicationContext);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        return Realm.getInstance(config);
    }

}
