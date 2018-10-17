package news.factory.com.single.view_holder.category;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;


import butterknife.BindView;
import news.factory.com.R;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.single.adapter.CategoryPagerAdapter;


public class CategoryViewHolder extends BaseItemViewHolder {

    @BindView(R.id.vpCategory)
    ViewPager vpCategory;
    @BindView(R.id.tabSlider)
    TabLayout tabSlider;

    public CategoryPagerAdapter adapter;


    public CategoryViewHolder(View itemView, List<RecyclerItemsWrapper> items, Context context, FragmentManager fragmentManager) {
        super(itemView, items);

        //this.adapter = categoryPagerAdapter;
        //this.mContext = context; // itemView.getContext();
        //this.fragmentManager = fragmentManager; //((AppCompatActivity)mContext).getSupportFragmentManager();
        adapter = new CategoryPagerAdapter(fragmentManager, context);

        setupAdapter();
    }

    private void setupAdapter() {
        tabSlider.setupWithViewPager(vpCategory);
        vpCategory.setAdapter(adapter);
    }

    @Override
    public void bind(int position) {
        CategoryDataClass category = (CategoryDataClass) items.get(position).getItem();
        adapter.setId(category.getId());
        adapter.setPage(category.getPage());
    }

}
