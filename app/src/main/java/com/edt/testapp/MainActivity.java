package com.edt.testapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.edt.testapp.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.rl_top)
    RelativeLayout mRlTop;
    @InjectView(R.id.base)
    RelativeLayout mBase;
    @InjectView(R.id.btn_skip_image)
    Button mBtnSkipImage;
    @InjectView(R.id.btn_fragment)
    Button mBtnFragment;
    @InjectView(R.id.btn_theme_test)
    Button mBtnThemeTest;
    @InjectView(R.id.btn_status_bar)
    Button mBtnStatusBar;
    @InjectView(R.id.btn_user_define)
    Button mBtnUserDefine;

    @Override
    public void onBaseCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @Override
    public void initListener() {
        mBtnSkipImage.setOnClickListener(this);
        mBtnThemeTest.setOnClickListener(this);
        mBtnFragment.setOnClickListener(this);
        mBtnStatusBar.setOnClickListener(this);
        mBtnUserDefine.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_skip_image:
                ImageThemeActivity.actionStart(this);
                break;
            case R.id.btn_theme_test:
                ThemeTestActivity.actionStart(this);
                break;
            case R.id.btn_fragment:
                FragmentActivity.actionStart(this);
                break;
            case R.id.btn_status_bar:
                StatusBarActivity.actionStart(mContext);
                break;
            case R.id.btn_user_define:
                UserDefinedActivity.actionStart(mContext);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
