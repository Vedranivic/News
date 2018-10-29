package news.factory.com.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import news.factory.com.base.networking.NewsAPI;
import news.factory.com.model.interactor.InteractorListener;

public class BaseInteractorImpl implements BaseInteractor {

    private CompositeDisposable disposable;

    @Inject
    public NewsAPI newsAPI;

    public DisposableSingleObserver getObserver(final InteractorListener listener){
        return new DisposableSingleObserver<ResultWrapper>() {
            @Override
            public void onSuccess(ResultWrapper result) {
                if (result != null) {
                    listener.onSuccess(result);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("GET_OBSERVER", e.getMessage());
                listener.onFailure();
            }
        };
    }

    public CompositeDisposable getDisposable(){
        if(disposable==null || disposable.isDisposed()) {
            disposable = new CompositeDisposable();
        }
        return disposable;
    }

    @Override
    public void onDestroy(){
        Log.d("DISPOSE_TAG", "Disposing using LifecycleObserver");
        disposable.dispose();
    }
}
