package com.zjxdd.mvp.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * $
 *
 * @author admin
 * @date 2020-03-20 11:03
 */
public abstract class BasePresenter<V extends BaseView> {
    private V view;
    private CompositeDisposable mCompositeDisposable;

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        view = null;
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }

    }

    public V getView() {
        return view;

    }

    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }


}
