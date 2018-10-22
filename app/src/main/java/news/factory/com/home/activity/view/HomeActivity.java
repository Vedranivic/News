package news.factory.com.home.activity.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

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
    }

    private void switchToSingle() {
        SingleActivity.openActivityInstance(
                        HomeActivity.this,
                        "280146"
                        );
    }

    @Override
    public void displayBottomMenu(List<Menu> result) {
        homePagerAdapter.setBottomMenuItems(result);
    }
}
