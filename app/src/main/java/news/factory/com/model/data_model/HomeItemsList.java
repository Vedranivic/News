package news.factory.com.model.data_model;

import java.util.List;
import java.util.Random;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import news.factory.com.base.Constants;

public class HomeItemsList extends RealmObject {


    @PrimaryKey
    private int id;

    private RealmList<Category> itemList;

    public HomeItemsList(){}

    public HomeItemsList(List<Category> itemList) {
        this.id = Constants.HOME_ITEMS_DB_TYPE;
        this.itemList = new RealmList<>();
        this.itemList.addAll(itemList);
    }

    public RealmList<Category> getItemList() {
        return itemList;
    }

    public int getId() {
        return id;
    }
}
