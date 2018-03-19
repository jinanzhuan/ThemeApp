package com.edt.statusbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.edt.statusbar.base.BaseActivity;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/03/19
 *     desc   :
 *     modify :
 * </pre>
 */

public class XmlThemeActivity extends BaseActivity {
    private RelativeLayout rl_title;
    private LinearLayout ll_base;

    @Override
    public void onBaseCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_xml_theme);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setSystemFit();
    }

    public static void actionStart(Context context){
        Intent intent = new Intent(context, XmlThemeActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initView() {
        super.initView();
        rl_title = (RelativeLayout)findViewById(R.id.rl_title);
        ll_base = (LinearLayout)findViewById(R.id.ll_base);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    public void onGray(View view){
        ll_base.setBackgroundColor(Color.GRAY);
        rl_title.setBackgroundColor(Color.YELLOW);
    }

    public void onBlue(View view){
        ll_base.setBackgroundColor(Color.BLUE);
        rl_title.setBackgroundColor(Color.YELLOW);
    }

    public void onPrink(View view){
        ll_base.setBackgroundColor(Color.RED);
        rl_title.setBackgroundColor(Color.YELLOW);
    }
}
