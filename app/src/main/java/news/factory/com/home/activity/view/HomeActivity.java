package news.factory.com.home.activity.view;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnPageChange;
import news.factory.com.R;
import news.factory.com.common.adapter.HomePagerAdapter;
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

    private List<Menu> bottomMenuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

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
        bottomMenuItems = result;
        homePagerAdapter.setBottomMenuItems(result);
        for(int i=0; i < tabHome.getTabCount(); i++){
            tabHome.getTabAt(i).setCustomView(R.layout.tab_home);
        }
        selectTab(tabHome.getTabAt(0));
    }

    @Override
    public void showMore(String categoryName){
        for(Menu menu : bottomMenuItems){
            if(menu.getTitle().equals(categoryName)) {
                vpHome.setCurrentItem(menu.getPriority()-1);
            }
        }
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

    @OnPageChange(R.id.vpHome)
    public void onPageStateChanged(int position){
        tabHome.setBackgroundColor(Color.parseColor(bottomMenuItems.get(position).getColor()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        homePresenter.dispose();
    }
}
