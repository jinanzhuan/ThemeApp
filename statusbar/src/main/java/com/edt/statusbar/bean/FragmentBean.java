package com.edt.statusbar.bean;

import com.edt.statusbar.R;
import com.edt.statusbar.base.BaseFragment;
import com.edt.statusbar.fragment.HomeFragment;
import com.edt.statusbar.fragment.MeFragment;
import com.edt.statusbar.fragment.MessageFragment;

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

public class FragmentBean {
    private String title;
    private int image;
    private int imageSelected;
    private BaseFragment fragment;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImageSelected() {
        return imageSelected;
    }

    public void setImageSelected(int imageSelected) {
        this.imageSelected = imageSelected;
    }

    public BaseFragment getFragment() {
        return fragment;
    }

    public void setFragment(BaseFragment fragment) {
        this.fragment = fragment;
    }

    public static List<FragmentBean> init(){
        List<FragmentBean> data = new ArrayList<>();

        FragmentBean bean = new FragmentBean();
        bean.setTitle("首页");
        bean.setFragment(new HomeFragment());
        bean.setImage(R.drawable.doctor_home_menu_home_gray);
        data.add(bean);

        bean = new FragmentBean();
        bean.setTitle("消息");
        bean.setFragment(new MessageFragment());
        bean.setImage(R.drawable.doctor_homemenumessagegray);
        data.add(bean);

        bean = new FragmentBean();
        bean.setTitle("我");
        bean.setFragment(new MeFragment());
        bean.setImage(R.drawable.doctor_homemenumygray);
        data.add(bean);

        return data;
    }
}
