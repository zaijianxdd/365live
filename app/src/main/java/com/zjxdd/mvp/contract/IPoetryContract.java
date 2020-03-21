package com.zjxdd.mvp.contract;

import com.zjxdd.mvp.base.BaseView;
import com.zjxdd.mvp.entity.PoetryEntity;

import io.reactivex.Observable;

/**
 * $
 *
 * @author admin
 * @date 2020-03-20 11:06
 */
public interface IPoetryContract {

    interface IpoetryModel {
        Observable<PoetryEntity> getPoetry();
    }

    interface IpoetryPresenter {
        void getPoetry();
    }

    interface IpoetryView extends BaseView {
        void searchSuccess(String author);
    }
}
