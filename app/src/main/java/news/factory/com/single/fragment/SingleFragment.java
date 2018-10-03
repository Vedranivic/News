package news.factory.com.single.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.model.Constants;
import news.factory.com.model.data_model.BaseItem;

import news.factory.com.R;
import news.factory.com.single.BaseFragment;

import news.factory.com.single.adapter.SingleRecyclerAdapter;

public class SingleFragment extends BaseFragment implements SingleFragmentInterface {

    private SingleFragmentPresenter presenter;

    @BindView(R.id.rvItems)
    RecyclerView rvItems;

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
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_page, container, false);
        unbinder = ButterKnife.bind(this,viewGroup);
        setupMVP();
        getArticleItems();

        return viewGroup;
    }

    private void setupMVP() {
        presenter = new SingleFragmentPresenter(this);
    }

    private void getArticleItems() {
        presenter.getArticleItems();
    }

    @Override
    public void displayArticleItems(List<BaseItem> items) {
        rvItems.setLayoutManager(new LinearLayoutManager(getContext()));
        rvItems.setAdapter(new SingleRecyclerAdapter(items,getContext()));
    }

    @Override
    public String getArticleID() {
        if(getArguments()!=null){
            return getArguments().getString(Constants.ARTICLE_KEY);
        }
        return "";
    }

    @Override
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
        //call.cancel();
    }

    @Override
    public void onStop() {
        super.onStop();
        //call.cancel();
    }

}
