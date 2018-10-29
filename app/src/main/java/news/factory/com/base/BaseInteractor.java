package news.factory.com.base;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

public interface BaseInteractor extends LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy();
}