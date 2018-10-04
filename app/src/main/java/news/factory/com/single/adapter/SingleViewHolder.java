package news.factory.com.single.adapter;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.Constants;
import news.factory.com.model.data_model.Content;
import news.factory.com.model.data_model.FeaturedImage;
import news.factory.com.model.data_model.Image;
import news.factory.com.model.data_model.News;
import news.factory.com.R;

public class SingleViewHolder {

    public static class FeatureViewHolder extends BaseItemViewHolder<News> {

        private static final String sourceBeginningText = "Source: ";

        @BindView(R.id.ivFeature)
        ImageView ivFeatureImage;
        @BindView(R.id.tvCategory)
        TextView tvCategory;
        @BindView(R.id.tvSource)
        TextView tvSource;
        @BindView(R.id.tvCaption)
        TextView tvCaption;


        public FeatureViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void bind(News object) {
            tvCategory.setText(object.getCategory());
            tvSource.setText(sourceBeginningText + object.getFeatured_image_source());
            tvCaption.setText(object.getFeatured_image_caption());
            Picasso.get()
                    .load(Constants.IMAGE_BASE_URL+object.getFeatured_image().getOriginal())
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

    public static class UpperTitleViewHolder extends BaseItemViewHolder<News>{

        @BindView(R.id.tvUpperTitle)
        TextView tvUpperTitle;


        public UpperTitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void bind(News object) {
            tvUpperTitle.setText(object.getUppertitle());
        }
    }
}
