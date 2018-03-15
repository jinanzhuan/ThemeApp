package com.edt.testapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.edt.testapp.base.BaseActivity;

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

public class ThemeTestActivity extends BaseActivity implements View.OnClickListener {
    @InjectView(R.id.btn_red)
    Button mBtnRed;
    @InjectView(R.id.btn_blue)
    Button mBtnBlue;
    @InjectView(R.id.btn_orange)
    Button mBtnOrange;
    @InjectView(R.id.btn_prink)
    Button mBtnPrink;
    @InjectView(R.id.rl_top)
    RelativeLayout mRlTop;
    @InjectView(R.id.base)
    RelativeLayout mBase;

    @Override
    public void onBaseCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_theme_test);
        ButterKnife.inject(this);
        setSystemFit();
    }

    public static void actionStart(Context context){
        Intent intent = new Intent(context, ThemeTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initListener() {
        mBtnRed.setOnClickListener(this);
        mBtnBlue.setOnClickListener(this);
        mBtnOrange.setOnClickListener(this);
        mBtnPrink.setOnClickListener(this);
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
        }
    }
}
