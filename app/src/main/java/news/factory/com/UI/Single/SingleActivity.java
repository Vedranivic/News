package news.factory.com.UI.Single;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.MainActivity;
import news.factory.com.Model.DataModel.BaseItem;
import news.factory.com.Model.DataModel.Content;
import news.factory.com.Model.DataModel.News;
import news.factory.com.Model.Networking.NewsAPI;
import news.factory.com.Model.Networking.ServiceGenerator;
import news.factory.com.R;
import news.factory.com.UI.Single.Adapter.SinglePagerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleActivity extends FragmentActivity {

    private int pages;
    private static final String TAG = SingleActivity.class.getSimpleName();

    @BindView(R.id.vpArticles)
    ViewPager vpSingles;

    private List<BaseItem> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        ButterKnife.bind(this);
        vpSingles = (ViewPager) findViewById(R.id.vpArticles);
        setupUI();
    }


    private void setupUI() {
        vpSingles.setAdapter(new SinglePagerAdapter(getSupportFragmentManager(),pages));
        vpSingles.setCurrentItem(0);
    }
}
