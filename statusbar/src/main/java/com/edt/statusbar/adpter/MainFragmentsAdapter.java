package com.edt.statusbar.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.edt.statusbar.bean.FragmentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/05/11
 *     desc   :
 *     modify :
 * </pre>
 */

public class MainFragmentsAdapter extends FragmentPagerAdapter {
    private List<FragmentBean> mFragments = new ArrayList<>();

    public MainFragmentsAdapter(FragmentManager fm, List<FragmentBean> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position).getFragment();
    }
}
