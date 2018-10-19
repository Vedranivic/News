package news.factory.com.single.view_holder.category;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import news.factory.com.R;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.ObjectWrapper;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.single.adapter.CategoryPagerAdapter;


public class CategoryViewHolder extends BaseItemViewHolder {

    @BindView(R.id.vpCategory)
    ViewPager vpCategory;
    @BindView(R.id.tabSlider)
    TabLayout tabSlider;

    private Object pagerAdapter;

    public CategoryViewHolder(View itemView, List<RecyclerItemsWrapper> items, ObjectWrapper objectWrapper) {
        super(itemView, items, objectWrapper);
        this.pagerAdapter = objectWrapper.getPagerAdapter();
        setupAdapter();
    }

    private void setupAdapter() {
        tabSlider.setupWithViewPager(vpCategory);
        if(pagerAdapter instanceof CategoryPagerAdapter) {
            vpCategory.setAdapter((CategoryPagerAdapter)pagerAdapter);
        }
    }

    @Override
    public void bind(int position) {
        if(pagerAdapter instanceof CategoryPagerAdapter) {
            CategoryDataClass category = (CategoryDataClass) items.get(position).getItem();
            ((CategoryPagerAdapter) pagerAdapter).setId(category.getId());
            ((CategoryPagerAdapter) pagerAdapter).setPage(category.getPage());
        }
    }

}
