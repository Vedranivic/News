package news.factory.com.home.fragment_category_sort;

public interface HomeSortCategoryFragmentContract {

    interface View{
        void loadMore();
    }

    interface Presenter{
        void dispose();

        void initialize(String id, String page, String color);

        void getItemsByCategory(String category);
    }
}
