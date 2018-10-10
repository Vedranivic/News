package news.factory.com.model.interactor;

import news.factory.com.base.BaseInteractor;

public interface ArticleInteractor extends BaseInteractor {
    void makeCall(String articleID, String page, final InteractorListener listener);
}
