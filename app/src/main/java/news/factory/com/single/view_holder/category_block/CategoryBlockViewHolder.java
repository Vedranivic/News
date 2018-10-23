package news.factory.com.single.view_holder.category_block;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import news.factory.com.R;
import news.factory.com.adapter.RecyclerAdapter;
import news.factory.com.adapter.RecyclerAdapterImpl;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.ObjectWrapper;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.model.data_model.Category;
import news.factory.com.model.data_model.News;
import news.factory.com.single.view_holder.category_item.CategoryItemDataClass;

public class CategoryBlockViewHolder extends BaseItemViewHolder {

    @BindView(R.id.tvCategoryName)
    TextView tvCategoryName;
    @BindView(R.id.categoryUnderline)
    FrameLayout categoryUnderline;
    @BindView(R.id.rvCategoryBlockItems)
    RecyclerView rvCategoryBlockItems;
    @BindView(R.id.bShowMore)
    Button bShowMore;

    private RecyclerAdapter adapter;

    public CategoryBlockViewHolder(View itemView, List<RecyclerItemsWrapper> items, ObjectWrapper objectWrapper) {
        super(itemView, items, objectWrapper);
        adapter = (RecyclerAdapterImpl)objectWrapper.getRecyclerAdapter();
        setupAdapter();
    }

    private void setupAdapter() {
        rvCategoryBlockItems.setAdapter((RecyclerAdapterImpl)adapter);
    }

    @Override
    public void bind(int position) {
        Category category = (Category)items.get(position).getItem();
        tvCategoryName.setText(category.getTitle());
        categoryUnderline.setBackgroundColor(Color.parseColor(category.getCategory_color()));
        List<RecyclerItemsWrapper> items = new ArrayList<>();
        for(News article : category.getArticles()) {
            items.add(new RecyclerItemsWrapper(new CategoryItemDataClass(
                    article.getFeatured_image().getOriginal(),
                    article.getCategory(),
                    article.getTitle(),
                    article.getShares(),
                    article.getPublished_at_humans(),
                    article.getCategory_color()
                ), R.layout.item_news
            ));
        }
        adapter.setItems(items);
    }
}
