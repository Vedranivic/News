package news.factory.com.Single;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.Model.Constants;
import news.factory.com.Model.DataModel.News;
import news.factory.com.Model.Networking.NewsAPI;
import news.factory.com.Model.Networking.ServiceGenerator;
import news.factory.com.R;
import news.factory.com.Single.Adapter.SinglePagerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleActivity extends AppCompatActivity implements Callback<News> {

    private static final String TAG = SingleActivity.class.getSimpleName();
    private Call<News> call;
    private String articleID;

    @BindView(R.id.vpArticles)
    ViewPager vpSingles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        ButterKnife.bind(this);
        getPages();
    }


    private void setupUI(int pages) {
        vpSingles.setAdapter(new SinglePagerAdapter(getSupportFragmentManager(),articleID,pages));
        vpSingles.setCurrentItem(0);
    }

    private void getPages() {
        //getting number of pages in an article (needed for ViewPager)
        if(getIntent().getExtras()!=null) {
            articleID = getIntent().getExtras().getString(Constants.ARTICLE_KEY);
            call = ServiceGenerator.getRetrofit(this).create(NewsAPI.class)
                    .getNews(articleID,Constants.FIRST_PAGE_VALUE);
            call.enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<News> call, Response<News> response) {
        Log.d(TAG,"Call successfull!");

        if(response.body()!=null) {
            setupUI(Integer.parseInt(response.body().getPages_no()));
        }
    }

    @Override
    public void onFailure(Call<News> call, Throwable t) {
        Log.d(TAG,"Call failed!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        call.cancel();
    }

    @Override
    public void onStop() {
        super.onStop();
        call.cancel();
    }
}
