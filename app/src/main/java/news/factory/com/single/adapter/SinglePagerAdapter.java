package news.factory.com.single.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import news.factory.com.single.fragment.view.SingleFragment;

public class SinglePagerAdapter extends FragmentStatePagerAdapter {

    private int pages;
    private String articleId;

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
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
