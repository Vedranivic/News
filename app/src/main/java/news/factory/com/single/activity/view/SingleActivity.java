package news.factory.com.single.activity.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnPageChange;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import news.factory.com.base.BaseActivity;
import news.factory.com.base.Constants;
import news.factory.com.R;
import news.factory.com.single.activity.SingleContract;
import news.factory.com.single.adapter.SinglePagerAdapter;

public class SingleActivity extends BaseActivity implements SingleContract.View {

    @BindView(R.id.vpArticles)
    ViewPager vpSingles;
    @BindView(R.id.bPrevious)
    FrameLayout bPrevious;
    @BindView(R.id.bNext)
    FrameLayout bNext;

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
        vpSingles.setAdapter(adapter);
    }

    private void setupMVP() {
        singlePresenter.initialize(getArticleID());
    }

    private void getArticle() {
        singlePresenter.getArticle();
    }

    @Override
    public void displayArticle(String articleID, int pages) {
        adapter.setArticleId(articleID);
        adapter.setPages(pages);
        checkButtonVisibility(vpSingles.getCurrentItem());
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

    @OnClick({R.id.bPrevious, R.id.bNext})
    public void doPaging(FrameLayout frameLayout){
        int currentPosition = vpSingles.getCurrentItem();

        switch (frameLayout.getId()){
            case R.id.bPrevious:
                vpSingles.setCurrentItem(currentPosition-1);
                break;
            case R.id.bNext:
                vpSingles.setCurrentItem(currentPosition+1);
                break;
        }
    }

    @OnPageChange(R.id.vpArticles)
    public void onPageStateChanged(int state){
        checkButtonVisibility(state);
    }

    private void checkButtonVisibility(int state) {
        if(state == 0){
            bPrevious.setVisibility(View.GONE);
        }
        else if(state == adapter.getCount()-1){
            bNext.setVisibility(View.GONE);
        }
        else{
            bPrevious.setVisibility(View.VISIBLE);
            bNext.setVisibility(View.VISIBLE);
        }
    }

}
