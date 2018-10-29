package news.factory.com.base.view_holder.indicator;

public class IndicatorDataClass {
    private String page;
    private String totalPages;

    public IndicatorDataClass(String page, String totalPages) {
        this.page = page;
        this.totalPages = totalPages;
    }

    public String getPage() {
        return page;
    }

    public String getTotalPages() {
        return totalPages;
    }
}
