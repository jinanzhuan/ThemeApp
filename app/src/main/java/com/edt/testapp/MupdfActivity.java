package com.edt.testapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.method.PasswordTransformationMethod;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.artifex.mupdf.viewer.MuPDFCore;
import com.artifex.mupdf.viewer.PageAdapter;
import com.artifex.mupdf.viewer.ReaderView;
import com.artifex.mupdf.viewer.SearchTaskResult;
import com.edt.testapp.base.BaseActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/03/19
 *     desc   :
 *     modify :
 * </pre>
 */

public class MupdfActivity extends BaseActivity {

    @InjectView(R.id.toolbar_ecg_detail)
    Toolbar mToolbarEcgDetail;
    @InjectView(R.id.tv_ecg_detail_title)
    TextView mTvEcgDetailTitle;
    @InjectView(R.id.bt_p_ecg_detail_save)
    TextView mBtPEcgDetailSave;
    @InjectView(R.id.rl_ecg_detail_top)
    RelativeLayout mRlEcgDetailTop;
    @InjectView(R.id.tv_ecg_detail)
    TextView mTvEcgDetail;
    @InjectView(R.id.iv_ecg_detail_ecg)
    ReaderView mDocView;
    @InjectView(R.id.phoneviewcard)
    CardView mPhoneviewcard;
    @InjectView(R.id.tv_ecgdetail_tag_report)
    TextView mTvEcgdetailTagReport;
    @InjectView(R.id.bt_ecg_detail_zoom)
    Button mBtEcgDetailZoom;
    @InjectView(R.id.bt_ecg_3)
    Button mBtEcg3;
    @InjectView(R.id.bt_ecg_24)
    Button mBtEcg24;
    @InjectView(R.id.ll_ecg_detail_shrink)
    CardView mLlEcgDetailShrink;
    @InjectView(R.id.scroll_ecg)
    ScrollView mScrollEcg;

    private MuPDFCore core;
    private String mFileName;
    private EditText mPasswordView;
    private AlertDialog.Builder mAlertBuilder;

    @Override
    public void onBaseCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mupdf);
        ButterKnife.inject(this);
        mAlertBuilder = new AlertDialog.Builder(this);

        if (core == null) {
            if (savedInstanceState != null && savedInstanceState.containsKey("FileName")) {
                mFileName = savedInstanceState.getString("FileName");
            }
        }
        if (core == null) {
            Intent intent = getIntent();
            byte buffer[] = null;

            if (Intent.ACTION_VIEW.equals(intent.getAction())) {
                Uri uri = intent.getData();
                System.out.println("URI to open is: " + uri);
                if (uri.getScheme().equals("file")) {
                    String path = uri.getPath();
                    core = openFile(path);
                } else {
                    try {
                        InputStream is = getContentResolver().openInputStream(uri);
                        int len;
                        ByteArrayOutputStream bufferStream = new ByteArrayOutputStream();
                        byte[] data = new byte[16384];
                        while ((len = is.read(data, 0, data.length)) != -1) {
                            bufferStream.write(data, 0, len);
                        }
                        bufferStream.flush();
                        buffer = bufferStream.toByteArray();
                        is.close();
                    } catch (IOException e) {
                        String reason = e.toString();
                        Resources res = getResources();
                        AlertDialog alert = mAlertBuilder.create();
                        setTitle(String.format(Locale.ROOT, res.getString(R.string.cannot_open_document_Reason), reason));
                        alert.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.dismiss),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });
                        alert.show();
                        return;
                    }
                    core = openBuffer(buffer, intent.getType());
                }
                SearchTaskResult.set(null);
            }
            if (core != null && core.needsPassword()) {
                requestPassword(savedInstanceState);
                return;
            }
            if (core != null && core.countPages() == 0) {
                core = null;
            }
        }
        if (core == null) {
            AlertDialog alert = mAlertBuilder.create();
            alert.setTitle(R.string.cannot_open_document);
            alert.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.dismiss),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    finish();
                }
            });
            alert.show();
            return;
        }

        createUI(savedInstanceState);
    }

    public void requestPassword(final Bundle savedInstanceState) {
        mPasswordView = new EditText(this);
        mPasswordView.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        mPasswordView.setTransformationMethod(new PasswordTransformationMethod());

        AlertDialog alert = mAlertBuilder.create();
        alert.setTitle(R.string.enter_password);
        alert.setView(mPasswordView);
        alert.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.okay),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (core.authenticatePassword(mPasswordView.getText().toString())) {
                            createUI(savedInstanceState);
                        } else {
                            requestPassword(savedInstanceState);
                        }
                    }
                });
        alert.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.cancel),
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        alert.show();
    }

    public void createUI(Bundle savedInstanceState) {
        if (core == null)
            return;

        // Now create the UI.
        // First create the document view
//        mDocView = new ReaderView(this);
        mDocView.setAdapter(new PageAdapter(this, core));


        // Make the buttons overlay, and store all its
        // controls in variables
//		makeButtonsView();

        // Set up the page slider
        int smax = Math.max(core.countPages() - 1, 1);


        // Reenstate last state if it was recorded
        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        mDocView.setDisplayedViewIndex(prefs.getInt("page" + mFileName, 0));


        // Stick the document view and the buttons overlay into a parent view
//        RelativeLayout layout = new RelativeLayout(this);
//        layout.addView(mDocView);
//        setContentView(layout);
    }

    private MuPDFCore openFile(String path) {
        int lastSlashPos = path.lastIndexOf('/');
        mFileName = new String(lastSlashPos == -1
                ? path
                : path.substring(lastSlashPos + 1));
        System.out.println("Trying to open " + path);
        try {
            core = new MuPDFCore(path);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } catch (OutOfMemoryError e) {
            //  out of memory is not an Exception, so we catch it separately.
            System.out.println(e);
            return null;
        }
        return core;
    }

    private MuPDFCore openBuffer(byte buffer[], String magic) {
        System.out.println("Trying to open byte buffer");
        try {
            core = new MuPDFCore(buffer, magic);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return core;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
