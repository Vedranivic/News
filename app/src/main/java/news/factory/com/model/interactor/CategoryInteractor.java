package news.factory.com.model.interactor;

import news.factory.com.base.BaseInteractor;
import news.factory.com.base.ResultWrapper;
import news.factory.com.home.fragment_category_sort.presenter.HomeSortCategoryFragmentPresenter;

public interface CategoryInteractor extends BaseInteractor {
    void makeCall(String category, String id, String page, InteractorListener listener);

    void makeCallFromDatabase(String category, String id, String page, InteractorListener listener);

    void writeToDatabase(ResultWrapper result);
}
