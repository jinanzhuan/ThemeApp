package com.edt.statusbar.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/03/14
 *     desc   :
 *     modify :
 * </pre>
 */

public abstract class BaseFragment extends Fragment {
    private BaseActivity mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), getResourceId(), null);
        ButterKnife.inject(this, view);
        initArgument();
        initView();
        initListener();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (BaseActivity) getActivity();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    /**
     * init fragment's argument data
     */
    public void initArgument() {

    }

    /**
     * 返回fragment的布局id
     * @return
     */
    public abstract int getResourceId();

    /**
     * 初始化布局
     */
    public abstract void initView();

    /**
     * 初始化监听
     */
    public abstract void initListener();

    /**
     * 初始化数据
     */
    public abstract void initData();
}
