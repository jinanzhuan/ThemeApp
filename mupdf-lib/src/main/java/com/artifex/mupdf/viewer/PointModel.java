package com.artifex.mupdf.viewer;

/**
 * Created by lizhiqiang on 17-4-12.
 */
public class PointModel
{
    private float x;
    private float y;

    public PointModel(float x, float y)
    {
        this.x = x;
        this.y = y;
    }


    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }
}
