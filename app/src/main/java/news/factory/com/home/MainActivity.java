package news.factory.com.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import news.factory.com.model.data_model.News;
import news.factory.com.R;
import news.factory.com.base.networking.ServiceGenerator;
import news.factory.com.single.activity.view.SingleActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private News news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeRetrofit();
        switchToSingle();
    }

    private void initializeRetrofit() {
        //ServiceGenerator.setRetrofit(getApplicationContext());
        ServiceGenerator.setRetrofitRx(getApplicationContext());
    }

    private void switchToSingle() {
        SingleActivity.openActivityInstance(
                        MainActivity.this,
                        "280146"
                        );
    }


}
