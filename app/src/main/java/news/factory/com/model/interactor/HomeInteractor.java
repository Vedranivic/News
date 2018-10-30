package news.factory.com.model.interactor;

import java.util.List;

import news.factory.com.base.BaseInteractor;
import news.factory.com.model.data_model.Menu;

public interface HomeInteractor extends BaseInteractor {

    void getMenu(InteractorListener listener);
    void getBottomMenu(InteractorListener listener);
    void getHomeItems(InteractorListener listener);
    void writeToDatabase(List<Menu> menuList);
    void loadFromDatabase();
}
