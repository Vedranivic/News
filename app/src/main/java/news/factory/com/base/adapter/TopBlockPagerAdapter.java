package news.factory.com.base.adapter;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import news.factory.com.R;
import news.factory.com.base.Constants;
import news.factory.com.model.data_model.News;
import news.factory.com.single.activity.view.SingleActivity;

public class TopBlockPagerAdapter extends PagerAdapter {

    private List<News> sliderItems;

    @BindView(R.id.ivTopBlockFeature)
    ImageView ivTopBlockFeature;
    @BindView(R.id.tvTobBlockCategory)
    TextView tvTobBlockCategory;
    @BindView(R.id.tvTopBlockTitle)
    TextView tvTopBlockTitle;
    @BindView(R.id.topBlockArticle)
    ConstraintLayout topBlockArticle;

    private String articleID;

    @Inject
    public TopBlockPagerAdapter() {
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ViewGroup layout = (ViewGroup) LayoutInflater.from(container.getContext()).inflate(R.layout.item_top_block_content, container,false);
        ButterKnife.bind(this,layout);
        Glide.with(ivTopBlockFeature.getContext())
                .load(Constants.IMAGE_BASE_URL + sliderItems.get(position).getFeatured_image().getOriginal())
                .into(ivTopBlockFeature);
        tvTopBlockTitle.setText(sliderItems.get(position).getTitle());
        tvTobBlockCategory.setText(sliderItems.get(position).getCategory());
        tvTobBlockCategory.getBackground().setColorFilter(Color.parseColor(sliderItems.get(position).getCategory_color()), PorterDuff.Mode.ADD);
        container.addView(layout);
        if(position>0) {
            articleID = sliderItems.get(position - 1).getId();
        }
        else{
            articleID = sliderItems.get(position).getId();
        }
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
        sliderItems = new ArrayList<>();
        sliderItems.add(items.get(items.size()-1));
        sliderItems.addAll(items);
        sliderItems.add(items.get(0));
        notifyDataSetChanged();
    }

    @OnClick(R.id.topBlockArticle)
    public void onClick(ConstraintLayout layout){
        SingleActivity.openActivityInstance(layout.getContext(), articleID);
    }
}
