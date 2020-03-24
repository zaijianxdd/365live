package com.zjxdd.mvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zjxdd.mvp.R;
import com.zjxdd.mvp.base.BaseFragment;
import com.zjxdd.mvp.base.BasePresenter;
import com.zjxdd.mvp.contract.IPoetryContract;
import com.zjxdd.mvp.presenter.PoetryPresenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * $
 *
 * @author admin
 * @date 2020-03-20 11:37
 */
public class PoetryFragment extends BaseFragment<PoetryPresenter> implements IPoetryContract.IpoetryView {
    @BindView(R.id.btn_get_poetry)
    Button btnGetPoetry;
    @BindView(R.id.tv_poetry_author)
    TextView tvPoetryAuthor;

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public PoetryPresenter initPresenter() {
        return PoetryPresenter.getInstance();
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
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_get_poetry)
    public void onViewClicked() {
        getPresenter().getPoetry();
    }
}
