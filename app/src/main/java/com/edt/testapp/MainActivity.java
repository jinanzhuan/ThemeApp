package com.edt.testapp;

import android.graphics.Color;
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
    @InjectView(R.id.btn_red)
    Button mBtnRed;
    @InjectView(R.id.btn_blue)
    Button mBtnBlue;
    @InjectView(R.id.btn_orange)
    Button mBtnOrange;
    @InjectView(R.id.btn_prink)
    Button mBtnPrink;
    @InjectView(R.id.btn_skip_image)
    Button mBtnSkipImage;

    @Override
    public void onBaseCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @Override
    public void initListener() {
        mBtnRed.setOnClickListener(this);
        mBtnBlue.setOnClickListener(this);
        mBtnOrange.setOnClickListener(this);
        mBtnPrink.setOnClickListener(this);
        mBtnSkipImage.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_red:
                mBase.setBackgroundColor(Color.parseColor("#FF0000"));
                mRlTop.setBackgroundColor(Color.parseColor("#FF0000"));
                break;
            case R.id.btn_blue:
                mBase.setBackgroundColor(Color.parseColor("#0000FF"));
                mRlTop.setBackgroundColor(Color.parseColor("#0000FF"));
                break;
            case R.id.btn_orange:
                mBase.setBackgroundColor(Color.parseColor("#FFA500"));
                mRlTop.setBackgroundColor(Color.parseColor("#FFA500"));
                break;
            case R.id.btn_prink:
                mBase.setBackgroundColor(Color.parseColor("#A52A2A"));
                mRlTop.setBackgroundColor(Color.parseColor("#A52A2A"));
                break;
            case R.id.btn_skip_image:
                ImageThemeActivity.actionStart(this);
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
