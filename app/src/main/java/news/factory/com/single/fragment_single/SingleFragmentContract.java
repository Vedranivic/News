package news.factory.com.single.fragment_single;

import java.util.List;

import news.factory.com.base.RecyclerItemsWrapper;

public interface SingleFragmentContract {

    interface View{
        void displayArticleItems(List<RecyclerItemsWrapper> items);
        void showToast(String message);
    }

    interface Presenter{
        void getArticleItems();
        void initialize(String articleID, String page);
        void dispose();
    }
}
