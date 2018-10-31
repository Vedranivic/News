package news.factory.com.model.interactor;

import java.util.List;

import news.factory.com.base.BaseInteractor;
import news.factory.com.base.ResultWrapper;
import news.factory.com.home.activity.presenter.HomePresenter;
import news.factory.com.home.fragment_home.presenter.HomeFragmentPresenter;
import news.factory.com.model.data_model.Menu;
import news.factory.com.model.data_model.MenuList;

public interface HomeInteractor extends BaseInteractor {

    void getMenu(InteractorListener listener);
    void getBottomMenu(InteractorListener listener);
    void getHomeItems(InteractorListener listener);
    void writeToDatabase(ResultWrapper result);
    void getMenuFromDatabase(InteractorListener listener);
    void getBottomMenuFromDatabase(InteractorListener listener);
    void getHomeItemsFromDataBase(InteractorListener listener);
}
