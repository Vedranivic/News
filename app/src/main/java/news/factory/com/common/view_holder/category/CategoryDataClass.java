package news.factory.com.common.view_holder.category;

public class CategoryDataClass {
    private String categoryID;
    private String page;

    public CategoryDataClass(String id, String page) {
        this.categoryID = id;
        this.page = page;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getPage() {
        return page;
    }
}
