package news.factory.com.single.view_holder.category_item;

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
import news.factory.com.single.category_fragment.CategoryFragmentContract;
import news.factory.com.single.category_fragment.presenter.CategoryFragmentPresenter;
import news.factory.com.single.category_fragment.view.CategoryFragment;

public class CategoryItemViewHolder extends BaseItemViewHolder {

    @BindView(R.id.ivThumbnail)
    ImageView ivThumbnail;
    @BindView(R.id.tvCategory)
    TextView tvCategory;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvShares)
    TextView tvShares;

    public CategoryItemViewHolder(View itemView, List<RecyclerItemsWrapper> items, Object view) {
        super(itemView, items, view);
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

    @OnClick(R.id.tvTitle)
    public void onClick(TextView textView){
        if(view instanceof CategoryFragmentContract.View){
            ((CategoryFragmentContract.View) view).showToast(tvTitle.getText().toString());
        }
    }
}
