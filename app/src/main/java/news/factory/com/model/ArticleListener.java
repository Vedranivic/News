package news.factory.com.model;

import news.factory.com.model.data_model.News;

public interface ArticleListener {
    void onSuccess(News news);
    void onFailure();
}
