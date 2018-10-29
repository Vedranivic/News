package news.factory.com.base.view_holder.category_title;

import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import news.factory.com.R;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.ObjectWrapper;
import news.factory.com.base.RecyclerItemsWrapper;

public class CategoryTitleViewHolder extends BaseItemViewHolder {

    @BindView(R.id.tvCategoryName)
    TextView tvCategoryName;
    @BindView(R.id.categoryUnderline)
    FrameLayout categoryUnderline;


    public CategoryTitleViewHolder(View itemView, List<RecyclerItemsWrapper> items, ObjectWrapper objectWrapper) {
        super(itemView, items, objectWrapper);
    }

    @Override
    public void bind(int position) {
        if(items.get(position).getViewType() == R.layout.item_category_title) {
            CategoryTitleDataClass item = (CategoryTitleDataClass) items.get(position).getItem();
            tvCategoryName.setText(item.getTitle());
            categoryUnderline.setBackgroundColor(Color.parseColor(item.getColor()));
        }
    }
}
