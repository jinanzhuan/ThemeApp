package com.edt.statusbar.adpter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

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

public class MainFragmentsAdapter extends PagerAdapter {
    private List<FragmentBean> mFragments = new ArrayList<>();

    public MainFragmentsAdapter(List<FragmentBean> fragments) {
        mFragments = fragments;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }
}
