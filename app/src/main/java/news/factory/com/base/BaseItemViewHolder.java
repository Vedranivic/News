package news.factory.com.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseItemViewHolder extends RecyclerView.ViewHolder {

    public List<RecyclerItemsWrapper> items;

    public Object view;

    public BaseItemViewHolder(View itemView, List<RecyclerItemsWrapper> items, Object view) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.items = items;
        this.view = view;
    }

    public abstract void bind(int position);
}
