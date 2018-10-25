package news.factory.com.common.view_holder.category_block;

import java.util.List;

import news.factory.com.model.data_model.News;

public class CategoryBlockDataClass {

    private String categoryID;
    private String categoryName;
    private String categoryColor;
    private List<News> articles;

    public CategoryBlockDataClass(String categoryID, String categoryName, String categoryColor, List<News> articles) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.categoryColor = categoryColor;
        this.articles = articles;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryColor() {
        return categoryColor;
    }

    public List<News> getArticles() {
        return articles;
    }

    public String getCategoryID() {
        return categoryID;
    }
}
