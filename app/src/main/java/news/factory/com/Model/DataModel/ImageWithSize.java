package news.factory.com.Model.DataModel;

public class ImageWithSize
{
    private Xl xl;

    private Original original;

    private M m;

    public Xl getXl ()
    {
        return xl;
    }

    public void setXl (Xl xl)
    {
        this.xl = xl;
    }

    public Original getOriginal ()
    {
        return original;
    }

    public void setOriginal (Original original)
    {
        this.original = original;
    }

    public M getM ()
    {
        return m;
    }

    public void setM (M m)
    {
        this.m = m;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [xl = "+xl+", original = "+original+", m = "+m+"]";
    }
}
