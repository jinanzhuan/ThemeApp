package com.edt.statusbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.edt.statusbar.base.BaseActivity;
import com.edt.statusbar.utils.StatusBarCompat;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/03/19
 *     desc   :
 *     modify :
 * </pre>
 */

public class CodeThemeActivity extends BaseActivity {
    private RelativeLayout rl_title;

    @Override
    public void onBaseCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_code_theme);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setSystemFit();
//        StatusBarCompat.compat(mContext, Color.parseColor("#60b0e3"));
    }

    public static void actionStart(Context context){
        Intent intent = new Intent(context, CodeThemeActivity.class);
        context.startActivity(intent);
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
        StatusBarCompat.compat(mContext, Color.GRAY);
        rl_title.setBackgroundColor(Color.GRAY);
    }

    public void onBlue(View view){
        StatusBarCompat.compat(mContext, Color.BLUE);
        rl_title.setBackgroundColor(Color.BLUE);
    }

    public void onPrink(View view){
        StatusBarCompat.compat(mContext, Color.RED);
        rl_title.setBackgroundColor(Color.RED);
    }

}
