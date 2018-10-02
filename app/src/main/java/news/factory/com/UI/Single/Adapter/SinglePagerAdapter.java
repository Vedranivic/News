package news.factory.com.UI.Single.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.List;

import news.factory.com.Model.DataModel.BaseItem;
import news.factory.com.UI.Single.SingleFragment;

public class SinglePagerAdapter extends FragmentStatePagerAdapter {

    private int pages;

    public SinglePagerAdapter(FragmentManager fm, int pages) {
        super(fm);
        this.pages = pages;
    }

    @Override
    public Fragment getItem(int position) {
        SingleFragment article = new SingleFragment();
        Bundle bundle = new Bundle();
        bundle.putString("PAGE", String.valueOf(position+1));
        article.setArguments(bundle);
        return article;

    }

    @Override
    public int getCount() {
        return this.pages;
    }
}
