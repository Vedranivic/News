package news.factory.com.model.data_model;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Menu extends RealmObject {

    private String id;
    private String url;
    private String title;
    private String color;
    private String type;
    private int priority;
    private RealmList<Menu> submenu_items;
    private String icon;
    private String category_id;
    private String parent_id;
    private String main_category_id;

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public int getPriority() {
        return priority;
    }

    public List<Menu> getSubmenu_items() {
        return submenu_items;
    }

    public String getIcon() {
        return icon;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public String getMain_category_id() {
        return main_category_id;
    }
}
