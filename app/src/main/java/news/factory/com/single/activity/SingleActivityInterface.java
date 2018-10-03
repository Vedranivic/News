package news.factory.com.single.activity;

import android.content.Context;

public interface SingleActivityInterface {
    void displayArticle(String articleID, int pages);
    String getArticleID();
    Context getContext();
}
