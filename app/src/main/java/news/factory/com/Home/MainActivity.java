package news.factory.com.Home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import news.factory.com.Model.DataModel.News;
import news.factory.com.R;
import news.factory.com.Single.SingleActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private News news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchToSingle();
    }

    private void switchToSingle() {
        Intent i = new Intent(MainActivity.this,SingleActivity.class);
        i.putExtra("articleID","280146");
        i.putExtra("apiToken","PTTOKEN");
        startActivity(i);
    }


}
