package com.zjxdd.mvp.presenter;

import com.zjxdd.mvp.base.BasePresenter;
import com.zjxdd.mvp.contract.IPoetryContract;
import com.zjxdd.mvp.entity.PoetryEntity;
import com.zjxdd.mvp.model.PoetryModel;
import com.zjxdd.mvp.utils.RxJavaUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * $
 *
 * @author admin
 * @date 2020-03-20 11:32
 */
public class PoetryPresenter extends BasePresenter<IPoetryContract.IpoetryView> implements IPoetryContract.IpoetryPresenter {

    private static PoetryPresenter instance = null;
    private final PoetryModel mPoetryModel;
    private PoetryEntity mPoetryEntity;

    private PoetryPresenter() {
        mPoetryModel = PoetryModel.getInstance();
    }

    public static PoetryPresenter getInstance() {
        synchronized (PoetryPresenter.class) {
            if (instance == null) {
                instance = new PoetryPresenter();
            }
        }
        return instance;
    }


    @Override
    public void getPoetry() {
        Observable<PoetryEntity> observable = mPoetryModel.getPoetry().doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                addDisposable(disposable);
            }
        });

        observable = RxJavaUtils.toSubscribe(observable);
        observable.subscribe(new Observer<PoetryEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PoetryEntity poetryEntity) {
                mPoetryEntity = poetryEntity;
            }

            @Override
            public void onError(Throwable e) {
                getView().onError(e.getMessage());
            }

            @Override
            public void onComplete() {
                getView().searchSuccess(mPoetryEntity.getContent());
            }
        });
    }
}
