package news.factory.com.home.fragment_home;

import java.util.List;

import news.factory.com.base.RecyclerItemsWrapper;

public interface HomeFragmentContract {

    interface View{
        void displayHomeItems(List<RecyclerItemsWrapper> items);
    }

    interface Presenter{
        void initialize();
        void getHomeItems();
    }
}
