package news.factory.com.base.dependency_injection;


import javax.inject.Singleton;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import news.factory.com.App;
import news.factory.com.base.networking.NewsAPI;
import news.factory.com.single.view_holder.category.CategoryViewHolderComponent;
import news.factory.com.single.view_holder.category.CategoryViewHolderModule;


@Singleton
@Component(modules = {AppModule.class, ServiceModule.class, ActivityBuilder.class, AndroidInjectionModule.class})
public interface AppComponent {

    NewsAPI getNewsAPI();

    void inject(App app);

    CategoryViewHolderComponent plus(CategoryViewHolderModule module);

}
