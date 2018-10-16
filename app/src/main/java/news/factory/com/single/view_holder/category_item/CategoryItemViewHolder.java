package news.factory.com.single.view_holder.category_item;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import news.factory.com.R;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.Constants;
import news.factory.com.base.RecyclerItemsWrapper;

public class CategoryItemViewHolder extends BaseItemViewHolder {

    @BindView(R.id.ivThumbnail)
    ImageView ivThumbnail;
    @BindView(R.id.tvCategory)
    TextView tvCategory;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvShares)
    TextView tvShares;

    public CategoryItemViewHolder(View itemView, List<RecyclerItemsWrapper> items) {
        super(itemView, items);
    }

    @Override
    public void bind(int position) {
        CategoryItemDataClass categoryItem = (CategoryItemDataClass) items.get(position).getItem();
        tvCategory.setText(categoryItem.getCategory());
        tvTitle.setText(categoryItem.getTitle());
        tvShares.setText(categoryItem.getShares());
        Picasso.get()
                .load(Constants.IMAGE_BASE_URL+categoryItem.getImageOriginal())
                .fit()
                .centerCrop()
                .into(this.ivThumbnail);
    }
}