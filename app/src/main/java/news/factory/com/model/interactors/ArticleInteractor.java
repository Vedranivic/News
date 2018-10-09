package news.factory.com.model.interactors;

import news.factory.com.base.BaseInteractor;

public interface ArticleInteractor extends BaseInteractor {
    void makeCall(String articleID, String page, final InteractorListener listener);
}
