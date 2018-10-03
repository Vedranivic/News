package news.factory.com.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import news.factory.com.model.data_model.News;
import news.factory.com.R;
import news.factory.com.single.activity.SingleActivity;

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
        startActivity(SingleActivity.openActivityInstance(
                        MainActivity.this,
                        "280146"
                        )
        );
    }


}
