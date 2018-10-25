package news.factory.com.common.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import javax.inject.Inject;

import news.factory.com.R;
import news.factory.com.base.Constants;
import news.factory.com.home.fragment_category_sort.view.HomeSortCategoryFragment;
import news.factory.com.single.fragment_category.view.CategoryFragment;

public class HomeCategoryPagerAdapter extends FragmentStatePagerAdapter {

    private String categoryID;
    private String page;
    private Context mContext;
    private String color;

    @Inject
    public HomeCategoryPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return HomeSortCategoryFragment.newInstance(Constants.CATEGORY_NEWEST, categoryID, page, color);
            case 1:
                return HomeSortCategoryFragment.newInstance(Constants.CATEGORY_MOSTREAD, categoryID, page, color);
            default:
                return HomeSortCategoryFragment.newInstance(Constants.CATEGORY_NEWEST, categoryID, page, color);
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0: return mContext.getString(R.string.NewestPageTitle);
            case 1: return mContext.getString(R.string.MostReadPageTitle);
            default: return mContext.getString(R.string.NewestPageTitle);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
        notifyDataSetChanged();
    }

    public void setPage(String page) {
        this.page = page;
        notifyDataSetChanged();
    }

    public void setColor(String color) {
        this.color = color;
        notifyDataSetChanged();
    }
}

