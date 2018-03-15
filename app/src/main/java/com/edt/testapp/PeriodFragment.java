package com.edt.testapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edt.testapp.base.BaseFragment;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/03/14
 *     desc   :
 *     modify :
 * </pre>
 */

public class PeriodFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("fragment", "onCreateView");
        View view = View.inflate(getActivity(), R.layout.fragment_period, null);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("fragment", "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("fragment", "onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("fragment", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("fragment", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("fragment", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("fragment", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("fragment", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("fragment", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("fragment", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("fragment", "onDetach");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("fragment", "setUserVisibleHint="+isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("fragment", "onHiddenChanged="+hidden);
    }
}
