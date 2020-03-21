package com.zjxdd.mvp.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjxdd.mvp.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * $
 *
 * @author admin
 * @date 2020-03-20 11:02
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    private Context mContext;
    private Bundle mBundle;
    private P mPresenter;
    private View view;
    private Unbinder unbinder;

    /*
     *
     * 恢复数据
     * */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mBundle != null) {
            outState.putBundle("bundle", mBundle);
        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        mPresenter.detachView();
        super.onDetach();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mBundle = getArguments() == null ? new Bundle() : getArguments();
        } else {
            mBundle = savedInstanceState.getBundle("bundle");
        }
        mPresenter = initPresenter();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = initView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void startFragment(Fragment target) {
        startFragment(target, null);

    }

    public void startFragment(Fragment target, String tag) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.hide(this).add(R.id.content, target, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commitAllowingStateLoss();


    }

    public void onBack() {
        getFragmentManager().popBackStack();
    }

    public abstract View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    protected P getPresenter() {
        return mPresenter;
    }


    public abstract P initPresenter();


    @Nullable
    @Override
    public Context getContext() {
        return mContext;
    }

    public Bundle getBundle() {
        return mBundle;
    }

    public Fragment getFragment() {
        return this;
    }
}
