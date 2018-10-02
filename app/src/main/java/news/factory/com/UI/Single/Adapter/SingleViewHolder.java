package news.factory.com.UI.Single.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;

public class SingleViewHolder {

    public static class FeatureViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.ivFeature)
        ImageView ivFeatureImage;

        public FeatureViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        public TitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tvText)
        TextView tvTitle;

        public TextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.ivImage)
        ImageView ivFeatureImage;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
