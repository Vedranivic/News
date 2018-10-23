package news.factory.com.home.activity.view;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.adapter.HomePagerAdapter;
import news.factory.com.base.BaseActivity;
import news.factory.com.home.activity.HomeContract;
import news.factory.com.model.data_model.Menu;
import news.factory.com.single.activity.view.SingleActivity;

public class HomeActivity extends BaseActivity implements HomeContract.View {

    private static final String TAG = HomeActivity.class.getSimpleName();

    @BindView(R.id.vpHome)
    ViewPager vpHome;
    @BindView(R.id.tabHome)
    TabLayout tabHome;

    @Inject
    public HomeContract.Presenter homePresenter;
    @Inject
    public HomePagerAdapter homePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        //switchToSingle();
        setupAdapter();
        setupMVP();
        getMenu();
        getBottomMenu();
        getHomeItems();
    }

    private void getHomeItems() {
        homePresenter.getHomeItems();
    }

    private void getBottomMenu() {
        homePresenter.getBottomMenu();
    }

    private void getMenu() {
        homePresenter.getMenu();
    }

    private void setupMVP() {
        //homePresenter.initialize();

    }

    private void setupAdapter() {
        vpHome.setAdapter(homePagerAdapter);
        tabHome.setupWithViewPager(vpHome,true);
        tabHome.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectTab(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                unselectTab(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                selectTab(tab);
            }
        });
    }

    @Override
    public void displayBottomMenu(List<Menu> result) {
        homePagerAdapter.setBottomMenuItems(result);
        for(int i=0; i < tabHome.getTabCount(); i++){
            tabHome.getTabAt(i).setCustomView(R.layout.tab_home);
        }
        selectTab(tabHome.getTabAt(0));
    }

    private void selectTab(TabLayout.Tab tab){
        if(tab.getCustomView()!=null) {
            TextView textView = (TextView) tab.getCustomView().findViewById(android.R.id.text1);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(20);
        }
    }

    private void unselectTab(TabLayout.Tab tab){
        if(tab.getCustomView()!=null) {
            TextView textView = (TextView) tab.getCustomView().findViewById(android.R.id.text1);
            textView.setTextColor(getResources().getColor(R.color.unselectedTextTransparent));
            textView.setTextSize(14);
        }
    }

    private void switchToSingle() {
        SingleActivity.openActivityInstance(
                HomeActivity.this,
                "280146"
        );
    }

}
