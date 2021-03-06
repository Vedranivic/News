package news.factory.com.base.view_holder.feature;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import news.factory.com.single.fragment_single.SingleFragmentContract;

public class FeatureViewHolder extends BaseItemViewHolder {


    @BindView(R.id.ivFeature)
    ImageView ivFeatureImage;
    @BindView(R.id.tvCategory)
    TextView tvCategory;
    @BindView(R.id.tvSource)
    TextView tvSource;
    @BindView(R.id.tvCaption)
    TextView tvCaption;
    @BindView(R.id.lbSource)
    TextView lbSource;
    @BindView(R.id.ivBookmark)
    ImageView ivBookmark;

    public FeatureViewHolder(View itemView, List<RecyclerItemsWrapper> items, ObjectWrapper objectWrapper) {
        super(itemView, items, objectWrapper);
    }

    @Override
    public void bind(int position) {
        if(items.get(position).getViewType() == R.layout.item_feature_image) {
            FeatureDataClass feature = (FeatureDataClass) items.get(position).getItem();
            if(feature.getOriginal().equals("")){
                ivFeatureImage.setVisibility(View.GONE);
                lbSource.setTextColor(Color.BLACK);
                tvSource.setTextColor(Color.BLACK);
                tvCaption.setTextColor(Color.BLACK);
                ivBookmark.getDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
            }
            if (feature.getSource().equals("")) {
                lbSource.setVisibility(View.GONE);
                tvSource.setVisibility(View.GONE);
            }
            if (feature.getCaption().equals("")) {
                tvCaption.setVisibility(View.GONE);
            }
            tvCategory.setText(feature.getCategory());
            tvCategory.getBackground().setColorFilter(Color.parseColor(feature.getCategory_color()), PorterDuff.Mode.ADD);
            tvSource.setText(feature.getSource());
            tvCaption.setText(feature.getCaption());
            Glide.with(ivFeatureImage.getContext())
                    .load(Constants.IMAGE_BASE_URL + feature.getOriginal())
                    .into(ivFeatureImage);
        }
    }

    @OnClick(R.id.ivBookmark)
    public void onClick(ImageView imageView){
        if(objectWrapper.getView() instanceof SingleFragmentContract.View){
            ((SingleFragmentContract.View) objectWrapper.getView()).showToast("Article added to saved articles");
        }
    }
}
