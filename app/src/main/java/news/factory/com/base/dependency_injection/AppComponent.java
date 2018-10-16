package news.factory.com.base.dependency_injection;

import android.app.Application;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import news.factory.com.base.networking.NewsAPI;
import news.factory.com.single.activity.SingleActivityComponent;
import news.factory.com.single.activity.SingleActivityModule;
import news.factory.com.single.category_fragment.CategoryFragmentComponent;
import news.factory.com.single.category_fragment.CategoryFragmentModule;
import news.factory.com.single.category_fragment.view.CategoryFragment;
import news.factory.com.single.fragment.SingleFragmentComponent;
import news.factory.com.single.fragment.SingleFragmentModule;
import news.factory.com.single.fragment.view.SingleFragment;


@Singleton
@Component(modules = {AppModule.class, ServiceModule.class})
public interface AppComponent {

    @Named("ApplicationContext")
    Application getAppContext();

    NewsAPI getNewsAPI();

    SingleActivityComponent plus(SingleActivityModule module);

    SingleFragmentComponent plus(SingleFragmentModule module);

    CategoryFragmentComponent plus(CategoryFragmentModule module);
}
