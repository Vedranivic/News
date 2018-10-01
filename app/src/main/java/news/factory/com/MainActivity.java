package news.factory.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import news.factory.com.Model.DataModel.News;
import news.factory.com.Model.Networking.NewsAPI;
import news.factory.com.Model.Networking.ServiceGenerator;
import news.factory.com.UI.Single.SingleActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<News> {

    private static final String TAG = MainActivity.class.getSimpleName();
    private News news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getNews();
    }

    private void getNews() {

        Call<News> call = ServiceGenerator.getRetrofit(this).create(NewsAPI.class)
                .getNews("280146", "PTTOKEN", "1");
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<News> call, Response<News> response) {
        Log.d(TAG,"Call successfull!");
        if(response.body()!=null){
            news = response.body();
            Log.d(TAG + "TITLE: ",news.getTitle());
            Log.d(TAG + "FEATURED_IMAGE?: ",news.getNo_featured_image());
            Log.d(TAG + "TITLE: ",news.getTitle());
            Log.d(TAG + "TITLE: ",news.getTitle());
        }
        call.cancel();
        Intent i = new Intent(MainActivity.this,SingleActivity.class);
        startActivity(i);
    }

    @Override
    public void onFailure(Call<News> call, Throwable t) {
        Log.d(TAG,"Call failed!");
    }
}
