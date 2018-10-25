package news.factory.com.single.fragment_category.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.BaseFragment;
import news.factory.com.base.Constants;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.common.adapter.RecyclerAdapter;
import news.factory.com.common.adapter.RecyclerAdapterImpl;
import news.factory.com.single.fragment_category.CategoryFragmentContract;


public class CategoryFragment extends BaseFragment implements CategoryFragmentContract.View {

    @BindView(R.id.rvCategoryItems)
    RecyclerView rvCategoryItems;

    @Inject
    public CategoryFragmentContract.Presenter categoryFragmentPresenter;
    @Inject
    public RecyclerAdapter adapter;


    public static CategoryFragment newInstance(String category, String categoryID, String page) {
        Bundle args = new Bundle();
        args.putString(Constants.CATEGORY_SORT_KEY, category);
        args.putString(Constants.CATEGORY_ID_KEY, categoryID);
        args.putString(Constants.PAGE_KEY, page);
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_category_single, container, false);
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
        categoryFragmentPresenter.initialize(getID(), getPage());
    }

    private void getItems() {
        categoryFragmentPresenter.getItemsByCategory(getCategory());
    }

    @Override
    public void displayItemsByCategory(List<RecyclerItemsWrapper> items) {
        adapter.setItems(items);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this.getContext(),message,Toast.LENGTH_LONG).show();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        categoryFragmentPresenter.dispose();
    }
}
