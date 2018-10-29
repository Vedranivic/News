package news.factory.com.home.fragment_category.presenter;

import javax.inject.Inject;

import news.factory.com.home.fragment_category.HomeCategoryFragmentContract;

public class HomeCategoryFragmentPresenter implements HomeCategoryFragmentContract.Presenter {

    private HomeCategoryFragmentContract.View view;

    @Inject
    public HomeCategoryFragmentPresenter(HomeCategoryFragmentContract.View view){
        this.view = view;
    }

    @Override
    public void initialize() {
    }

}
