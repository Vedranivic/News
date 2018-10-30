package news.factory.com.base.dependency_injection;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import news.factory.com.messaging.MessagingService;
import news.factory.com.messaging.di.MessagingServiceModule;

@Module
public abstract class ServiceModule {

    @PerServiceScope
    @ContributesAndroidInjector(modules = MessagingServiceModule.class)
    abstract MessagingService provideMessagingServiceFactory();

}
