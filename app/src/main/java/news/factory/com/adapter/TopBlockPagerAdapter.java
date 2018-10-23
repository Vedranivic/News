package news.factory.com.adapter;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import news.factory.com.R;
import news.factory.com.base.Constants;
import news.factory.com.model.data_model.News;

public class TopBlockPagerAdapter extends PagerAdapter {

    private List<News> sliderItems;

    @Inject
    public TopBlockPagerAdapter() {
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ViewGroup layout = (ViewGroup) LayoutInflater.from(container.getContext()).inflate(R.layout.item_top_block_content, container,false);
        Picasso.get()
                .load(Constants.IMAGE_BASE_URL + sliderItems.get(position).getFeatured_image().getOriginal())
                .into(((ImageView) layout.findViewById(R.id.ivTopBlockFeature)));
        ((TextView) layout.findViewById(R.id.tvTopBlockTitle)).setText(sliderItems.get(position).getTitle());
        TextView category = ((TextView) layout.findViewById(R.id.tvTobBlockCategory));
        category.setText(sliderItems.get(position).getCategory());
        category.getBackground().setColorFilter(Color.parseColor(sliderItems.get(position).getCategory_color()), PorterDuff.Mode.ADD);
        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ViewGroup) object);
    }

    @Override
    public int getCount() {
        if(sliderItems!=null) {
            return sliderItems.size();
        }
        else
            return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public void setItems(List<News> items) {
        sliderItems = items;
        notifyDataSetChanged();
    }
}
