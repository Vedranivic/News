package news.factory.com.common.view_holder.feature;

public class FeatureDataClass {
    private String category;
    private String source;
    private String caption;
    private String original;
    private String category_color;

    public FeatureDataClass(String category, String source, String caption, String original, String category_color) {
        this.category = category;
        this.source = source;
        this.caption = caption;
        this.original = original;
        this.category_color = category_color;
    }

    public String getCategory() {
        return category;
    }

    public String getSource() {
        return source;
    }

    public String getCaption() {
        return caption;
    }

    public String getOriginal() {
        return original;
    }

    public String getCategory_color() {
        return category_color;
    }
}
