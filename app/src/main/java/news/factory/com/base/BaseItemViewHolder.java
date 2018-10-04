package news.factory.com.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseItemViewHolder<T> extends RecyclerView.ViewHolder {
    public BaseItemViewHolder(View itemView) {
        super(itemView);
    }
    public abstract void bind(T object);
}
