package news.factory.com.view_holder.title;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import news.factory.com.R;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.ObjectWrapper;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.model.data_model.News;

public class TitleViewHolder extends BaseItemViewHolder {

    @BindView(R.id.tvTitle)
    TextView tvTitle;


    public TitleViewHolder(View itemView, List<RecyclerItemsWrapper> items, ObjectWrapper objectWrapper) {
        super(itemView, items, objectWrapper);
    }

    @Override
    public void bind(int position) {
        if (items.get(position).getItem() instanceof TitleDataClass) {
            TitleDataClass title = (TitleDataClass) items.get(position).getItem();
            tvTitle.setText(title.getTitle());
        }
    }
}
