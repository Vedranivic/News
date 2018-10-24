package news.factory.com.view_holder.indicator;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.ObjectWrapper;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.model.data_model.News;

public class IndicatorViewHolder extends BaseItemViewHolder {

    @BindView(R.id.tvCurrentPage)
    TextView tvCurrentPage;
    @BindView(R.id.tvTotalPages)
    TextView tvTotalPages;


    public IndicatorViewHolder(View itemView, List<RecyclerItemsWrapper> items, ObjectWrapper objectWrapper) {
        super(itemView, items, objectWrapper);
    }

    @Override
    public void bind(int position) {
        if (items.get(position).getItem() instanceof IndicatorDataClass) {
            IndicatorDataClass indicator = (IndicatorDataClass) items.get(position).getItem();
            tvTotalPages.setText(indicator.getTotalPages());
            tvCurrentPage.setText(indicator.getPage());
        }
    }
}