package news.factory.com.UI.Single.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import news.factory.com.Model.Constants;
import news.factory.com.Model.DataModel.BaseItem;
import news.factory.com.R;

public class SingleRecyclerAdapter extends RecyclerView.Adapter<BaseItemViewHolder> {

    private List<? extends BaseItem> items;
    private LayoutInflater mInflater;

    public SingleRecyclerAdapter(List<? extends BaseItem> items, Context context) {
        this.items = items;
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
        }

        return null;
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
}
