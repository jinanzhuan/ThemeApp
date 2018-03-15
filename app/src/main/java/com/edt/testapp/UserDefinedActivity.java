package com.edt.testapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.edt.testapp.base.BaseActivity;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/03/15
 *     desc   :
 *     modify :
 * </pre>
 */

public class UserDefinedActivity extends BaseActivity {
    @Override
    public void onBaseCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_user_defined);
    }

    public static void actionStart(Context context){
        Intent intent = new Intent(context, UserDefinedActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
}
