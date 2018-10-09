package news.factory.com.base.networking;

import io.reactivex.Observable;
import io.reactivex.Single;
import news.factory.com.model.data_model.News;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsAPI {

    @GET("clanak/{articleID}")
    Single<News> getNews(@Path("articleID") String articleId,
                         @Query("stranica") String page
    );
}
