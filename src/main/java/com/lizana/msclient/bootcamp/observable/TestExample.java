package com.lizana.msclient.bootcamp.observable;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class TestExample {
    public static void main(String[] args) {

        Observable<String> empty = Observable.just("hola","mundo");
        empty.subscribe(System.out::println);

        //observable
        Observable<String> ObservableString = Observable.create(
                emiter -> {
                    System.out.println("Hilo Observable:  "+ Thread.currentThread().getName());
                    emiter.onNext("maria");
                    emiter.onNext("juan");
                    emiter.onNext("jose");
                    emiter.onNext("luisa");
                    emiter.onComplete();


                }
        );

        //observer
        Observer<String> observerString = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");

            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println("onNext");

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

    }

}
