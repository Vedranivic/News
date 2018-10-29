package news.factory.com.base.view_holder.button_block;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import news.factory.com.R;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.ObjectWrapper;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.home.activity.HomeContract;

public class ButtonBlockItemViewHolder extends BaseItemViewHolder {

    @BindView(R.id.bShowMore)
    Button bShowMore;

    private String categoryName;

    public ButtonBlockItemViewHolder(View itemView, List<RecyclerItemsWrapper> items, ObjectWrapper objectWrapper) {
        super(itemView, items, objectWrapper);
    }

    @Override
    public void bind(int position) {
        if(items.get(position).getViewType() == R.layout.item_button_block) {
            categoryName = (String) items.get(position).getItem();
        }
    }

    @OnClick(R.id.bShowMore)
    public void onClick(Button button){
        ((HomeContract.View) objectWrapper.getView()).showMore(categoryName);
    }
}
