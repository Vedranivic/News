package news.factory.com.single.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import dagger.Module;
import dagger.Provides;
import news.factory.com.base.dependency_injection.PerFragmentScope;
import news.factory.com.model.interactor.ArticleInteractor;
import news.factory.com.model.interactor.ArticleInteractorImpl;
import news.factory.com.single.adapter.RecyclerAdapter;
import news.factory.com.single.fragment.presenter.SingleFragmentPresenter;
import news.factory.com.single.fragment.view.SingleFragment;

@Module
public class SingleFragmentModule {

    private final SingleFragmentContract.View singleFragmentView;

    private final Context singleFragmentContext;

    public SingleFragmentModule(SingleFragmentContract.View singleFragmentView, Context singleFragmentContext) {
        this.singleFragmentView = singleFragmentView;
        this.singleFragmentContext = singleFragmentContext;
    }

    @Provides
    @PerFragmentScope
    public SingleFragmentContract.Presenter provideSingleFragmentPresenter(SingleFragmentContract.View singleFragmentView, ArticleInteractor articleInteractor){
        return new SingleFragmentPresenter(singleFragmentView, articleInteractor);
    }

    @Provides
    @PerFragmentScope
    public SingleFragmentContract.View provideSingleFragmentView(){
        return singleFragmentView;
    }

    @Provides
    @PerFragmentScope
    public RecyclerAdapter provideRecyclerAdapter(Context context){
        return new RecyclerAdapter(context);
    }

    @Provides
    @PerFragmentScope
    public Context provideContext(){
        return singleFragmentContext;
    }

    @Provides
    @PerFragmentScope
    public ArticleInteractor provideArticleInteractor(){
        return new ArticleInteractorImpl();
    }
}
