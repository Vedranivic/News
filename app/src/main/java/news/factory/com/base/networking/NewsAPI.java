package news.factory.com.base.networking;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import news.factory.com.model.data_model.Category;
import news.factory.com.model.data_model.Menu;
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

    @GET("{category}/{id}")
    Single<Category> getByCategory(@Path("category") String category,
                                   @Path("id") String id,
                                   @Query("stranica") String page
    );

    @GET("index")
    Single<List<Category>> getIndex();

    @GET("menu")
    Single<List<Menu>> getMenu();

    @GET("menu-bottom")
    Single<List<Menu>> getMenuBottom();

}
