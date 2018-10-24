package news.factory.com.view_holder.top_block;

import java.util.List;

import news.factory.com.model.data_model.News;

public class TopBlockDataClass {
    private List<News> articles;

    public TopBlockDataClass(List<News> articles) {
        this.articles = articles;
    }

    public List<News> getArticles() {
        return articles;
    }
}
