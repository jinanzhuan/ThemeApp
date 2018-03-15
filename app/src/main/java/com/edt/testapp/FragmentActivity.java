package com.edt.testapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

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

public class FragmentActivity extends BaseActivity {
    @InjectView(R.id.frameLayout)
    FrameLayout mFrameLayout;

    @Override
    public void onBaseCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fragment);
        ButterKnife.inject(this);
    }

    public static void actionStart(Context context){
        Intent intent = new Intent(context, FragmentActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initView() {
        super.initView();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, new PeriodFragment());
        transaction.commit();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

}
