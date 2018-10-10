package news.factory.com.single.view_holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.Constants;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.model.data_model.News;

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
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.items = items;
    }

    @Override
    public void bind(int position) {
        News news = (News) items.get(position).getItem();
        if(news.getFeatured_image_source().equals("")){
            lbSource.setVisibility(View.INVISIBLE);
            tvSource.setVisibility(View.INVISIBLE);
        }
        if(news.getFeatured_image_caption().equals("")){
            tvCaption.setVisibility(View.INVISIBLE);
        }
        tvCategory.setText(news.getCategory());
        tvSource.setText(news.getFeatured_image_source());
        tvCaption.setText(news.getFeatured_image_caption());
        Picasso.get()
                .load(Constants.IMAGE_BASE_URL+news.getFeatured_image().getOriginal())
                .into(this.ivFeatureImage);
    }
}
