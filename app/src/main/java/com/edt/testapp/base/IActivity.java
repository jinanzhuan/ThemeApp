package com.edt.testapp.base;

import android.os.Bundle;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/03/13
 *     desc   :
 *     modify :
 * </pre>
 */

public interface IActivity {
    /**
     * 设置布局,即setContent()
     * @param savedInstanceState
     */
    void onBaseCreate(Bundle savedInstanceState);

    /**
     * 初始化事件监听
     */
    void initListener();

    /**
     * 初始化数据
     */
    void initData();
}
