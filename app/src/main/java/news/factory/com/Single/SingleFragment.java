package news.factory.com.Single;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.Model.Constants;
import news.factory.com.Model.DataModel.BaseItem;
import news.factory.com.Model.DataModel.Content;
import news.factory.com.Model.DataModel.News;
import news.factory.com.Model.Networking.NewsAPI;
import news.factory.com.Model.Networking.ServiceGenerator;
import news.factory.com.R;
import news.factory.com.Single.Adapter.SingleRecyclerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleFragment extends BaseFragment implements Callback<News> {

    private static final String TAG = SingleFragment.class.getSimpleName();
    private Call<News> call;
    private List<BaseItem> items = new ArrayList<>();

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
        getNews();

        return viewGroup;
    }

    private void getNews() {
        String page;
        if(getArguments()!=null) {
            page = getArguments().getString(Constants.PAGE_KEY);
        }
        else {
            page = "1";
        }

        call = ServiceGenerator.getRetrofit(getContext()).create(NewsAPI.class)
                .getNews(getArguments().getString(Constants.ARTICLE_KEY), page);
        call.enqueue(this);
    }

    private void setupUI() {
        rvItems.setLayoutManager(new LinearLayoutManager(getContext()));
        rvItems.setAdapter(new SingleRecyclerAdapter(items,getContext()));
    }

    @Override
    public void onResponse(Call<News> call, Response<News> response) {
        Log.d(TAG,"Call successfull!");

        if(response.body()!=null){
            News news = response.body();
            if(!news.getNo_featured_image()){
                items.add(news.getFeatured_image());//feature image
            }
            items.add(news);                        //title
            for(Content c : news.getContent()) {
                if(c.getType().equals("text")) {
                    items.add(c);                   //text
                }
                if(c.getType().equals("image")){
                    items.add(c.getImage());        //image
                }
            }
            setupUI();
        }
        call.cancel();
    }

    @Override
    public void onFailure(Call<News> call, Throwable t) {
        Log.d(TAG,"Call failed!");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        call.cancel();
    }

    @Override
    public void onStop() {
        super.onStop();
        call.cancel();
    }
}
