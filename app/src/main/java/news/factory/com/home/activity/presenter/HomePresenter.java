package news.factory.com.home.activity.presenter;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import news.factory.com.base.Constants;
import news.factory.com.base.ResultWrapper;
import news.factory.com.home.activity.HomeContract;
import news.factory.com.model.data_model.BottomMenuList;
import news.factory.com.model.data_model.Menu;
import news.factory.com.model.data_model.MenuList;
import news.factory.com.model.interactor.HomeInteractor;
import news.factory.com.model.interactor.InteractorListener;

public class HomePresenter implements HomeContract.Presenter, InteractorListener {

    private final String TAG = HomePresenter.class.getSimpleName();

    private HomeContract.View homeView;
    private HomeInteractor homeInteractor;
    public Boolean isNetworkConnected;

    @Inject
    public HomePresenter(HomeContract.View homeView, HomeInteractor homeInteractor) {
        this.homeView = homeView;
        this.homeInteractor = homeInteractor;
    }

    @Override
    public void initialize(Boolean isNetworkConnected) {
        this.isNetworkConnected = isNetworkConnected;
    }

    @Override
    public void getMenu() {
        if(isNetworkConnected) {
            homeInteractor.getMenu(this);
        }
        else {
            homeInteractor.getMenuFromDatabase(this);
        }
    }

    @Override
    public void getBottomMenu() {
        if(isNetworkConnected) {
            homeInteractor.getBottomMenu(this);
        }
        else {
            homeInteractor.getBottomMenuFromDatabase(this);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onSuccess(ResultWrapper result) {
        switch (result.getType()){
            case Constants.BOTTOM_MENU_TYPE:
                homeView.displayBottomMenu((List<Menu>) result.getResult());
                homeInteractor.writeToDatabase(result);
                break;
            case Constants.BOTTOM_MENU_DB_TYPE:
                homeView.displayBottomMenu(((BottomMenuList) result.getResult()).getMenuList());
                Log.d(TAG, "Bottom Menu Loaded from database");
                break;
            case Constants.MENU_TYPE:
                homeView.displayMenu((List<Menu>)result.getResult());
                homeInteractor.writeToDatabase(result);
                break;
            case Constants.MENU_DB_TYPE:
                homeView.displayMenu(((MenuList) result.getResult()).getMenuList());
                Log.d(TAG, "Menu Loaded from database");
                break;
        }
    }

    @Override
    public void onFailure() {
        Log.d(TAG, "Error getting data");
        homeView.displayErrorMessage("No internet connection. Please connect to the internet.");
    }


}
