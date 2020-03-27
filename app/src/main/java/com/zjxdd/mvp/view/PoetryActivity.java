package com.zjxdd.mvp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zjxdd.mvp.R;
import com.zjxdd.mvp.base.BaseMvpActivity;
import com.zjxdd.mvp.base.MyApplication;
import com.zjxdd.mvp.contract.IPoetryContract;
import com.zjxdd.mvp.presenter.PoetryPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * $
 *
 * @author admin
 * @date 2020-03-20 11:37
 */
public class PoetryActivity extends BaseMvpActivity<PoetryPresenter, PoetryActivity> implements IPoetryContract.IpoetryView {
    @BindView(R.id.btn_get_poetry)
    Button btnGetPoetry;
    @BindView(R.id.tv_poetry_author)
    TextView tvPoetryAuthor;
    @BindView(R.id.btn_goto_fragment)
    Button btnGotoFragment;
    @BindView(R.id.ll)
    LinearLayout ll;

    @Override
    protected PoetryPresenter createPresenter() {
        return PoetryPresenter.getInstance();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_poetry;
    }

    @Override
    protected void initViews() {

    }

    @Override
    public void searchSuccess(String author) {
        tvPoetryAuthor.setText(author);
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


    @OnClick({R.id.btn_get_poetry, R.id.btn_goto_fragment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_poetry:
                getPresenter().getPoetry();
                break;
            case R.id.btn_goto_fragment:
                startFragment(R.id.ll,new PoetryFragment());
                break;
        }
    }
}
