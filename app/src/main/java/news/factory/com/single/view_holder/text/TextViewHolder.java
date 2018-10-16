package news.factory.com.single.view_holder.text;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.model.data_model.Content;

public class TextViewHolder extends BaseItemViewHolder {

    @BindView(R.id.tvText)
    TextView tvText;


    public TextViewHolder(View itemView, List<RecyclerItemsWrapper> items){
        super(itemView, items);
    }

    @Override
    public void bind(int position) {
        TextDataClass text = (TextDataClass) items.get(position).getItem();
        tvText.setText(Html.fromHtml(text.getTextData()));
    }
}