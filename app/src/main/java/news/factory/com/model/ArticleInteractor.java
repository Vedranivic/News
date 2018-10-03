package news.factory.com.model;

import android.content.Context;
import android.util.Log;

import news.factory.com.model.data_model.News;
import news.factory.com.model.networking.NewsAPI;
import news.factory.com.model.networking.ServiceGenerator;
import news.factory.com.single.activity.SingleActivity;
import news.factory.com.single.activity.SingleActivityInterface;
import news.factory.com.single.activity.SinglePresenterInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleInteractor implements Callback<News> {

    private static final String TAG = ArticleInteractor.class.getSimpleName();

    private Call<News> call;
    private SinglePresenterInterface singlePresenterInterface;

    public ArticleInteractor(SinglePresenterInterface singlePresenterInterface) {
        this.singlePresenterInterface = singlePresenterInterface;
    }

    public void makeCall(String articleID){
        call = ServiceGenerator.getRetrofit(singlePresenterInterface.getContext()).create(NewsAPI.class)
                    .getNews(articleID,Constants.FIRST_PAGE_VALUE);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<News> call, Response<News> response) {
        Log.d(TAG,"Call successfull!");

        if(response.body()!=null) {
            singlePresenterInterface.setArticle(Integer.parseInt(response.body().getPages_no()));
        }
    }

    @Override
    public void onFailure(Call<News> call, Throwable t) {
        Log.d(TAG,"Call failed!");
    }
}
