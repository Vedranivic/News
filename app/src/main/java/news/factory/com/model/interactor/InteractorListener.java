package news.factory.com.model.interactor;

import news.factory.com.base.ResultWrapper;

public interface InteractorListener {
    void onSuccess(ResultWrapper result);
    void onFailure();
}
