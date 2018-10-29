package news.factory.com.home.fragment_home.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.Constants;
import news.factory.com.base.adapter.RecyclerAdapter;
import news.factory.com.base.adapter.RecyclerAdapterImpl;
import news.factory.com.base.BaseFragment;
import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.home.activity.HomeContract;
import news.factory.com.home.fragment_category.view.HomeCategoryFragment;
import news.factory.com.home.fragment_home.HomeFragmentContract;


public class HomeFragment extends BaseFragment implements HomeFragmentContract.View {

    @BindView(R.id.rvHome)
    RecyclerView rvHome;

    @Inject
    public HomeFragmentContract.Presenter homeFragmentPresenter;

    @Inject
    public RecyclerAdapter adapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this,viewGroup);

        setupRecycler();
        setupMVP();
        getHomeItems();

        return viewGroup;
    }

    private void setupRecycler() {
        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        rvHome.setAdapter((RecyclerAdapterImpl)adapter);
        Log.d("ADAPTER_TAG", adapter.toString());
    }

    private void setupMVP() {
        homeFragmentPresenter.initialize();
    }

    private void getHomeItems() {
        homeFragmentPresenter.getHomeItems();
    }

    @Override
    public void displayHomeItems(List<RecyclerItemsWrapper> items) {
        adapter.setItems(items);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        homeFragmentPresenter.dispose();
    }
}
