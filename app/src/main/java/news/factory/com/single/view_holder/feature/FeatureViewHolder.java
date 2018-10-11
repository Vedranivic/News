package news.factory.com.single.view_holder.feature;

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


    public FeatureViewHolder(View itemView, List<RecyclerItemsWrapper> items){
        super(itemView, items);
    }

    @Override
    public void bind(int position) {
        FeatureDataClass feature = (FeatureDataClass) items.get(position).getItem();
        if(feature.getSource().equals("")){
            lbSource.setVisibility(View.INVISIBLE);
            tvSource.setVisibility(View.INVISIBLE);
        }
        if(feature.getCaption().equals("")){
            tvCaption.setVisibility(View.INVISIBLE);
        }
        tvCategory.setText(feature.getCategory());
        tvSource.setText(feature.getSource());
        tvCaption.setText(feature.getCaption());
        Picasso.get()
                .load(Constants.IMAGE_BASE_URL+feature.getOriginal())
                .into(this.ivFeatureImage);
    }
}
