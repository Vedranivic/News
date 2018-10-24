package news.factory.com.home.fragment_category_sort;

public interface HomeSortCategoryFragmentContract {

    interface View{

    }

    interface Presenter{
        void dispose();

        void initialize(String id, String page);

        void getItemsByCategory(String category);
    }
}
