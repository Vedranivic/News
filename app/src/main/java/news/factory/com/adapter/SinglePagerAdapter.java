package news.factory.com.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import javax.inject.Inject;

import news.factory.com.single.fragment_single.view.SingleFragment;

public class SinglePagerAdapter extends FragmentStatePagerAdapter {

    private int pages;
    private String articleId;


    @Inject
    public SinglePagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return SingleFragment.newInstance(articleId,String.valueOf(position+1));
    }

    @Override
    public int getCount() {
        return this.pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
        notifyDataSetChanged();
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
        notifyDataSetChanged();
    }

}
