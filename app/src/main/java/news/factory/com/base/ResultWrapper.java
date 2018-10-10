package news.factory.com.base;


public class ResultWrapper {

    private Object result;
    private int type;

    public ResultWrapper(Object object, int type ){
        this.result = object;
        this.type = type;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
