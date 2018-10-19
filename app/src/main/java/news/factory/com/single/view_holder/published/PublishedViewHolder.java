package news.factory.com.single.view_holder.published;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import news.factory.com.R;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.ObjectWrapper;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.model.data_model.News;

public class PublishedViewHolder extends BaseItemViewHolder {

    @BindView(R.id.tvPublished)
    TextView tvPublished;
    @BindView(R.id.tvAuthor)
    TextView tvAuthor;
    @BindView(R.id.tvShares)
    TextView tvShares;


    public PublishedViewHolder(View itemView, List<RecyclerItemsWrapper> items, ObjectWrapper objectWrapper) {
        super(itemView, items, objectWrapper);
    }

    @Override
    public void bind(int position) {
        if (items.get(position).getItem() instanceof PublishedDataClass) {
            PublishedDataClass published = (PublishedDataClass) items.get(position).getItem();
            tvPublished.setText(published.getPublished().split(" ")[0].concat("."));
            tvAuthor.setText(published.getAuthor());
            tvShares.setText(published.getShares());
        }
    }
}
