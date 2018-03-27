package com.artifex.mupdf.viewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/02/24
 *     desc   :
 *     modify :
 * </pre>
 */

public class MyReaderView extends ReaderView {
    private Context mContext;
    private ShapeDrawable drawable;
    private Paint mPaint;
    private float mCurrentX;
    private float mCurrentY;
    private ArrayList<PointModel> points;
    private final Path mPath = new Path();
    private final Matrix matrix = new Matrix();
    // 放大镜的半径
    private int RADIUS;
    // 放大倍数
    private int FACTOR;
    private Bitmap mBmp;
    private boolean showManifier = false;
    private double mUnitTime;
    private double mUnitMv;
    private boolean isCanMeasure;
    private float mPreY;

    public MyReaderView(Context context) {
        this(context, null);
    }

    public MyReaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyReaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initData(context);
    }

    private void initData(Context context) {
        mContext = context;
        isCanMeasure = true;
        RADIUS = CommonUtils.dip2px(context, 50);
        FACTOR = 3;

        mPaint = new Paint();
        mPaint.setStrokeWidth(3);
        mPaint.setColor(Color.GREEN);

        points = new ArrayList<>();

        mPath.addCircle(RADIUS, RADIUS, RADIUS, Path.Direction.CW);
        matrix.setScale(FACTOR, FACTOR);

        DisplayMetrics dm = getResources().getDisplayMetrics();

        int width = dm.widthPixels;

        double unitWidth = (double) width / (42 * 5);      //每个小格的宽度

        //单位时间
        mUnitTime = 40 / unitWidth;
        //单位毫伏
        mUnitMv = 0.1 / unitWidth;

        setDrawingCacheEnabled(true);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(isCanMeasure) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mCurrentX = event.getX();
                    mCurrentY = event.getY();
                    showManifier = false;
                    mBmp = getDrawingCache(true);
                    break;

                case MotionEvent.ACTION_MOVE:
                    mCurrentX = event.getX();
                    mCurrentY = event.getY();
                    showManifier = true;
                    break;

                case MotionEvent.ACTION_UP:
                    mCurrentX = event.getX();
                    mCurrentY = event.getY();
                    showManifier = false;
                    PointModel pointModel = new PointModel(mCurrentX, mCurrentY);
                    points.add(pointModel);
                    break;
            }
            invalidate();
            return super.onTouchEvent(event);
        }else {
            return false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(isCanMeasure) {
            if (points.size() == 0) {
                canvas.drawCircle(mCurrentX, mCurrentY, 3, mPaint);
            } else if (points.size() == 1) {
                canvas.drawCircle(points.get(0).getX(), points.get(0).getY(), 3, mPaint);
                canvas.drawCircle(mCurrentX, mCurrentY, 3, mPaint);
            } else {
                int size = points.size();

                PointModel firstPoint = points.get(size - 2);
                PointModel secondPoint = points.get(size - 1);

                canvas.drawCircle(firstPoint.getX(), firstPoint.getY(), 3, mPaint);
                canvas.drawCircle(secondPoint.getX(), secondPoint.getY(), 3, mPaint);

                canvas.drawLine(firstPoint.getX(), firstPoint.getY(), firstPoint.getX(), firstPoint.getY() - 30, mPaint);
                canvas.drawLine(firstPoint.getX(), firstPoint.getY() - 30, secondPoint.getX(), firstPoint.getY() - 30, mPaint);
                canvas.drawLine(secondPoint.getX(), firstPoint.getY() - 30, secondPoint.getX(), secondPoint.getY(), mPaint);

                mPaint.setTextAlign(Paint.Align.CENTER);

                float X = Math.abs(secondPoint.getX() - firstPoint.getX());
                float Y = Math.abs(secondPoint.getY() - firstPoint.getY());

                DecimalFormat df = new DecimalFormat("#.##");

                double measureTime = mUnitTime * X;
                double measureMv = mUnitMv * Y;
                mPaint.setTextSize(CommonUtils.dip2px(mContext, 12));
                canvas.drawText(df.format(measureTime) + "ms   " + df.format(measureMv) + "mv", Math.abs((firstPoint.getX() - secondPoint.getX()) / 2) + getMin(firstPoint.getX(), secondPoint.getX()), firstPoint.getY() - 40, mPaint);

                points.clear();
            }


            if (showManifier) {

                BitmapShader shader = new BitmapShader(Bitmap.createScaledBitmap(mBmp,
                        mBmp.getWidth() * FACTOR, mBmp.getHeight() * FACTOR, true),
                        Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

                // 圆形的drawable
                OvalShape ovalShape = new OvalShape();

                drawable = new ShapeDrawable(ovalShape);
                drawable.getPaint().setShader(shader);

                matrix.setTranslate(RADIUS - mCurrentX * FACTOR, RADIUS - mCurrentY * FACTOR);
                drawable.getPaint().getShader().setLocalMatrix(matrix);

                // bounds，就是那个圆的外切矩形
                drawable.setBounds((int) (mCurrentX - RADIUS),
                        (int) (mCurrentY - RADIUS - CommonUtils.dip2px(mContext, 80)),
                        (int) (mCurrentX + RADIUS),
                        (int) (mCurrentY + RADIUS - CommonUtils.dip2px(mContext, 80)));

                drawable.draw(canvas);

                canvas.drawCircle(mCurrentX, mCurrentY - CommonUtils.dip2px(mContext, 60) - (RADIUS / 2), 3, mPaint);

            }
        }
        super.onDraw(canvas);

    }

    public float getMin(double x, double y) {
        return (float) (x < y ? x : y);
    }
    /**
     * 是否是测量状态
     * @param isCanMeasure
     */
    public void setMeasure(boolean isCanMeasure) {
        this.isCanMeasure = isCanMeasure;
//        if(isCanMeasure) {
//            setOnTouch(false);
//        }
    }

    private void setOnTouch(final boolean b) {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return b;
            }
        });
    }
}
