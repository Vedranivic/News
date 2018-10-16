package news.factory.com.base.networking;

import android.content.Context;
import android.util.Log;

import com.maradroid.dummyresponsegenerator.base.DRGInterceptor;
import com.maradroid.dummyresponsegenerator.utils.ConstKt;

import java.io.IOException;
import news.factory.com.base.Constants;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIServiceGenerator {
    private static NewsAPI api;

    public static void setRetrofit(Context context){
        if (api == null){

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();
                            HttpUrl url = request.url().newBuilder().addQueryParameter("api_token",Constants.API_TOKEN).build();
                            request = request.newBuilder().url(url).build();
                            return chain.proceed(request);
                        }
                    })
                    .addInterceptor(new DRGInterceptor(context, ConstKt.MEDIATYPE_JSON))
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            api = retrofit.create(NewsAPI.class);
        }
    }

    public static NewsAPI getAPI(){
        return api;
    }
}