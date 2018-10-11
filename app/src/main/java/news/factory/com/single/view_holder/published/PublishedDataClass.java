package news.factory.com.single.view_holder.published;

public class PublishedDataClass {
    private String published;
    private String author;
    private String shares;

    public PublishedDataClass(String published, String author, String shares) {
        this.published = published;
        this.author = author;
        this.shares = shares;
    }

    public String getPublished() {
        return published;
    }

    public String getAuthor() {
        return author;
    }

    public String getShares() {
        return shares;
    }
}
