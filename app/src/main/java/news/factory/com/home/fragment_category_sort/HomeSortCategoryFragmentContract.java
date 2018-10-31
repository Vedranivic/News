package news.factory.com.home.fragment_category_sort;

public interface HomeSortCategoryFragmentContract {

    interface View{
        void loadMore();
    }

    interface Presenter{
        void initialize(String id, String page, String color, Boolean isNetworkConnected);

        void getItemsByCategory(String category);
    }
}
