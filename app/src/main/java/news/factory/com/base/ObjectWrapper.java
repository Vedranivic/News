package news.factory.com.base;

public class ObjectWrapper {

    private Object presenter;

    private Object view;

    public ObjectWrapper(Object presenter, Object view) {
        this.presenter = presenter;
        this.view = view;
    }

    public Object getPresenter() {
        return presenter;
    }

    public Object getView() {
        return view;
    }
}
