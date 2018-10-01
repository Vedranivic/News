package news.factory.com.Model.Networking;

import android.content.Context;

import com.maradroid.dummyresponsegenerator.base.DRGInterceptor;
import com.maradroid.dummyresponsegenerator.utils.ConstKt;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit retrofit;

    public static Retrofit getRetrofit(Context context) {
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new DRGInterceptor(context, ConstKt.MEDIATYPE_JSON))
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
