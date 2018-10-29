package news.factory.com.base.view_holder.button;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import news.factory.com.R;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.ObjectWrapper;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.home.fragment_category_sort.HomeSortCategoryFragmentContract;

public class ButtonItemViewHolder extends BaseItemViewHolder {

    @BindView(R.id.bLoadMore)
    Button bLoadMore;

    public ButtonItemViewHolder(View itemView, List<RecyclerItemsWrapper> items, ObjectWrapper objectWrapper) {
        super(itemView, items, objectWrapper);
    }

    @Override
    public void bind(int position) {
        if(items.get(position).getViewType() == R.layout.item_button) {
            bLoadMore.setTextColor(Color.parseColor((String) items.get(position).getItem()));
        }
    }

    @OnClick(R.id.bLoadMore)
    public void onClick(Button button){
        ((HomeSortCategoryFragmentContract.View) objectWrapper.getView()).loadMore();
    }
}
