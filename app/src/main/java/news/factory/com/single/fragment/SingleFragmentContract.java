package news.factory.com.single.fragment;

import java.util.List;

import news.factory.com.base.RecyclerItemsWrapper;

public interface SingleFragmentContract {

    interface View{
        void displayArticleItems(List<RecyclerItemsWrapper> items);
    }

    interface Presenter{
        void getArticleItems();
        void initialize(String articleID, String page);
        void dispose();
    }
}
