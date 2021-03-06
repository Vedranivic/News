package news.factory.com.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseItemViewHolder extends RecyclerView.ViewHolder {

    public List<RecyclerItemsWrapper> items;

    public View itemView;

    public ObjectWrapper objectWrapper;

    public BaseItemViewHolder(View itemView, List<RecyclerItemsWrapper> items, ObjectWrapper objectWrapper) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.items = items;
        this.itemView = itemView;
        this.objectWrapper = objectWrapper;
    }

    public abstract void bind(int position);
}
