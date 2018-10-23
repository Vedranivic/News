package news.factory.com.single.view_holder.category_item;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


import butterknife.BindView;
import butterknife.OnClick;
import news.factory.com.R;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.Constants;
import news.factory.com.base.ObjectWrapper;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.single.fragment_category.CategoryFragmentContract;

public class CategoryItemViewHolder extends BaseItemViewHolder {

    @BindView(R.id.ivThumbnail)
    ImageView ivThumbnail;
    @BindView(R.id.tvCategory)
    TextView tvCategory;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvShares)
    TextView tvShares;
    @BindView(R.id.tvPublishedBefore)
    TextView tvPublishedBefore;

    public CategoryItemViewHolder(View itemView, List<RecyclerItemsWrapper> items, ObjectWrapper objectWrapper) {
        super(itemView, items, objectWrapper);
    }

    @Override
    public void bind(int position) {
        if(items.get(position).getItem() instanceof CategoryItemDataClass) {
            CategoryItemDataClass categoryItem = (CategoryItemDataClass) items.get(position).getItem();
            tvCategory.setText(categoryItem.getCategory());
            tvCategory.getBackground().setColorFilter(Color.parseColor(categoryItem.getCategory_color()), PorterDuff.Mode.ADD);
            tvTitle.setText(categoryItem.getTitle());
            tvShares.setText(categoryItem.getShares());
            tvPublishedBefore.setText(categoryItem.getPublishedBefore());
            Picasso.get()
                    .load(Constants.IMAGE_BASE_URL + categoryItem.getImageOriginal())
                    .fit()
                    .centerCrop()
                    .into(this.ivThumbnail);
        }
    }

    @OnClick(R.id.tvTitle)
    public void onClick(TextView textView){
        if(objectWrapper.getView() instanceof CategoryFragmentContract.View){
            ((CategoryFragmentContract.View) objectWrapper.getView()).showToast(tvTitle.getText().toString());
        }
    }
}
