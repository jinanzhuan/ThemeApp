package com.edt.statusbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.edt.statusbar.base.BaseActivity;
import com.edt.statusbar.bean.FragmentBean;
import com.edt.statusbar.fragment.HomeFragment;
import com.edt.statusbar.view.NoScrollViewPager;

import butterknife.ButterKnife;
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

public class ImageThemeActivity extends BaseActivity {

    @InjectView(R.id.vp_fragment)
    NoScrollViewPager mVpFragment;
    @InjectView(R.id.tl_label)
    TabLayout mTlLabel;

    @Override
    public void onBaseCreate(Bundle savedInstanceState) {
        setStatusBar();
        setContentView(R.layout.activity_image_theme);
        ButterKnife.inject(this);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ImageThemeActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        FragmentBean bean = new FragmentBean();
        bean.setTitle("主页");
        bean.setFragment(new HomeFragment());
    }

    /**
     * 适配透明状态栏-沉浸式状态栏
     */
    protected void setStatusBar() {
        //4.4以上才有透明状态栏一说。低版本暂时无法适配
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //兼容5.0 状态栏半透明情况， 貌似并没有什么卵用(机型锤子T2 5.0系统)(跟厂商定制有关原生有用)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // Translucent status bar
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 获取状态栏的高度
     *
     * @return
     */
    protected int getStatusBarHeight() {
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }
}
