package news.factory.com.single.view_holder.uppertitle;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import news.factory.com.R;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.RecyclerItemsWrapper;


public class UpperTitleViewHolder extends BaseItemViewHolder {

    @BindView(R.id.tvUpperTitle)
    TextView tvUpperTitle;


    public UpperTitleViewHolder(View itemView, List<RecyclerItemsWrapper> items){
        super(itemView, items);
    }

    @Override
    public void bind(int position) {
        UppertitleDataClass uppertitle = (UppertitleDataClass) items.get(position).getItem();
        tvUpperTitle.setText(uppertitle.getUpperTitle());
    }
}
