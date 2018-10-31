package news.factory.com.base.dependency_injection;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

import com.maradroid.dummyresponsegenerator.base.DRGInterceptor;
import com.maradroid.dummyresponsegenerator.utils.ConstKt;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import news.factory.com.App;
import news.factory.com.base.Constants;
import news.factory.com.base.networking.NewsAPI;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = AppModule.class)
public class NetworkModule {

    @Provides
    @Singleton
    public NewsAPI provideNewsAPI(Retrofit retrofit){
        return retrofit.create(NewsAPI.class);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides OkHttpClient provideOkHttpClient(Interceptor interceptor, DRGInterceptor drgInterceptor){
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(drgInterceptor)
                .build();
    }

    @Provides
    @Singleton
    public Interceptor provideInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter("api_token", Constants.API_TOKEN).build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        };
    }

    @Provides
    @Singleton
    public DRGInterceptor provideDRGInterceptor(@Named("ApplicationContext") App applicationContext){
        return new DRGInterceptor(applicationContext, ConstKt.MEDIATYPE_JSON);
    }

}
