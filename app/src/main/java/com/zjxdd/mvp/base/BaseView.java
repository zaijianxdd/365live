package com.zjxdd.mvp.base;

/**
 * $
 *
 * @author admin
 * @date 2020-03-20 11:03
 */
public interface BaseView {
    /*
     * 显示进度条
     * */
    void showProgressDialog();

    /*
     * 隐藏进度条
     * */
    void hideProgressDialog();

    /*
     * 出错回调
     *
     * */
    void onError(String message);
}
