package news.factory.com.model;

public interface ArticleInteractor {
    void makeCall(String articleID, String page, final ArticleListener listener);
    void cancelCall();
}
