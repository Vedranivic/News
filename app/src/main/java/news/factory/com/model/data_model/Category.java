package news.factory.com.model.data_model;

import java.util.List;

public class Category {
    private String id;
    private String name;
    private String title;
    private String description;
    private String category_color;
    private String slug;
    private String url;
    private String icon;
    private String category_id;
    private String template;
    private String breadcrumbs;
    private String card_title;
    private String analytics;
    private String read_more_text;
    private String stranica;
    private String page;
    private List<News> articles;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory_color() {
        return category_color;
    }

    public String getSlug() {
        return slug;
    }

    public String getUrl() {
        return url;
    }

    public String getIcon() {
        return icon;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getTemplate() {
        return template;
    }

    public String getBreadcrumbs() {
        return breadcrumbs;
    }

    public String getCard_title() {
        return card_title;
    }

    public String getAnalytics() {
        return analytics;
    }

    public String getRead_more_text() {
        return read_more_text;
    }

    public String getStranica() {
        return stranica;
    }

    public String getPage() {
        return page;
    }

    public List<News> getArticles() {
        return articles;
    }
}
