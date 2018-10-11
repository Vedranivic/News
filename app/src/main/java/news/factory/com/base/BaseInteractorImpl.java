package news.factory.com.base;

import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import news.factory.com.model.interactor.InteractorListener;

public class BaseInteractorImpl implements BaseInteractor {

    private CompositeDisposable disposable;

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
                Log.e("GET OBSERVER", e.getMessage());
                listener.onFailure();
            }
        };
    }

    public CompositeDisposable getDisposable(){
        //if(disposable.isDisposed()){
        if(disposable==null || disposable.isDisposed()) {
            disposable = new CompositeDisposable();
        }
        return disposable;
    }

    @Override
    public void dispose() {
        disposable.dispose();
    }

}
