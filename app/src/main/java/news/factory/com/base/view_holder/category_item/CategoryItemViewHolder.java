package news.factory.com.base.view_holder.category_item;

import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
    @Nullable
    @BindView(R.id.tvSubtitle)
    TextView tvSubtitle;
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
        CategoryItemDataClass article = (CategoryItemDataClass) items.get(position).getItem();
        articleID = article.getArticleID();
        tvCategory.setText(article.getCategory());
        tvCategory.getBackground().setColorFilter(Color.parseColor(article.getCategory_color()), PorterDuff.Mode.ADD);
        tvTitle.setText(article.getTitle());
        tvShares.setText(article.getShares());
        tvPublishedBefore.setText(article.getPublishedBefore());
        Glide.with(ivThumbnail.getContext())
                .load(Constants.IMAGE_BASE_URL + article.getImageOriginal())
                .into(this.ivThumbnail);

        if(items.get(position).getViewType() == R.layout.item_category_sort){
            ivThumbnail.setOutlineProvider(new ViewOutlineProvider() {
                    @Override
                    public void getOutline(View view, Outline outline) {
                        outline.setRoundRect(0, 0, ivThumbnail.getWidth(), ivThumbnail.getHeight() + 3, 3);
                    }
            });
            ivThumbnail.setClipToOutline(true);
            tvSubtitle.setText(article.getSubtitle());
        }
    }

    @OnClick(R.id.newsItem)
    public void onClick(RelativeLayout relativeLayout){
        SingleActivity.openActivityInstance(relativeLayout.getContext(), articleID);
    }
}
