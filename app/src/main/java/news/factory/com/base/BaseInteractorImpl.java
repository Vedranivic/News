package news.factory.com.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import news.factory.com.model.interactors.InteractorListener;

public class BaseInteractorImpl implements BaseInteractor {

    public CompositeDisposable disposable = new CompositeDisposable();

    public DisposableSingleObserver getObserver(final InteractorListener listener, final int resultType){
        return new DisposableSingleObserver<BaseResult>() {
            @Override
            public void onSuccess(BaseResult result) {
                if (result != null) {
                    result.setResultType(resultType);
                    listener.onSuccess(result);
                }
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure();
            }
        };
    }

    @Override
    public void dispose() {
        disposable.dispose();
    }

}
