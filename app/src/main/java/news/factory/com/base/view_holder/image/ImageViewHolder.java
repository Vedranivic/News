package news.factory.com.base.view_holder.image;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import news.factory.com.R;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.Constants;
import news.factory.com.base.ObjectWrapper;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.model.data_model.Image;

public class ImageViewHolder extends BaseItemViewHolder {

    @BindView(R.id.ivImage)
    ImageView ivImage;


    public ImageViewHolder(View itemView, List<RecyclerItemsWrapper> items, ObjectWrapper objectWrapper) {
        super(itemView, items, objectWrapper);
    }

    @Override
    public void bind(int position) {
        if (items.get(position).getViewType() == R.layout.item_image) {
            ImageDataClass image = (ImageDataClass) items.get(position).getItem();
            Glide.with(ivImage.getContext())
                    .load(Constants.IMAGE_BASE_URL + image.getOriginal())
                    .into(this.ivImage);
        }
    }
}
