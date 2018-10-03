package news.factory.com.single.fragment;

import android.content.Context;

import news.factory.com.model.data_model.News;

public interface SingleFragmentPresenterInterface {
    void getArticleItems();
    void setArticleItems(News news);
    Context getContext();
}
