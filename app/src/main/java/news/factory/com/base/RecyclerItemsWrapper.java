package news.factory.com.base;

public class RecyclerItemsWrapper {

    private Object item;
    private int viewType;

    public RecyclerItemsWrapper(Object item, int viewType){
        this.item = item;
        this.viewType = viewType;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int type) {
        this.viewType = type;
    }
}
