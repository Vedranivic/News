package news.factory.com.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import news.factory.com.R;
import news.factory.com.base.networking.APIServiceGenerator;
import news.factory.com.single.activity.view.SingleActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeRetrofit();
        switchToSingle();
    }

    private void initializeRetrofit() {
        APIServiceGenerator.setRetrofit(getApplicationContext());
    }

    private void switchToSingle() {
        SingleActivity.openActivityInstance(
                        MainActivity.this,
                        "280146"
                        );
    }


}
