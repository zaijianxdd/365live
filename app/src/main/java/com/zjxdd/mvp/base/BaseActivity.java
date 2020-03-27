package com.zjxdd.mvp.base;

import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.ButterKnife;

/**
 * $
 *
 * @author admin
 * @date 2020-02-26 17:49
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        initPresenter();
        initViews();
        ButterKnife.bind(this);
    }

    /*
     * 设置布局id
     * */
    protected abstract int setLayoutId();

    /*
     *实例化 presenter
     * */
    protected abstract void initPresenter();


    /*
     * 实例化view
     * */

    protected abstract void initViews();

    /*
     *启动fragment
     * */
    protected void startFragment(int id, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(id, fragment);
        fragmentTransaction.commit();
    }

}

