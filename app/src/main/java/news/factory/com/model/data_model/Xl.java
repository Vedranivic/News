package news.factory.com.model.data_model;

import io.realm.RealmObject;

public class Xl extends RealmObject
{
    private String height;

    private String width;

    private String image;

    public String getHeight ()
    {
        return height;
    }

    public void setHeight (String height)
    {
        this.height = height;
    }

    public String getWidth ()
    {
        return width;
    }

    public void setWidth (String width)
    {
        this.width = width;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [height = "+height+", width = "+width+", image = "+image+"]";
    }
}
