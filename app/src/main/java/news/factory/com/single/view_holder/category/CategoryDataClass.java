package news.factory.com.single.view_holder.category;

public class CategoryDataClass {
    private String id;
    private String page;

    public CategoryDataClass(String id, String page) {
        this.id = id;
        this.page = page;
    }

    public String getId() {
        return id;
    }

    public String getPage() {
        return page;
    }
}
