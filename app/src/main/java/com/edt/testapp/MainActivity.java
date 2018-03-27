package com.edt.testapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.edt.framework_common.utils.PermissionUtils;
import com.edt.testapp.base.BaseActivity;
import com.yanzhenjie.permission.PermissionListener;

import java.io.File;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.rl_top)
    RelativeLayout mRlTop;
    @InjectView(R.id.base)
    RelativeLayout mBase;
    @InjectView(R.id.btn_skip_image)
    Button mBtnSkipImage;
    @InjectView(R.id.btn_fragment)
    Button mBtnFragment;
    @InjectView(R.id.btn_theme_test)
    Button mBtnThemeTest;
    @InjectView(R.id.btn_status_bar)
    Button mBtnStatusBar;
    @InjectView(R.id.btn_user_define)
    Button mBtnUserDefine;
    @InjectView(R.id.btn_mupdf)
    Button mBtnMupdf;

    @Override
    public void onBaseCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @Override
    public void initListener() {
        mBtnSkipImage.setOnClickListener(this);
        mBtnThemeTest.setOnClickListener(this);
        mBtnFragment.setOnClickListener(this);
        mBtnStatusBar.setOnClickListener(this);
        mBtnUserDefine.setOnClickListener(this);
        mBtnMupdf.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_skip_image:
                ImageThemeActivity.actionStart(this);
                break;
            case R.id.btn_theme_test:
                ThemeTestActivity.actionStart(this);
                break;
            case R.id.btn_fragment:
                FragmentActivity.actionStart(this);
                break;
            case R.id.btn_status_bar:
                StatusBarActivity.actionStart(mContext);
                break;
            case R.id.btn_user_define:
                UserDefinedActivity.actionStart(mContext);
                break;

            case R.id.btn_mupdf:
                if(PermissionUtils.hasPermission(mContext, 100, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    startToMuPdf();
                }

                break;
        }
    }

    private void startToMuPdf() {
        Log.e("TAG", "getPackageCodePath="+getPackageCodePath());
        Log.e("TAG", "getPackageResourcePath="+getPackageResourcePath());
        Log.e("TAG", "getCacheDir="+getCacheDir());
        Log.e("TAG", "getDir(“test”, Context.MODE_PRIVATE)="+getDir("", Context.MODE_PRIVATE));
        Log.e("TAG", "getExternalCacheDir="+getExternalCacheDir());
        Log.e("TAG", "getExternalFilesDir="+getExternalFilesDir("test"));
        Log.e("TAG", "getExternalFilesDir="+getExternalFilesDir(null));
        Log.e("TAG", "getFilesDir="+getFilesDir());
        File file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath(), "ecg.pdf");
        if(file.exists()) {
            Uri uri = Uri.fromFile(file);
            Intent intent = new Intent(this, MupdfActivity.class);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(uri);
            startActivity(intent);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults, new PermissionListener() {
            @Override
            public void onSucceed(int requestCode, List<String> grantPermissions) {
                startToMuPdf();
            }

            @Override
            public void onFailed(int requestCode, List<String> deniedPermissions) {
                if(requestCode == 100) {
                    PermissionUtils.FailedForPermissions(mContext, requestCode, deniedPermissions);
                }
            }
        });
    }
}
