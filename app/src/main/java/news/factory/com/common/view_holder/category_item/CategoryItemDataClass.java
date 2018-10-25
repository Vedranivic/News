package news.factory.com.common.view_holder.category_item;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CategoryItemDataClass {
    private String imageOriginal;
    private String category;
    private String title;
    private String shares;
    private String publishedDate;
    private String category_color;
    private String articleID;

    public CategoryItemDataClass(String imageOriginal, String category, String title, String shares, String publishedDate, String category_color, String articleID) {
        this.imageOriginal = imageOriginal;
        this.category = category;
        this.title = title;
        this.shares = shares;
        this.publishedDate = publishedDate;
        this.category_color = category_color;
        this.articleID = articleID;
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

    public String getPublishedBefore() {
        int days;
        Long currentTime = System.currentTimeMillis();
        Date published;
        try {
            published = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(publishedDate);
            days = (int)((currentTime - published.getTime())/(24*60*60*1000));
            String ending = " dana";
            if(days % 10 == 1 && days != 11){
                ending = " dan";
            }
            return "Prije: " + String.valueOf(days) + ending;
        } catch (ParseException e) {
            e.printStackTrace();
            return publishedDate.split(" ")[0].concat(".");
        }
    }

    public String getCategory_color() {
        return category_color;
    }

    public String getArticleID() {
        return articleID;
    }
}
