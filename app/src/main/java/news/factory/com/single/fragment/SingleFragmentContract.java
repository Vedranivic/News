package news.factory.com.single.fragment;

import java.util.List;

import news.factory.com.base.BaseItem;

public interface SingleFragmentContract {

    interface View{
        void displayArticleItems(List<BaseItem> items);
    }

    interface Presenter{
        void getArticleItems();
        void cancelCall();
        void initialize(String articleID, String page);
        void dispose();
    }
}
