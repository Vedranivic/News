package news.factory.com.base;

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
