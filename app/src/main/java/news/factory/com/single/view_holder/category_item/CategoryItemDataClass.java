package news.factory.com.single.view_holder.category_item;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CategoryItemDataClass {
    private String imageOriginal;
    private String category;
    private String title;
    private String shares;
    private String publishedDate;

    public CategoryItemDataClass(String imageOriginal, String category, String title, String shares, String publishedDate) {
        this.imageOriginal = imageOriginal;
        this.category = category;
        this.title = title;
        this.shares = shares;
        this.publishedDate = publishedDate;
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
            return "";
        }
    }
}
