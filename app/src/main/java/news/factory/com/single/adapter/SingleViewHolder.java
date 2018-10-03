package news.factory.com.single.adapter;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.model.Constants;
import news.factory.com.model.data_model.Content;
import news.factory.com.model.data_model.FeaturedImage;
import news.factory.com.model.data_model.Image;
import news.factory.com.model.data_model.News;
import news.factory.com.R;

public class SingleViewHolder {

    public static class FeatureViewHolder extends BaseItemViewHolder<FeaturedImage>{

        @BindView(R.id.ivFeature)
        ImageView ivFeatureImage;

        public FeatureViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void bind(FeaturedImage object) {
            Picasso.get()
                    .load(Constants.IMAGE_BASE_URL+object.getOriginal())
                    .into(this.ivFeatureImage);
        }


    }

    public static class TitleViewHolder extends BaseItemViewHolder<News>{

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        public TitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void bind(News object) {
            tvTitle.setText(object.getTitle());
        }
    }

    public static class TextViewHolder extends BaseItemViewHolder<Content>{

        @BindView(R.id.tvText)
        TextView tvText;

        public TextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void bind(Content object) {
            tvText.setText(Html.fromHtml(object.getData()));
        }
    }

    public static class ImageViewHolder extends BaseItemViewHolder<Image>{

        @BindView(R.id.ivImage)
        ImageView ivImage;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void bind(Image object) {
            Picasso.get()
                    .load(Constants.IMAGE_BASE_URL+object.getOriginal())
                    .into(this.ivImage);
        }
    }
}
