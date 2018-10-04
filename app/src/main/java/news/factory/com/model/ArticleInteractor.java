package news.factory.com.model;

import android.util.Log;

import news.factory.com.App;
import news.factory.com.model.data_model.News;
import news.factory.com.model.networking.NewsAPI;
import news.factory.com.model.networking.ServiceGenerator;
import news.factory.com.single.activity.SinglePresenterInterface;
import news.factory.com.single.fragment.SingleFragmentPresenterInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleInteractor implements Callback<News> {

    private static final String TAG = ArticleInteractor.class.getSimpleName();

    private Call<News> call;
    private SinglePresenterInterface singlePresenterInterface;
    private SingleFragmentPresenterInterface singleFragmentPresenterInterface;

    public ArticleInteractor(SinglePresenterInterface singlePresenterInterface) {
        this.singlePresenterInterface = singlePresenterInterface;
    }

    public ArticleInteractor(SingleFragmentPresenterInterface singleFragmentPresenterInterface) {
        this.singleFragmentPresenterInterface = singleFragmentPresenterInterface;
    }

    public void makeCall(String articleID){
        call = ServiceGenerator.getRetrofit(App.provideContext()).create(NewsAPI.class)
                    .getNews(articleID,Constants.FIRST_PAGE_VALUE);
        call.enqueue(this);
    }

    public void makeCall(String articleID, String page){
        call = ServiceGenerator.getRetrofit(App.provideContext()).create(NewsAPI.class)
                .getNews(articleID,page);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<News> call, Response<News> response) {
        Log.d(TAG,"Call successfull!");

        if(response.body()!=null) {
            if(singlePresenterInterface!=null) {
                singlePresenterInterface.setArticle(Integer.parseInt(response.body().getPages_no()));
            }
            if(singleFragmentPresenterInterface!=null){
                singleFragmentPresenterInterface.setArticleItems(response.body());
            }
        }
    }

    @Override
    public void onFailure(Call<News> call, Throwable t) {
        Log.d(TAG,"Call failed!");
    }

    public void cancelCall(){
        call.cancel();
    }
}
