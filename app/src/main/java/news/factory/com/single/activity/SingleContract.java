package news.factory.com.single.activity;

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
