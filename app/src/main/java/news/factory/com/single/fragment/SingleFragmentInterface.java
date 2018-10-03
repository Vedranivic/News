package news.factory.com.single.fragment;

import android.content.Context;

import java.util.List;

import news.factory.com.model.data_model.BaseItem;

public interface SingleFragmentInterface {
    void displayArticleItems(List<BaseItem> items);
    String getArticleID();
    String getPage();
    Context getContext();
}
