package com.hecy.command.designmodel;

import rx.Observable;
import rx.Subscriber;

public class HystrixCommandE {

    public static void main(String[] args) {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>(){
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello rxjava");
                subscriber.onNext("I am hecy");
                subscriber.onCompleted();
            }
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("subscriber" + s);
            }
        };
        observable.subscribe(subscriber);
    }


}
