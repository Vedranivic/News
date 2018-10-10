package news.factory.com.single.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import news.factory.com.R;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.single.view_holder.FeatureViewHolder;
import news.factory.com.single.view_holder.ImageViewHolder;
import news.factory.com.single.view_holder.IndicatorViewHolder;
import news.factory.com.single.view_holder.PublishedViewHolder;
import news.factory.com.single.view_holder.TextViewHolder;
import news.factory.com.single.view_holder.TitleViewHolder;
import news.factory.com.single.view_holder.UpperTitleViewHolder;

public class RecyclerAdapter extends RecyclerView.Adapter<BaseItemViewHolder> {

    private List<RecyclerItemsWrapper> items;
    private LayoutInflater mInflater;

    public RecyclerAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }

    @NonNull
    @Override
    public BaseItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case R.layout.item_feature_image:
                return new FeatureViewHolder(mInflater.inflate(viewType,parent,false),items);
            case R.layout.item_title:
                return new TitleViewHolder(mInflater.inflate(viewType,parent,false),items);
            case R.layout.item_image:
                return new ImageViewHolder(mInflater.inflate(viewType,parent,false),items);
            case R.layout.item_text:
                return new TextViewHolder(mInflater.inflate(viewType,parent,false),items);
            case R.layout.item_uppertitle:
                return new UpperTitleViewHolder(mInflater.inflate(viewType,parent,false),items);
            case R.layout.item_published:
                return new PublishedViewHolder(mInflater.inflate(viewType,parent,false),items);
            case R.layout.item_indicator:
                return new IndicatorViewHolder(mInflater.inflate(viewType,parent,false),items);
        }

        return new DummyViewHolder(new View(parent.getContext()));
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

    private class DummyViewHolder extends BaseItemViewHolder{
        public DummyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(int position) {

        }
    }

    public void setItems(List<RecyclerItemsWrapper> items) {
        this.items = items;
        notifyDataSetChanged();
    }

}
