package news.factory.com.single.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import javax.inject.Inject;

import news.factory.com.R;
import news.factory.com.base.Constants;
import news.factory.com.single.category_fragment.view.CategoryFragment;

public class CategoryPagerAdapter extends FragmentStatePagerAdapter {

    private String id;
    private String page;
    private Context mContext;

    @Inject
    public CategoryPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: return CategoryFragment.newInstance(Constants.CATEGORY_MOSTPOPULAR,id,page);
            case 1: return CategoryFragment.newInstance(Constants.CATEGORY_MOSTREAD,id,page);
            case 2: return CategoryFragment.newInstance(Constants.CATEGORY_NEWEST,id,page);
            default: return CategoryFragment.newInstance(Constants.CATEGORY_MOSTREAD,id,page);
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0: return mContext.getString(R.string.MostPopularPageTitle);
            case 1: return mContext.getString(R.string.MostReadPageTitle);
            case 2: return mContext.getString(R.string.NewestPageTitle);
            default: return mContext.getString(R.string.MostPopularPageTitle);
        }
    }

    @Override
    public int getCount() {
        //Three tabs (Most popular, Most read, Newest)
        return 3;
    }

    public void setId(String id) {
        this.id = id;
        notifyDataSetChanged();
    }

    public void setPage(String page) {
        this.page = page;
        notifyDataSetChanged();
    }
}
