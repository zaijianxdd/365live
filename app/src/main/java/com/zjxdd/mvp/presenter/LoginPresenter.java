package com.zjxdd.mvp.presenter;

import com.zjxdd.mvp.base.BasePresenter;
import com.zjxdd.mvp.contract.ILoginContract;

/**
 * $
 *
 * @author admin
 * @date 2020-03-26 13:56
 */
public class LoginPresenter extends BasePresenter<ILoginContract.IloginView> implements ILoginContract.IloginPresenter {
    private static LoginPresenter instance = null;

    private LoginPresenter() {
    }

    public static LoginPresenter getInstance() {
        synchronized (LoginPresenter.class) {
            if (instance == null) {
                instance = new LoginPresenter();
            }
        }
        return instance;
    }

    @Override
    public void userLogin() {
        getView().loginSuccess();
    }
}
