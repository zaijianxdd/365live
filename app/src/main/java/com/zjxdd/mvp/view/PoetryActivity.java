package com.zjxdd.mvp.view;
import android.widget.Toast;
import com.zjxdd.mvp.base.BaseMvpActivity;
import com.zjxdd.mvp.base.MyApplication;
import com.zjxdd.mvp.contract.IPoetryContract;
import com.zjxdd.mvp.presenter.PoetryPresenter;

/**
 * $
 *
 * @author admin
 * @date 2020-03-20 11:37
 */
public class PoetryActivity extends BaseMvpActivity<PoetryPresenter, PoetryActivity> implements IPoetryContract.IpoetryView {
    @Override
    protected PoetryPresenter createPresenter() {
        return null;
    }

    @Override
    protected int setLayoutId() {
        return 0;
    }

    @Override
    protected void initViews() {

    }

    @Override
    public void searchSuccess(String author) {
        Toast.makeText(MyApplication.getContext(), author, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void onError(String message) {
        Toast.makeText(MyApplication.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
