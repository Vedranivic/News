package news.factory.com.base.view_holder.top_block;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.rd.PageIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnPageChange;
import news.factory.com.R;
import news.factory.com.base.adapter.TopBlockPagerAdapter;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.ObjectWrapper;
import news.factory.com.base.RecyclerItemsWrapper;

public class TopBlockViewHolder extends BaseItemViewHolder {

    @BindView(R.id.vpTopBlock)
    ViewPager vpTopBlock;
    @BindView(R.id.pivTopBlock)
    PageIndicatorView pivTopBlock;

    private Object pagerAdapter;
    private int mCurrentPosition;

    public TopBlockViewHolder(View itemView, List<RecyclerItemsWrapper> items, ObjectWrapper objectWrapper) {
        super(itemView, items, objectWrapper);
        this.pagerAdapter = objectWrapper.getPagerAdapter();
        setupAdapter();
    }

    private void setupAdapter() {
        pivTopBlock.setViewPager(vpTopBlock);
        pivTopBlock.setAnimationDuration(200);
        vpTopBlock.setAdapter((TopBlockPagerAdapter) pagerAdapter);
        vpTopBlock.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                int lastPageIndex = ((TopBlockPagerAdapter) pagerAdapter).getCount()-1;

                if (mCurrentPosition == 0)
                    vpTopBlock.setCurrentItem(lastPageIndex - 1, false);

                if (mCurrentPosition == lastPageIndex)
                    vpTopBlock.setCurrentItem(1, false);
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void bind(int position) {
        if(items.get(position).getViewType() == R.layout.item_top_block) {
            ((TopBlockPagerAdapter) pagerAdapter).setItems(
                    ((TopBlockDataClass) items.get(position).getItem()).getArticles()
            );
            vpTopBlock.setCurrentItem(1);
        }
    }

}
