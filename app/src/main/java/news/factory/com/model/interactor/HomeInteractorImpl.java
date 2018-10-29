package news.factory.com.model.interactor;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import news.factory.com.base.BaseInteractorImpl;
import news.factory.com.base.Constants;
import news.factory.com.base.ResultWrapper;
import news.factory.com.base.networking.NewsAPI;

public class HomeInteractorImpl extends BaseInteractorImpl implements HomeInteractor {

    @Inject
    public HomeInteractorImpl() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getMenu(InteractorListener listener) {
        getDisposable().add(newsAPI.getMenu()
                .map(menuItems -> new ResultWrapper(menuItems, Constants.MENU_TYPE))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver(listener))
        );
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getBottomMenu(InteractorListener listener) {
        getDisposable().add(newsAPI.getMenuBottom()
                .map(menuBottomItems -> new ResultWrapper(menuBottomItems, Constants.MENU_BOTTOM_TYPE))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver(listener))
        );
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getHomeItems(InteractorListener listener) {
        getDisposable().add(newsAPI.getIndex()
                .map(homeItems -> new ResultWrapper(homeItems, Constants.HOME_ITEMS_TYPE))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver(listener))
        );
    }
}
