package news.factory.com.model.interactors;

import news.factory.com.base.BaseResult;

public interface InteractorListener {
    void onSuccess(BaseResult result);
    void onFailure();
}
