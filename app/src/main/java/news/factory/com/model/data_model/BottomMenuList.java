package news.factory.com.model.data_model;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import news.factory.com.base.Constants;

public class BottomMenuList extends RealmObject {

    @PrimaryKey
    private int id;

    private RealmList<Menu> menuList;

    public BottomMenuList(){}

    public BottomMenuList(List<Menu> menuList) {
        this.id = Constants.BOTTOM_MENU_DB_TYPE;
        this.menuList = new RealmList<>();
        this.menuList.addAll(menuList);
    }

    public RealmList<Menu> getMenuList() {
        return menuList;
    }

    public int getId() {
        return id;
    }
}
