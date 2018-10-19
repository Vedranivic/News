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


    public UpperTitleViewHolder(View itemView, List<RecyclerItemsWrapper> items, Object view){
        super(itemView, items, view);
    }

    @Override
    public void bind(int position) {
        if (items.get(position).getItem() instanceof UppertitleDataClass) {
            UppertitleDataClass uppertitle = (UppertitleDataClass) items.get(position).getItem();
            tvUpperTitle.setText(uppertitle.getUpperTitle());
        }
    }
}
