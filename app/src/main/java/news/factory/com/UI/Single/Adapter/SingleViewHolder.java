package news.factory.com.UI.Single.Adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.Model.Constants;
import news.factory.com.Model.DataModel.Content;
import news.factory.com.Model.DataModel.FeaturedImage;
import news.factory.com.Model.DataModel.Image;
import news.factory.com.Model.DataModel.News;
import news.factory.com.R;

public class SingleViewHolder {

    public static class FeatureViewHolder extends BaseItemViewHolder<FeaturedImage>{

        @BindView(R.id.ivFeature)
        ImageView ivFeatureImage;

        public FeatureViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            ivFeatureImage = (ImageView) itemView.findViewById(R.id.ivFeature);
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
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);

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
            tvText = (TextView) itemView.findViewById(R.id.tvText);

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
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);

        }

        @Override
        public void bind(Image object) {
            Picasso.get()
                    .load(Constants.IMAGE_BASE_URL+object.getOriginal())
                    .into(this.ivImage);
        }
    }
}
