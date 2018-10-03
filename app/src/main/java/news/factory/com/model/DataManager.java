package news.factory.com.model;

import news.factory.com.model.data_model.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataManager implements Callback<News> {

    private static final String TAG = DataManager.class.getSimpleName();
    private static DataManager INSTANCE;
    private News news;
    private Callback<News> mCallback;

    private DataManager(){
    }

    public static synchronized DataManager getInstance(){
        if(INSTANCE==null){
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }

    public News getNews(){
        return news;
    }

    public void makeDataCall(){

    }

    @Override
    public void onResponse(Call<News> call, Response<News> response) {

    }

    @Override
    public void onFailure(Call<News> call, Throwable t) {

    }
}
