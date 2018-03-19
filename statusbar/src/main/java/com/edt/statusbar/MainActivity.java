package com.edt.statusbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.edt.statusbar.base.BaseActivity;
import com.edt.statusbar.utils.StatusBarCompat;

public class MainActivity extends BaseActivity {
    private RelativeLayout rl_title;

    @Override
    public void onBaseCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        super.initView();
        rl_title = (RelativeLayout)findViewById(R.id.rl_title);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    public void onGray(View view){
        CodeThemeActivity.actionStart(mContext);
    }

    public void onBlue(View view){
        XmlThemeActivity.actionStart(mContext);
    }

    public void onPrink(View view){
        StatusBarCompat.compat(mContext, Color.RED);
        rl_title.setBackgroundColor(Color.RED);
    }
}
