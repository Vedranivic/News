package news.factory.com.home.activity.presenter;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import news.factory.com.base.Constants;
import news.factory.com.base.ResultWrapper;
import news.factory.com.home.activity.HomeContract;
import news.factory.com.model.data_model.Category;
import news.factory.com.model.data_model.Menu;
import news.factory.com.model.interactor.CategoryInteractor;
import news.factory.com.model.interactor.HomeInteractor;
import news.factory.com.model.interactor.InteractorListener;

public class HomePresenter implements HomeContract.Presenter, InteractorListener {

    private final String TAG = HomePresenter.class.getSimpleName();

    private HomeContract.View homeView;
    private HomeInteractor homeInteractor;

    @Inject
    public HomePresenter(HomeContract.View homeView, HomeInteractor homeInteractor) {
        this.homeView = homeView;
        this.homeInteractor = homeInteractor;
    }

    @Override
    public void getMenu() {
        homeInteractor.getMenu(this);
    }

    @Override
    public void getBottomMenu() {
        homeInteractor.getBottomMenu(this);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void dispose() {
        homeInteractor.dispose();
    }

    @Override
    public void onSuccess(ResultWrapper result) {
        switch (result.getType()){
            case Constants.MENU_BOTTOM_TYPE:
                homeView.displayBottomMenu((List<Menu>) result.getResult());
                break;
            case Constants.MENU_TYPE:
                homeView.displayMenu((List<Menu>) result.getResult());
                break;
        }
    }

    @Override
    public void onFailure() {
        Log.d(TAG, "Error getting data");
    }
}
