package news.factory.com.single.activity.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnPageChange;
import news.factory.com.base.BaseActivity;
import news.factory.com.base.Constants;
import news.factory.com.R;
import news.factory.com.single.activity.SingleContract;
import news.factory.com.base.adapter.SinglePagerAdapter;

public class SingleActivity extends BaseActivity implements SingleContract.View {

    @BindView(R.id.vpArticles)
    ViewPager vpSingles;
    @BindView(R.id.bPrevious)
    FrameLayout bPrevious;
    @BindView(R.id.bNext)
    FrameLayout bNext;
    @BindView(R.id.shareButton)
    FrameLayout shareButton;

    @Inject
    public SingleContract.Presenter singlePresenter;
    @Inject
    public SinglePagerAdapter adapter;

    private String articleURL;


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
    public void displayArticle(String articleID, String articleUrl, int pages) {
        adapter.setArticleId(articleID);
        adapter.setPages(pages);
        articleURL = articleUrl;
        checkButtonVisibility(vpSingles.getCurrentItem());
    }

    public String getArticleID(){
        if(getIntent().getExtras()!=null) {
            return getIntent().getExtras().getString(Constants.ARTICLE_KEY);
        }
        else return null;
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
        if(adapter.getCount() == 1){
            bPrevious.setVisibility(View.GONE);
            bNext.setVisibility(View.GONE);
        }
        else if(state == 0){
            bPrevious.setVisibility(View.GONE);
            bNext.setVisibility(View.VISIBLE);
        }
        else if(state == adapter.getCount()-1){
            bNext.setVisibility(View.GONE);
            bPrevious.setVisibility(View.VISIBLE);
        }
        else{
            bPrevious.setVisibility(View.VISIBLE);
            bNext.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.shareButton)
    public void onShare(View view){
        if(articleURL!=null) {
            Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Avaz Article");
            shareIntent.putExtra(Intent.EXTRA_TEXT, articleURL);
            startActivity(Intent.createChooser(shareIntent, "Share via"));
            //on successfull share -> update number of shares
        }
    }

}
