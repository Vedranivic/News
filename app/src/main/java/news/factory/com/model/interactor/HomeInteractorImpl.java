package news.factory.com.model.interactor;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import news.factory.com.base.BaseInteractorImpl;
import news.factory.com.base.Constants;
import news.factory.com.base.ResultWrapper;
import news.factory.com.base.networking.NewsAPI;
import news.factory.com.model.data_model.Menu;

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

    @Override
    public void writeToDatabase(List<Menu> menuList) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(menuList);
                Log.d("MYTAG_SIZE",String.valueOf(menuList.size()));
                //realm.insertOrUpdate(menuList);
            }
        });
    }

    @Override
    public void loadFromDatabase() {
        RealmResults<Menu> menuList = realm.where(Menu.class).equalTo("parent_id","0").findAll();
        realm.beginTransaction();
        for(Menu menu : menuList){
            Log.d("MYTAG","TITLE:" + menu.getTitle());
        }
        realm.cancelTransaction();
    }
}
