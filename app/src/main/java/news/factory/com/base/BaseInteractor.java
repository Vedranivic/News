package news.factory.com.base;


import android.arch.lifecycle.LifecycleObserver;

public interface BaseInteractor extends LifecycleObserver {
    void dispose();
}
