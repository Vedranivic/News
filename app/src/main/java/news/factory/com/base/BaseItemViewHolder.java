package news.factory.com.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public abstract class BaseItemViewHolder extends RecyclerView.ViewHolder {

    public List<RecyclerItemsWrapper> items;

    public BaseItemViewHolder(View itemView) {
        super(itemView);
    }
    public abstract void bind(int position);
}
