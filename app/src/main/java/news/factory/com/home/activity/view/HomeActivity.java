package news.factory.com.home.activity.view;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnPageChange;
import io.realm.Realm;
import io.realm.RealmResults;
import news.factory.com.R;
import news.factory.com.base.Constants;
import news.factory.com.base.adapter.HomePagerAdapter;
import news.factory.com.base.BaseActivity;
import news.factory.com.home.activity.HomeContract;
import news.factory.com.model.data_model.Menu;
import news.factory.com.single.activity.view.SingleActivity;

public class HomeActivity extends BaseActivity implements HomeContract.View, NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = HomeActivity.class.getSimpleName();

    @BindView(R.id.vpHome)
    ViewPager vpHome;
    @BindView(R.id.tabHome)
    TabLayout tabHome;
    @BindView(R.id.drawerMenu)
    DrawerLayout drawerMenu;
    @BindView(R.id.nvMenu)
    NavigationView navigationView;


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

        setupMenuNavigation();
        setupAdapter();
        setupMVP();
        getMenu();
        getBottomMenu();
        notificationLaunch();
    }

    private void notificationLaunch() {
        if(getIntent().hasExtra(Constants.ARTICLE_KEY)){
            SingleActivity.openActivityInstance(
                    this,
                    getIntent().getExtras().getString(Constants.ARTICLE_KEY));
            getIntent().removeExtra(Constants.ARTICLE_KEY);
        }
    }

    private void setupMenuNavigation() {
        navigationView.setNavigationItemSelectedListener(this);
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

    private void setupMVP() {
        homePresenter.initialize(isNetworkConnected());
    }

    private void getMenu() {
        homePresenter.getMenu();
    }

    private void getBottomMenu() {
        homePresenter.getBottomMenu();
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
    public void displayMenu(List<Menu> result) {
        android.view.Menu menuItems = navigationView.getMenu();
        for(Menu menuItem : result){
            menuItems.add(menuItem.getTitle());
            for(Menu submenu : menuItem.getSubmenu_items()) {
                menuItems.addSubMenu(submenu.getTitle());
            }
        }
    }

    @Override
    public void showMore(String categoryName){
        for(Menu menu : bottomMenuItems){
            if(menu.getTitle().equals(categoryName)) {
                vpHome.setCurrentItem(menu.getPriority()-1);
            }
        }
    }

    @Override
    public void displayErrorMessage(String errorMessage) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(errorMessage)
                .create().show();
    }

    private void selectTab(TabLayout.Tab tab){
        if(tab.getCustomView()!=null) {
            TextView textView = (TextView) tab.getCustomView().findViewById(android.R.id.text1);
            textView.setTextAppearance(this,R.style.homeTabStyle_Selected);
        }
    }

    private void unselectTab(TabLayout.Tab tab){
        if(tab.getCustomView()!=null) {
            TextView textView = (TextView) tab.getCustomView().findViewById(android.R.id.text1);
            textView.setTextAppearance(this,R.style.homeTabStyle_Unselected);
        }
    }

    @OnPageChange(R.id.vpHome)
    public void onPageStateChanged(int position){
        tabHome.setBackgroundColor(Color.parseColor(bottomMenuItems.get(position).getColor()));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        for(Menu menu : bottomMenuItems){
            if(item.getTitle().equals(menu.getTitle())) {
                vpHome.setCurrentItem(menu.getPriority()-1);
                drawerMenu.closeDrawer(GravityCompat.START);
            }
        }
        return true;
    }

}
