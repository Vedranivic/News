package news.factory.com.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import javax.inject.Inject;

import news.factory.com.base.Constants;
import news.factory.com.home.fragment_home.view.HomeFragment;
import news.factory.com.model.data_model.Menu;
import news.factory.com.single.fragment_category.view.CategoryFragment;

public class HomePagerAdapter extends FragmentStatePagerAdapter {

    private List<Menu> bottomMenuItems;

    @Inject
    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return HomeFragment.newInstance();
    }

    @Override
    public int getCount() {
        if(bottomMenuItems != null){
            return bottomMenuItems.size();
        }
        else return 0;
    }

    @Override
    public CharSequence getPageTitle(int position){
        String pageTitle = "";
        for(Menu menu : bottomMenuItems){
            if(menu.getPriority()-1 == position){
                pageTitle = menu.getTitle();
            }
        }
        return pageTitle;
    }

    public void setBottomMenuItems(List<Menu> bottomMenuItems){
        this.bottomMenuItems = bottomMenuItems;
        notifyDataSetChanged();
    }
}
