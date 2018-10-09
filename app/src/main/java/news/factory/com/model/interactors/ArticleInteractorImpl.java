package news.factory.com.model.interactors;

import android.annotation.SuppressLint;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import news.factory.com.base.BaseInteractorImpl;
import news.factory.com.base.Constants;
import news.factory.com.base.networking.APIServiceGenerator;


public class ArticleInteractorImpl extends BaseInteractorImpl implements ArticleInteractor {

    private static final String TAG = ArticleInteractorImpl.class.getSimpleName();

    @SuppressLint("CheckResult")
    @SuppressWarnings("unchecked")
    @Override
    public void makeCall(String articleID, String page, final InteractorListener listener) {

        disposable.add(APIServiceGenerator.getAPI().getNews(articleID,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver(listener, Constants.NEWS_TYPE))
        );



        //Operators testing:

        List<String> myList = Arrays.asList("a", "ab", "abc");

        Observable<String> myObservable1 = Observable.create(subscriber -> {
            subscriber.onNext("AAAAA");
            subscriber.onComplete();
        });

        myObservable1 = Observable.fromIterable(myList);

        myObservable1.take(2).first("Default").subscribe(string -> Log.d(TAG, string + "\n"));

        Observable<String> myObservable2 = Observable.defer(()-> Observable.just("First", "Second","Third"));

        myObservable2.startWith(Arrays.asList("Strings", "are"))
                .scan((s, s2) -> s.concat(s2) )
                .map(String::toUpperCase)
                .filter(s -> s.length()>10)
                .subscribe(string -> Log.d(TAG, string + "\n"));

    }

    public void dispose(){
        disposable.dispose();
    }

}
