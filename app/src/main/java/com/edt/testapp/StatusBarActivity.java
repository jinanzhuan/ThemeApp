package com.edt.testapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.edt.testapp.base.BaseActivity;
import com.edt.testapp.utils.StatusBarCompat;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/03/14
 *     desc   :
 *     modify :
 * </pre>
 */

public class StatusBarActivity extends BaseActivity implements View.OnClickListener {
    @InjectView(R.id.btn_red)
    Button mBtnRed;
    @InjectView(R.id.btn_gray)
    Button mBtnGray;
    @InjectView(R.id.btn_orange)
    Button mBtnOrange;
    @InjectView(R.id.btn_brown)
    Button mBtnBrown;
    @InjectView(R.id.btn_green)
    Button mBtnGreen;
    @InjectView(R.id.rl_title)
    RelativeLayout mRlTitle;

    @Override
    public void onBaseCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_status_bar);
        ButterKnife.inject(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setSystemFit();
        StatusBarCompat.compat(mContext, Color.parseColor("#60b0e3"));
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, StatusBarActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initListener() {
        mBtnRed.setOnClickListener(this);
        mBtnGray.setOnClickListener(this);
        mBtnOrange.setOnClickListener(this);
        mBtnBrown.setOnClickListener(this);
        mBtnGreen.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_red:
                StatusBarCompat.compat(mContext, Color.RED);
                mRlTitle.setBackgroundColor(Color.RED);
                break;
            case R.id.btn_gray:
                StatusBarCompat.compat(mContext, Color.GRAY);
                mRlTitle.setBackgroundColor(Color.GRAY);
                break;
            case R.id.btn_orange:
                StatusBarCompat.compat(mContext, Color.parseColor("#FFA500"));
                mRlTitle.setBackgroundColor(Color.parseColor("#FFA500"));
                break;
            case R.id.btn_brown:
                StatusBarCompat.compat(mContext, Color.parseColor("#A52A2A"));
                mRlTitle.setBackgroundColor(Color.parseColor("#A52A2A"));
                break;
            case R.id.btn_green:
                StatusBarCompat.compat(mContext, Color.GREEN);
                mRlTitle.setBackgroundColor(Color.GREEN);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }
}
