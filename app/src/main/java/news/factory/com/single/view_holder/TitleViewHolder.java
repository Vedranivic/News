package news.factory.com.single.view_holder;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.model.data_model.News;

public class TitleViewHolder extends BaseItemViewHolder {

    @BindView(R.id.tvTitle)
    TextView tvTitle;


    public TitleViewHolder(View itemView, List<RecyclerItemsWrapper> items){
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.items = items;
    }

    @Override
    public void bind(int position) {
        News news = (News) items.get(position).getItem();
        tvTitle.setText(news.getTitle());
    }
}
