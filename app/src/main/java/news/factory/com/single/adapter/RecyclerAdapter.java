package news.factory.com.single.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import news.factory.com.R;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.single.view_holder.category.CategoryViewHolder;
import news.factory.com.single.view_holder.category_item.CategoryItemViewHolder;
import news.factory.com.single.view_holder.feature.FeatureViewHolder;
import news.factory.com.single.view_holder.image.ImageViewHolder;
import news.factory.com.single.view_holder.indicator.IndicatorViewHolder;
import news.factory.com.single.view_holder.published.PublishedViewHolder;
import news.factory.com.single.view_holder.text.TextViewHolder;
import news.factory.com.single.view_holder.title.TitleViewHolder;
import news.factory.com.single.view_holder.uppertitle.UpperTitleViewHolder;

public class RecyclerAdapter extends RecyclerView.Adapter<BaseItemViewHolder> {

    private List<RecyclerItemsWrapper> items;
    private LayoutInflater mInflater;

    @Inject
    public Context mContext;

    //private FragmentManager fragmentManager;
    private CategoryPagerAdapter categoryPagerAdapter;

    @Inject
    public RecyclerAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }

    @NonNull
    @Override
    public BaseItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(viewType,parent,false);
        switch (viewType){
            case R.layout.item_feature_image:
                return new FeatureViewHolder(view,items);
            case R.layout.item_title:
                return new TitleViewHolder(view,items);
            case R.layout.item_image:
                return new ImageViewHolder(view,items);
            case R.layout.item_text:
                return new TextViewHolder(view,items);
            case R.layout.item_uppertitle:
                return new UpperTitleViewHolder(view,items);
            case R.layout.item_published:
                return new PublishedViewHolder(view,items);
            case R.layout.item_indicator:
                return new IndicatorViewHolder(view,items);
            case R.layout.item_category:
                return new CategoryViewHolder(view,items,categoryPagerAdapter);
            case R.layout.item_news:
                return new CategoryItemViewHolder(view,items);
        }

        return new DummyViewHolder(new View(parent.getContext()),items);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull BaseItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if(items != null)
            return items.size();
        else
            return 0;
    }

    public void setItems(List<RecyclerItemsWrapper> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setChildFragmentManager(FragmentManager childFragmentManager) {
        //fragmentManager = childFragmentManager;
    }

    public void setCategoryPagerAdaper(CategoryPagerAdapter categoryPagerAdapter) {
        this.categoryPagerAdapter = categoryPagerAdapter;
    }

    private class DummyViewHolder extends BaseItemViewHolder{
        public DummyViewHolder(View itemView,List<RecyclerItemsWrapper> items) {
            super(itemView, items);
        }

        @Override
        public void bind(int position) {

        }
    }
}
