package news.factory.com.single.activity;


import android.support.v4.app.FragmentManager;

public interface SingleContract {

    interface View{
        void displayArticle(String articleID, int pages);
    }

    interface Presenter{
        void getArticle();
        void initialize(String articleID);
        void dispose();
    }
}
