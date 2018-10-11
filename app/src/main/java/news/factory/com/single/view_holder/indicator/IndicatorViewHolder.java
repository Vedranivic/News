package news.factory.com.single.view_holder.indicator;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.model.data_model.News;

public class IndicatorViewHolder extends BaseItemViewHolder {

    @BindView(R.id.tvCurrentPage)
    TextView tvCurrentPage;
    @BindView(R.id.tvTotalPages)
    TextView tvTotalPages;


    public IndicatorViewHolder(View itemView, List<RecyclerItemsWrapper> items){
        super(itemView, items);
    }

    @Override
    public void bind(int position) {
        IndicatorDataClass indicator = (IndicatorDataClass) items.get(position).getItem();
        tvTotalPages.setText(indicator.getTotalPages());
        tvCurrentPage.setText(indicator.getPage());
    }
}