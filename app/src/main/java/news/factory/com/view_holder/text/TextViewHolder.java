package news.factory.com.view_holder.text;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.BaseItemViewHolder;
import news.factory.com.base.ObjectWrapper;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.model.data_model.Content;

public class TextViewHolder extends BaseItemViewHolder {

    @BindView(R.id.tvText)
    TextView tvText;


    public TextViewHolder(View itemView, List<RecyclerItemsWrapper> items, ObjectWrapper objectWrapper) {
        super(itemView, items, objectWrapper);
    }

    @Override
    public void bind(int position) {
        if (items.get(position).getItem() instanceof TextDataClass) {
            TextDataClass text = (TextDataClass) items.get(position).getItem();
            tvText.setText(Html.fromHtml(text.getTextData()));
        }
    }
}
