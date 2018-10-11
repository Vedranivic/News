package news.factory.com.model.interactor;

import news.factory.com.base.BaseInteractor;

public interface CategoryInteractor extends BaseInteractor {
    void makeCall(String category, String id, String page, InteractorListener listener);
}
