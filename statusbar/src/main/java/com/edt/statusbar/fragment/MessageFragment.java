package com.edt.statusbar.fragment;

import android.widget.TextView;

import com.edt.statusbar.R;
import com.edt.statusbar.base.BaseFragment;

import butterknife.InjectView;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/05/11
 *     desc   :
 *     modify :
 * </pre>
 */

public class MessageFragment extends BaseFragment {
    @InjectView(R.id.tv_me)
    TextView mTvMe;

    @Override
    public int getResourceId() {
        return R.layout.fragment_message;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

}
