package news.factory.com.base.dependency_injection;

import android.content.Context;

import com.maradroid.dummyresponsegenerator.base.DRGInterceptor;
import com.maradroid.dummyresponsegenerator.utils.ConstKt;

import java.io.IOException;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
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
public class ServiceModule {

    @Provides
    @ApplicationScope
    public NewsAPI provideNewsAPI(Retrofit retrofit){
        return retrofit.create(NewsAPI.class);
    }

    @Provides
    @ApplicationScope
    public Retrofit provideRetrofit(OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @ApplicationScope
    @Provides OkHttpClient provideOkHttpClient(Interceptor interceptor, DRGInterceptor drgInterceptor){
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(drgInterceptor)
                .build();
    }

    @Provides
    @ApplicationScope
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
    @ApplicationScope
    public DRGInterceptor provideDRGInterceptor(@Named("ApplicationContext") Context context){
        return new DRGInterceptor(context, ConstKt.MEDIATYPE_JSON);
    }
}
