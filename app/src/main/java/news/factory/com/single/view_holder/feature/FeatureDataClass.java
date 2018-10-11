package news.factory.com.single.view_holder.feature;

public class FeatureDataClass {
    private String category;
    private String source;
    private String caption;
    private String original;

    public FeatureDataClass(String category, String source, String caption, String original) {
        this.category = category;
        this.source = source;
        this.caption = caption;
        this.original = original;
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
}
