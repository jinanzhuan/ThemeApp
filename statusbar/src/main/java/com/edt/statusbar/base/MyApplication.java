package com.edt.statusbar.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/03/19
 *     desc   :
 *     modify :
 * </pre>
 */

public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
