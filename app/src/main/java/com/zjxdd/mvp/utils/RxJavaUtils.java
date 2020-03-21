package com.zjxdd.mvp.utils;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * $
 * 线程切换
 *
 * @author admin
 * @date 2020-03-20 11:33
 */
public class RxJavaUtils {
    public static <T> Observable toSubscribe(Observable<T> observable) {

        return observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
