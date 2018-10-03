package news.factory.com.single.activity;

import android.content.Context;

public interface SinglePresenterInterface {
    void getArticle();
    void setArticle(int pagesNumber);
    Context getContext();
}
