package news.factory.com.home.fragment_category_sort.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.common.adapter.RecyclerAdapter;
import news.factory.com.common.adapter.RecyclerAdapterImpl;
import news.factory.com.base.BaseFragment;
import news.factory.com.base.Constants;
import news.factory.com.home.fragment_category_sort.HomeSortCategoryFragmentContract;

public class HomeSortCategoryFragment extends BaseFragment implements HomeSortCategoryFragmentContract.View {

    @BindView(R.id.rvCategoryItems)
    RecyclerView rvCategoryItems;

    @Inject
    public HomeSortCategoryFragmentContract.Presenter presenter;
    @Inject
    public RecyclerAdapter adapter;

    public static HomeSortCategoryFragment newInstance(String category, String categoryID, String page, String color) {
        Bundle args = new Bundle();
        args.putString(Constants.CATEGORY_SORT_KEY, category);
        args.putString(Constants.CATEGORY_ID_KEY, categoryID);
        args.putString(Constants.PAGE_KEY, page);
        args.putString(Constants.COLOR_KEY,color);
        HomeSortCategoryFragment fragment = new HomeSortCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_category_sort_home, container, false);
        unbinder = ButterKnife.bind(this,viewGroup);

        setupRecycler();
        setupMVP();
        getItems();

        return viewGroup;
    }

    private void setupRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvCategoryItems.setLayoutManager(linearLayoutManager);
        rvCategoryItems.setAdapter((RecyclerAdapterImpl)adapter);
    }

    private void setupMVP() {
        presenter.initialize(getID(), getPage(), getColor());
    }

    private void getItems() {
        presenter.getItemsByCategory(getCategory());
    }

    @Override
    public void loadMore() {
        Toast.makeText(this.getContext(),"Loading more articles", Toast.LENGTH_SHORT).show();
    }

    public String getCategory() {
        if(getArguments()!=null){
            return getArguments().getString(Constants.CATEGORY_SORT_KEY);
        }
        return "";
    }

    public String getID() {
        if(getArguments()!=null){
            return getArguments().getString(Constants.CATEGORY_ID_KEY);
        }
        return "";
    }

    public String getPage() {
        if(getArguments()!=null){
            return getArguments().getString(Constants.PAGE_KEY);
        }
        return "";
    }

    public String getColor() {
        if(getArguments()!=null){
            return getArguments().getString(Constants.COLOR_KEY);
        }
        return "";
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dispose();
    }

}
