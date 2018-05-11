package com.edt.statusbar.bean;

import com.edt.statusbar.base.BaseFragment;

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

}
