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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private News news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(MainActivity.this,SingleActivity.class);
        startActivity(i);
    }
}
