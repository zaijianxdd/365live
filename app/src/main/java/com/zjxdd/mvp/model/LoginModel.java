package com.zjxdd.mvp.model;

import com.zjxdd.mvp.contract.ILoginContract;
import com.zjxdd.mvp.entity.BaseEnity;
import com.zjxdd.mvp.iApiService.IGetPoetryEntity;
import com.zjxdd.mvp.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * $
 *
 * @author admin
 * @date 2020-03-26 14:01
 */
public class LoginModel implements ILoginContract.IpoetryModel {
    private static LoginModel instance = null;


    private LoginModel() {
    }

    public static LoginModel getInstance() {
        synchronized (LoginModel.class) {
            if (instance == null) {
                instance = new LoginModel();
            }
        }
        return instance;
    }

    @Override
    public Observable<BaseEnity> userLogin() {
        return RetrofitManager.getInstance().createRs(IGetPoetryEntity.class).userLogin();
    }
}
