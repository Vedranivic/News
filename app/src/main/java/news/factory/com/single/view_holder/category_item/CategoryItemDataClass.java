package news.factory.com.single.view_holder.category_item;

public class CategoryItemDataClass {
    private String imageOriginal;
    private String category;
    private String title;
    private String shares;

    public CategoryItemDataClass(String imageOriginal, String category, String title, String shares) {
        this.imageOriginal = imageOriginal;
        this.category = category;
        this.title = title;
        this.shares = shares;
    }

    public String getImageOriginal() {
        return imageOriginal;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getShares() {
        return shares;
    }
}
