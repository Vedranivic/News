package news.factory.com.single.adapter;

import java.util.List;

import news.factory.com.base.RecyclerItemsWrapper;

public interface RecyclerAdapter {
    void setItems(List<RecyclerItemsWrapper> items);
}
