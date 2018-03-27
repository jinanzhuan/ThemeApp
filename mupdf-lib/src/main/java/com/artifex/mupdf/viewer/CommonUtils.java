package com.artifex.mupdf.viewer;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/3/26.
 */
public class CommonUtils {
    public static int dip2px(Context context,float dipValue){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dipValue*scale+0.5f);
    }

    public static List<String> listRemoveSame(List<String> list)
    {
        ArrayList<String> unique = new ArrayList();
        if(list!=null){
        if(!list.isEmpty()){
         for(int i = 0;i<list.size();i++){
             String s = list.get(i);

             if(unique.isEmpty()){
             unique.add(s);
             }else {
                 boolean eq = false;
                for(int j = 0;j<unique.size();j++){
                   boolean b = TextUtils.equals(s, unique.get(j));
                    if(b==true){
                        eq = true;
                    }
                }

                 if(!eq){
                     unique.add(s);
                 }

             }



         }



        }}

        return unique;
    }

    public static int px2dp(Context context,float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5f);
    }

    public static byte[] getBitmapByte(Bitmap bitmap){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    public static Bitmap getBitmapFromByte(byte[] temp){
        if(temp != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
            return bitmap;
        }else{
            return null;
        }
    }

    public static void saveBitmap(Bitmap bm,File file) {
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Log.i("test", "已经保存");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
    public static boolean isMobileNumber(String mobiles) {
        Pattern p = Pattern
                .compile("^1[345678]\\d{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isDateExpird(String expird_time)
    {
        try
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            long expirdTime = simpleDateFormat.parse(expird_time + " 00:00:00").getTime();
            long currentTime = System.currentTimeMillis();

            if (currentTime < expirdTime)
            {
                return false;
            }else
            {
                return true;
            }

        } catch (ParseException e)
        {
            e.printStackTrace();
            return false;
        }

    }

    public static void call(Context context,String phone)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);

        context.startActivity(intent);
    }

    /**
     * 设置窗口的背景颜色变化，可用于popuwindow弹出时背景变暗
     * @param context
     * @param x
     */
    public static void setWindowBackground(Activity context, float x){
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = x;
        context.getWindow().setAttributes(lp);
    }

    /**
     * 得到当前页面名字
     * @param context
     * @return
     */
    public static String getCurrentActivityName(Context context){
        ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTasks = manager .getRunningTasks(1);
        ActivityManager.RunningTaskInfo cinfo = runningTasks.get(0);
        ComponentName component = cinfo.topActivity;
        return component.getClassName();
    }

}
