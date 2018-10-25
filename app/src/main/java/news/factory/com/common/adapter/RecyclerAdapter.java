package news.factory.com.common.adapter;

import java.util.List;

import news.factory.com.base.RecyclerItemsWrapper;

public interface RecyclerAdapter {
    void setItems(List<RecyclerItemsWrapper> items);
}
