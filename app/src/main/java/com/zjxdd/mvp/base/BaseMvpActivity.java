package com.zjxdd.mvp.base;

/**
 * $
 *
 * @author admin
 * @date 2020-03-20 11:02
 */
public abstract class BaseMvpActivity<P extends BasePresenter,V extends BaseView> extends BaseActivity implements BaseView {

    private P presenter;

    @Override
    protected void initPresenter() {
        presenter = createPresenter();
        if (presenter!=null){
            //presenter 绑定 view
            presenter.attachView(this);
        }
    }

    /*
    *
    * */
    protected  abstract P createPresenter();

    protected P getPresenter(){
        return  presenter;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            //presenter 销毁操作
            presenter.detachView();
        }
    }
}
