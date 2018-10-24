package news.factory.com.view_holder.top_block;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.rd.PageIndicatorView;

import java.util.List;

import butterknife.BindView;
import news.factory.com.R;
import news.factory.com.adapter.TopBlockPagerAdapter;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.ObjectWrapper;
import news.factory.com.base.RecyclerItemsWrapper;

public class TopBlockViewHolder extends BaseItemViewHolder {

    @BindView(R.id.vpTopBlock)
    ViewPager vpTopBlock;
    @BindView(R.id.pivTopBlock)
    PageIndicatorView pivTopBlock;

    private Object pagerAdapter;

    public TopBlockViewHolder(View itemView, List<RecyclerItemsWrapper> items, ObjectWrapper objectWrapper) {
        super(itemView, items, objectWrapper);
        this.pagerAdapter = objectWrapper.getPagerAdapter();
        setupAdapter();
    }

    private void setupAdapter() {
        pivTopBlock.setViewPager(vpTopBlock);
        vpTopBlock.setAdapter((TopBlockPagerAdapter) pagerAdapter);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void bind(int position) {
        ((TopBlockPagerAdapter) pagerAdapter).setItems(
                ((TopBlockDataClass)items.get(position).getItem()).getArticles()
        );
    }
}
