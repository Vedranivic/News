package news.factory.com.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import news.factory.com.base.Constants;
import news.factory.com.model.data_model.Menu;
import news.factory.com.single.category_fragment.view.CategoryFragment;

public class HomePagerAdapter extends FragmentStatePagerAdapter {

    private List<Menu> bottomMenuItems;

    @Inject
    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return CategoryFragment.newInstance(Constants.CATEGORY_MOSTPOPULAR,"0","1");
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
                Log.d("HOME_PRESENTER", pageTitle);
            }
        }
        return pageTitle;
    }

    public void setBottomMenuItems(List<Menu> bottomMenuItems){
        this.bottomMenuItems = bottomMenuItems;
        notifyDataSetChanged();
    }
}
