package news.factory.com.base;

import news.factory.com.single.activity.presenter.SinglePresenter;
import news.factory.com.single.category_fragment.presenter.CategoryFragmentPresenter;
import news.factory.com.single.fragment.presenter.SingleFragmentPresenter;

public class ObjectWrapper {

    private Object presenter;

    private Object view;

    private Object pagerAdapter;

    public ObjectWrapper(Object presenter, Object view) {
        this.presenter = presenter;
        this.view = view;
    }

    public ObjectWrapper(Object presenter, Object view, Object pagerAdapter) {
        this.presenter = presenter;
        this.view = view;
        this.pagerAdapter = pagerAdapter;
    }

    public int getType(){
        if(presenter instanceof SingleFragmentPresenter){
            return SingleFragmentPresenter.class.hashCode();
        }
        else if (presenter instanceof CategoryFragmentPresenter){
            return CategoryFragmentPresenter.class.hashCode();
        }
        else if (presenter instanceof SinglePresenter){
            return SinglePresenter.class.hashCode();
        }
        else
            return 0;
    }

    public Object getPresenter() {
        return presenter;
    }

    public Object getView() {
        return view;
    }

    public Object getPagerAdapter() {
        return pagerAdapter;
    }
}
