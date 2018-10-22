package news.factory.com.model.interactor;

import news.factory.com.base.BaseInteractor;

public interface HomeInteractor extends BaseInteractor {

    void getMenu(InteractorListener listener);
    void getBottomMenu(InteractorListener listener);
    void getHomeItems(InteractorListener listener);
}
