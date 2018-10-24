package news.factory.com.view_holder.category_item;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import news.factory.com.single.activity.view.SingleActivity;
import news.factory.com.single.fragment_category.CategoryFragmentContract;
import news.factory.com.single.fragment_single.view.SingleFragment;

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
    @BindView(R.id.newsItem)
    RelativeLayout itemLayout;

    private String articleID;

    public CategoryItemViewHolder(View itemView, List<RecyclerItemsWrapper> items, ObjectWrapper objectWrapper) {
        super(itemView, items, objectWrapper);
    }

    @Override
    public void bind(int position) {
        if(items.get(position).getItem() instanceof CategoryItemDataClass) {
            CategoryItemDataClass article = (CategoryItemDataClass) items.get(position).getItem();
            articleID = article.getArticleID();
            tvCategory.setText(article.getCategory());
            tvCategory.getBackground().setColorFilter(Color.parseColor(article.getCategory_color()), PorterDuff.Mode.ADD);
            tvTitle.setText(article.getTitle());
            tvShares.setText(article.getShares());
            tvPublishedBefore.setText(article.getPublishedBefore());
            Picasso.get()
                    .load(Constants.IMAGE_BASE_URL + article.getImageOriginal())
                    .fit()
                    .centerCrop()
                    .into(this.ivThumbnail);
        }
    }

    @OnClick(R.id.newsItem)
    public void onClick(RelativeLayout relativeLayout){
        //if(objectWrapper.getView() instanceof CategoryFragmentContract.View){
            //((CategoryFragmentContract.View) objectWrapper.getView()).showToast(tvTitle.getText().toString()); }
        SingleActivity.openActivityInstance(relativeLayout.getContext(), articleID);
    }
}
