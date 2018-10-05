package news.factory.com.model;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import news.factory.com.model.data_model.News;
import news.factory.com.base.networking.NewsAPI;
import news.factory.com.base.networking.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleInteractorImpl implements ArticleInteractor {

    private static final String TAG = ArticleInteractorImpl.class.getSimpleName();
    private CompositeDisposable disposable = new CompositeDisposable();
    private Call<News> call;


    public void makeCall(String articleID, String page, final ArticleListener listener){

        call = ServiceGenerator.getRetrofit().create(NewsAPI.class)
                    .getNews(articleID,page);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                Log.d(TAG,"Call successfull!");

                if(response.body()!=null) {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                listener.onFailure();
            }
        });
    }


    public void cancelCall(){
        call.cancel();
    }

    @Override
    public void makeCallRx(String articleID, String page, final ArticleListener listener) {

        disposable.add(ServiceGenerator.getRetrofit().create(NewsAPI.class).getNewsRx(articleID,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<News>() {
                    @Override
                    public void onSuccess(News news) {
                        Log.d(TAG,"Call successfull!");
                        if(news!=null) {
                            listener.onSuccess(news);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure();
                    }
                })
        );
    }

    public void dispose(){
        disposable.dispose();
    }

}
