package news.factory.com.home.activity;

import java.util.List;

import news.factory.com.model.data_model.Menu;

public interface HomeContract {

    interface View{
        void displayBottomMenu(List<Menu> result);

        void showMore(String categoryName);
    }

    interface Presenter{
        void getHomeItems();
        void getMenu();
        void getBottomMenu();
        void initialize();
        void dispose();
    }
}
