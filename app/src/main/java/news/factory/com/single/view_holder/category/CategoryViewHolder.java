package news.factory.com.single.view_holder.category;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import news.factory.com.R;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.single.adapter.CategoryPagerAdapter;
import news.factory.com.single.category_fragment.view.CategoryFragment;

public class CategoryViewHolder extends BaseItemViewHolder {

    @BindView(R.id.vpCategory)
    ViewPager vpCategory;
    @BindView(R.id.tabSlider)
    TabLayout tabSlider;

    private FragmentManager fragmentManager;
    private Context mContext;
    private CategoryPagerAdapter adapter;

    public CategoryViewHolder(View itemView, List<RecyclerItemsWrapper> items, Context context, FragmentManager fragmentManager) {
        super(itemView, items);

        this.mContext = context;
        this.fragmentManager = fragmentManager;
        setupAdapter();
    }

    private void setupAdapter() {
        adapter = new CategoryPagerAdapter(fragmentManager, mContext);
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
