package news.factory.com.common.view_holder.category_block;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import news.factory.com.R;
import news.factory.com.common.adapter.RecyclerAdapterImpl;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.ObjectWrapper;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.home.activity.HomeContract;
import news.factory.com.model.data_model.News;
import news.factory.com.common.view_holder.category_item.CategoryItemDataClass;

public class CategoryBlockViewHolder extends BaseItemViewHolder {

    @BindView(R.id.tvCategoryName)
    TextView tvCategoryName;
    @BindView(R.id.categoryUnderline)
    FrameLayout categoryUnderline;
    @BindView(R.id.rvCategoryBlockItems)
    RecyclerView rvCategoryBlockItems;
    @BindView(R.id.bShowMore)
    Button bShowMore;

    private RecyclerAdapterImpl adapter;
    private String categoryName;

    public CategoryBlockViewHolder(View itemView, List<RecyclerItemsWrapper> items, ObjectWrapper objectWrapper) {
        super(itemView, items, objectWrapper);
        adapter = new RecyclerAdapterImpl(objectWrapper);
        setupRecycler(new LinearLayoutManager(itemView.getContext()));
    }

    private void setupRecycler(LinearLayoutManager linearLayoutManager) {
        rvCategoryBlockItems.setLayoutManager(linearLayoutManager);
        rvCategoryBlockItems.setAdapter(adapter);
    }

    @Override
    public void bind(int position) {
        CategoryBlockDataClass categoryBlock = (CategoryBlockDataClass)items.get(position).getItem();
        tvCategoryName.setText(categoryBlock.getCategoryName());
        categoryUnderline.setBackgroundColor(Color.parseColor(categoryBlock.getCategoryColor()));
        List<RecyclerItemsWrapper> items = new ArrayList<>();
        for(News article : categoryBlock.getArticles()) {
            items.add(new RecyclerItemsWrapper(new CategoryItemDataClass(
                    article.getFeatured_image().getOriginal(),
                    article.getCategory(),
                    article.getTitle(),
                    article.getShares(),
                    article.getPublished_at_humans(),
                    categoryBlock.getCategoryColor(),
                    article.getId()
                ), R.layout.item_news
            ));
        }
        categoryName = categoryBlock.getCategoryName();
        adapter.setItems(items);
    }

    @OnClick(R.id.bShowMore)
    public void onClick(Button button){
        ((HomeContract.View) objectWrapper.getView()).showMore(categoryName);
    }
}
