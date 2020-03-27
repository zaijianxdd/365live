package com.zjxdd.mvp.contract;

import com.zjxdd.mvp.base.BaseView;
import com.zjxdd.mvp.entity.BaseEnity;
import com.zjxdd.mvp.entity.PoetryEntity;

import io.reactivex.Observable;

/**
 * $
 *
 * @author admin
 * @date 2020-03-20 11:06
 */
public interface ILoginContract {

    interface IpoetryModel {
        Observable<BaseEnity> userLogin();
    }

    interface IloginPresenter {
        void userLogin();
    }

    interface IloginView extends BaseView {
        void loginSuccess();
    }
}
