package news.factory.com.single.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.model.Constants;
import news.factory.com.R;
import news.factory.com.single.adapter.SinglePagerAdapter;

public class SingleActivity extends AppCompatActivity implements SingleActivityInterface {

    private SinglePresenter presenter;

    @BindView(R.id.vpArticles)
    ViewPager vpSingles;

    public static Intent openActivityInstance(Context context,String articleId) {
        Intent i = new Intent(context, SingleActivity.class);
        i.putExtra(Constants.ARTICLE_KEY,articleId);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        ButterKnife.bind(this);

        setupMVP();
        getArticle();
    }

    private void setupMVP() {
        presenter = new SinglePresenter(this);
    }

    private void getArticle() {
        presenter.getArticle();
    }

    @Override
    public void displayArticle(String articleID, int pages) {
        vpSingles.setAdapter(new SinglePagerAdapter(getSupportFragmentManager(), articleID, pages));
        vpSingles.setCurrentItem(0);
    }

    public String getArticleID(){
        if(getIntent().getExtras()!=null) {
            return getIntent().getExtras().getString(Constants.ARTICLE_KEY);
        }
        else return null;
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //call.cancel();
    }

    @Override
    public void onStop() {
        super.onStop();
        //call.cancel();
    }

}
