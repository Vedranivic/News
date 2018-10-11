package news.factory.com.base.networking;

import io.reactivex.Observable;
import io.reactivex.Single;
import news.factory.com.model.data_model.Category;
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

/*    @GET("najpopularnije/{id}")
    Single<News> getMostPopular(@Path("id") String id,
                                @Query("stranica") String page
    );

    @GET("najcitanije/{id}")
    Single<News> getMostRead(@Path("id") String id,
                                @Query("stranica") String page
    );

    @GET("najnovije/{id}")
    Single<News> getNewest(@Path("id") String id,
                                @Query("stranica") String page
    );*/

    @GET("{category}/{id}")
    Single<Category> getByCategory(@Path("category") String category,
                                   @Path("id") String id,
                                   @Query("stranica") String page
    );

}
