package news.factory.com.model.networking;

import news.factory.com.model.data_model.News;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsAPI {
    @GET("clanak/{articleID}")
    Call<News> getNews(@Path("articleID") String articleId,
                       @Query("stranica") String page
                       );
}
