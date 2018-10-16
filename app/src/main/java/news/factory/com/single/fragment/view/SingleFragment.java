package news.factory.com.single.fragment.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import news.factory.com.App;
import news.factory.com.base.BaseFragment;
import news.factory.com.base.Constants;

import news.factory.com.R;

import news.factory.com.base.RecyclerItemsWrapper;
import news.factory.com.single.activity.SingleActivityModule;
import news.factory.com.single.adapter.RecyclerAdapter;
import news.factory.com.single.fragment.SingleFragmentContract;
import news.factory.com.single.fragment.SingleFragmentModule;
import news.factory.com.single.fragment.presenter.SingleFragmentPresenter;

public class SingleFragment extends BaseFragment implements SingleFragmentContract.View {

    @BindView(R.id.rvItems)
    RecyclerView rvItems;

    @Inject
    public SingleFragmentContract.Presenter singleFragmentPresenter;
    @Inject
    public RecyclerAdapter adapter;


    public static SingleFragment newInstance(String articleId, String page) {
        Bundle args = new Bundle();
        args.putString(Constants.ARTICLE_KEY, articleId);
        args.putString(Constants.PAGE_KEY, page);
        SingleFragment fragment = new SingleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_single, container, false);
        unbinder = ButterKnife.bind(this,viewGroup);

        AndroidSupportInjection.inject(this);

        setupRecycler();
        setupMVP();
        getArticleItems();

        return viewGroup;
    }

    private void setupRecycler() {
        //adapter  = new RecyclerAdapter(getContext());
        adapter.setChildFragmentManager(getChildFragmentManager());
        rvItems.setLayoutManager(new LinearLayoutManager(getContext()));
        rvItems.setAdapter(adapter);
    }

    private void setupMVP() {
        //singleFragmentPresenter = new SingleFragmentPresenter(this);
        singleFragmentPresenter.initialize(getArticleID(), getPage());
    }

    private void getArticleItems() {
        singleFragmentPresenter.getArticleItems();
    }

    @Override
    public void displayArticleItems(List<RecyclerItemsWrapper> items) {
        adapter.setItems(items);
    }

    public String getArticleID() {
        if(getArguments()!=null){
            return getArguments().getString(Constants.ARTICLE_KEY);
        }
        return "";
    }

    public String getPage() {
        if(getArguments()!=null) {
            return getArguments().getString(Constants.PAGE_KEY);
        }
        else {
            return  "1";
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        singleFragmentPresenter.dispose();
    }

}
