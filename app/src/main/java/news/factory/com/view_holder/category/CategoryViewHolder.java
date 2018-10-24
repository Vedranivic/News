package news.factory.com.view_holder.category;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import news.factory.com.R;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.ObjectWrapper;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.adapter.CategoryPagerAdapter;


public class CategoryViewHolder extends BaseItemViewHolder {

    @BindView(R.id.vpCategory)
    ViewPager vpCategory;
    @BindView(R.id.tabSlider)
    TabLayout tabSlider;

    public CategoryViewHolder(View itemView, List<RecyclerItemsWrapper> items, ObjectWrapper objectWrapper) {
        super(itemView, items, objectWrapper);
        this.objectWrapper = objectWrapper;
        setupAdapter();
    }

    private void setupAdapter() {
        tabSlider.setupWithViewPager(vpCategory);
        if(objectWrapper.getPagerAdapter() instanceof CategoryPagerAdapter) {
            vpCategory.setAdapter((CategoryPagerAdapter)objectWrapper.getPagerAdapter());
        }
    }

    @Override
    public void bind(int position) {
        if(objectWrapper.getPagerAdapter() instanceof CategoryPagerAdapter) {
            CategoryDataClass category = (CategoryDataClass) items.get(position).getItem();
            ((CategoryPagerAdapter) objectWrapper.getPagerAdapter()).setCategoryID(category.getCategoryID());
            ((CategoryPagerAdapter) objectWrapper.getPagerAdapter()).setPage(category.getPage());
        }
    }

}
