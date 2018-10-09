package news.factory.com.single.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.Constants;
import news.factory.com.base.BaseItem;
import news.factory.com.R;

public class SingleRecyclerAdapter extends RecyclerView.Adapter<BaseItemViewHolder> {

    private List<? extends BaseItem> items;
    private LayoutInflater mInflater;


    public SingleRecyclerAdapter(Context context) {
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
            case Constants.FEATURE_TYPE:
                return new SingleViewHolder.FeatureViewHolder(mInflater.inflate(R.layout.item_feature_image,parent,false));
            case Constants.TITLE_TYPE:
                return new SingleViewHolder.TitleViewHolder(mInflater.inflate(R.layout.item_title,parent,false));
            case Constants.IMAGE_TYPE:
                return new SingleViewHolder.ImageViewHolder(mInflater.inflate(R.layout.item_image,parent,false));
            case Constants.TEXT_TYPE:
                return new SingleViewHolder.TextViewHolder(mInflater.inflate(R.layout.item_text,parent,false));
            case Constants.UPPERTITLE_TYPE:
                return new SingleViewHolder.UpperTitleViewHolder(mInflater.inflate(R.layout.item_uppertitle,parent,false));
            case Constants.PUBLISHED:
                return new SingleViewHolder.PublishedViewHolder(mInflater.inflate(R.layout.item_published,parent,false));
            case Constants.INDICATOR:
                return new SingleViewHolder.IndicatorViewHolder(mInflater.inflate(R.layout.item_indicator,parent,false));
        }
        return new DummyViewHolder(new View(parent.getContext()));
    }


    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull BaseItemViewHolder holder, int position) {
        holder.bind(items.get(position));
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
        public void bind(Object object) {

        }
    }

    public void setItems(List<? extends BaseItem> items) {
        this.items = items;
    }
}
