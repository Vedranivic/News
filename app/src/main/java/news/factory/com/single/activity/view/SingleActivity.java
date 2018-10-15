package news.factory.com.single.activity.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.internal.GwtIncompatible;
import news.factory.com.App;
import news.factory.com.base.Constants;
import news.factory.com.R;
import news.factory.com.base.dependency_injection.AppComponent;
import news.factory.com.base.dependency_injection.AppModule;
import news.factory.com.base.dependency_injection.DaggerAppComponent;
import news.factory.com.single.activity.SingleContract;
import news.factory.com.single.activity.presenter.SinglePresenter;
import news.factory.com.single.adapter.SinglePagerAdapter;

public class SingleActivity extends AppCompatActivity implements SingleContract.View {

    @BindView(R.id.vpArticles)
    ViewPager vpSingles;

    @Inject
    public SingleContract.Presenter singlePresenter;

    @Inject
    public SinglePagerAdapter adapter;


    public static void openActivityInstance(Context context,String articleId) {
        Intent i = new Intent(context, SingleActivity.class);
        i.putExtra(Constants.ARTICLE_KEY,articleId);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        ButterKnife.bind(this);

        setupAdapter();
        setupMVP();
        getArticle();
    }

    private void setupAdapter() {
        //adapter = new SinglePagerAdapter(getSupportFragmentManager());
        vpSingles.setAdapter(adapter);
    }

    private void setupMVP() {
        //singlePresenter = new SinglePresenter(this);
        singlePresenter.initialize(getArticleID());
    }

    private void getArticle() {
        singlePresenter.getArticle();
    }

    @Override
    public void displayArticle(String articleID, int pages) {
        adapter.setArticleId(articleID);
        adapter.setPages(pages);
    }

    public String getArticleID(){
        if(getIntent().getExtras()!=null) {
            return getIntent().getExtras().getString(Constants.ARTICLE_KEY);
        }
        else return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        singlePresenter.dispose();
    }

}
