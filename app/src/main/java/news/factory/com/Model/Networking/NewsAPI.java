package news.factory.com.Model.Networking;

import news.factory.com.Model.DataModel.News;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsAPI {
    @GET("clanak/{articleID}")
    Call<News> getNews(@Path("articleID") String articleId,
                       @Query("api_token") String apiToken,
                       @Query("stranica") String page
                       );
}
