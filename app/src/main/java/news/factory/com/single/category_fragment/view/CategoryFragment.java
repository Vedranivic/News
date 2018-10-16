package news.factory.com.single.category_fragment.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import news.factory.com.R;
import news.factory.com.base.BaseFragment;
import news.factory.com.base.Constants;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.single.adapter.RecyclerAdapter;
import news.factory.com.single.category_fragment.CategoryFragmentContract;


public class CategoryFragment extends BaseFragment implements CategoryFragmentContract.View {

    @BindView(R.id.rvCategoryItems)
    RecyclerView rvCategoryItems;

    @Inject
    public CategoryFragmentContract.Presenter categoryFragmentPresenter;
    @Inject
    public RecyclerAdapter adapter;


    public static CategoryFragment newInstance(String category, String id, String page) {
        Bundle args = new Bundle();
        args.putString(Constants.CATEGORY_KEY, category);
        args.putString(Constants.ARTICLE_KEY, id);
        args.putString(Constants.PAGE_KEY, page);
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_category, container, false);
        unbinder = ButterKnife.bind(this,viewGroup);

        AndroidSupportInjection.inject(this);

        setupRecycler();
        setupMVP();
        getItems();

        return viewGroup;
    }

    private void setupRecycler() {
        //adapter  = new RecyclerAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvCategoryItems.setLayoutManager(linearLayoutManager);
        rvCategoryItems.setAdapter(adapter);
        rvCategoryItems.addItemDecoration(new DividerItemDecoration(
                Objects.requireNonNull(getContext()),linearLayoutManager.getOrientation()
        ));
    }

    private void setupMVP() {
        //categoryFragmentPresenter = new CategoryFragmentPresenter(this);
        categoryFragmentPresenter.initialize(getID(), getPage());
    }

    private void getItems() {
        categoryFragmentPresenter.getItemsByCategory(getCategory());
    }

    @Override
    public void displayItemsByCategory(List<RecyclerItemsWrapper> items) {
        adapter.setItems(items);
    }

    public String getCategory() {
        if(getArguments()!=null){
            return getArguments().getString(Constants.CATEGORY_KEY);
        }
        return "";
    }

    public String getID() {
        if(getArguments()!=null){
            return getArguments().getString(Constants.ARTICLE_KEY);
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
