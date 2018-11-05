package news.factory.com.model.interactor;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import news.factory.com.base.BaseInteractorImpl;
import news.factory.com.base.Constants;
import news.factory.com.base.ResultWrapper;
import news.factory.com.model.data_model.BottomMenuList;
import news.factory.com.model.data_model.Category;
import news.factory.com.model.data_model.HomeItemsList;
import news.factory.com.model.data_model.Menu;
import news.factory.com.model.data_model.MenuList;

public class HomeInteractorImpl extends BaseInteractorImpl implements HomeInteractor {

    @Inject
    public HomeInteractorImpl() {}

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
                .map(menuBottomItems -> new ResultWrapper(menuBottomItems, Constants.BOTTOM_MENU_TYPE))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver(listener))
        );
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getHomeItems(InteractorListener listener) {
        getDisposable().add(newsAPI.getIndex()
                .map(homeItems -> {
                    for(Category c : homeItems) c.setCompoundID();
                    return new ResultWrapper(homeItems, Constants.HOME_ITEMS_TYPE);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver(listener))
        );
    }

    @SuppressWarnings("unchecked")
    @Override
    public void writeToDatabase(ResultWrapper result) {
        switch (result.getType()) {
            case Constants.MENU_TYPE:
                realm.executeTransactionAsync(
                        realm -> realm.insertOrUpdate(new MenuList((List<Menu>)result.getResult()))
                );
                break;
            case Constants.BOTTOM_MENU_TYPE:
                realm.executeTransactionAsync(
                        realm -> realm.insertOrUpdate(new BottomMenuList((List<Menu>)result.getResult()))
                );
                break;
            case Constants.HOME_ITEMS_TYPE:

                realm.executeTransactionAsync(
                        realm -> realm.insertOrUpdate(new HomeItemsList((List<Category>) result.getResult()))
                );
                break;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getMenuFromDatabase(InteractorListener listener) {
        MenuList menuList = realm.where(MenuList.class).equalTo("id",Constants.MENU_DB_TYPE).findFirst();
        if (menuList != null) {
            getDisposable().add(Single.just(menuList)
                    .map(menuList1 -> new ResultWrapper(menuList1, Constants.MENU_DB_TYPE))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(getObserver(listener))
            );
        }
        else {
            getMenu(listener);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getBottomMenuFromDatabase(InteractorListener listener) {
        BottomMenuList bottomMenuList = realm.where(BottomMenuList.class).equalTo("id",Constants.BOTTOM_MENU_DB_TYPE).findFirst();
        if (bottomMenuList != null) {
            getDisposable().add(Single.just(bottomMenuList)
                    .map(bottomMenuList1 -> new ResultWrapper(bottomMenuList1, Constants.BOTTOM_MENU_DB_TYPE))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(getObserver(listener))
            );
        }
        else {
            getBottomMenu(listener);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getHomeItemsFromDataBase(InteractorListener listener) {
        HomeItemsList homeItemsList = realm.where(HomeItemsList.class).equalTo("id",Constants.HOME_ITEMS_DB_TYPE).findFirst();
        if (homeItemsList != null) {
            getDisposable().add(Single.just(homeItemsList)
                    .map(homeItemsList1 -> new ResultWrapper(homeItemsList1, Constants.HOME_ITEMS_DB_TYPE))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(getObserver(listener))
            );
        }
        else {
            getHomeItems(listener);
        }
    }
}
