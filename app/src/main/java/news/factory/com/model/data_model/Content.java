package news.factory.com.model.data_model;

public class Content
{
    private ImageWithSize image_with_size;

    private String id;

    private String article_id;

    private String order;

    private String page;

    private String width;

    private String embed_src;

    private String data_raw;

    private String data;

    private Image image;

    private String image_caption;

    private String type;

    private String image_source;

    public ImageWithSize getImage_with_size ()
    {
        return image_with_size;
    }

    public void setImage_with_size (ImageWithSize image_with_size)
    {
        this.image_with_size = image_with_size;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getArticle_id ()
    {
        return article_id;
    }

    public void setArticle_id (String article_id)
    {
        this.article_id = article_id;
    }

    public String getOrder ()
    {
        return order;
    }

    public void setOrder (String order)
    {
        this.order = order;
    }

    public String getPage ()
    {
        return page;
    }

    public void setPage (String page)
    {
        this.page = page;
    }

    public String getWidth ()
    {
        return width;
    }

    public void setWidth (String width)
    {
        this.width = width;
    }

    public String getEmbed_src ()
    {
        return embed_src;
    }

    public void setEmbed_src (String embed_src)
    {
        this.embed_src = embed_src;
    }

    public String getData_raw ()
    {
        return data_raw;
    }

    public void setData_raw (String data_raw)
    {
        this.data_raw = data_raw;
    }

    public String getData ()
    {
        return data;
    }

    public void setData (String data)
    {
        this.data = data;
    }

    public Image getImage ()
    {
        return image;
    }

    public void setImage (Image image)
    {
        this.image = image;
    }

    public String getImage_caption ()
{
    return image_caption;
}

    public void setImage_caption (String image_caption)
    {
        this.image_caption = image_caption;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getImage_source ()
{
    return image_source;
}

    public void setImage_source (String image_source)
    {
        this.image_source = image_source;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [image_with_size = "+image_with_size+", id = "+id+", article_id = "+article_id+", order = "+order+", page = "+page+", width = "+width+", embed_src = "+embed_src+", data_raw = "+data_raw+", data = "+data+", image = "+image+", image_caption = "+image_caption+", type = "+type+", image_source = "+image_source+"]";
    }
}