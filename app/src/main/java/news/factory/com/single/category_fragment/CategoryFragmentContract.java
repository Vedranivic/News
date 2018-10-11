package news.factory.com.single.category_fragment;

import java.util.List;

import news.factory.com.base.RecyclerItemsWrapper;

public interface CategoryFragmentContract {

    interface View{
        void displayItemsByCategory(List<RecyclerItemsWrapper> items);
    }

    interface Presenter{
        void getItemsByCategory(String category);
        void initialize(String id, String page);
        void dispose();
    }
}
