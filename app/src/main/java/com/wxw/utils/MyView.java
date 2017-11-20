package com.wxw.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义控件
 * Created by Administrator on 2017/11/1.
 */
public class MyView extends View {

    private Paint mPaint_Black = new Paint();
    private Paint mPaint_Red = new Paint();

    public MyView(Context context) {
        super(context);
        initPaint();
//        recording();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
//        recording();
    }

    private void initPaint() {
        mPaint_Black.setColor(Color.BLACK);
        mPaint_Black.setStrokeWidth(2f);
        mPaint_Black.setStyle(Paint.Style.STROKE);
        mPaint_Black.setTextSize(50);

        mPaint_Red.setColor(Color.RED);
        mPaint_Red.setStrokeWidth(2f);
        mPaint_Red.setStyle(Paint.Style.FILL);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, 0, 500, 800, mPaint_Black);
        canvas.drawCircle(500, 800, 100, mPaint_Red);
        canvas.drawText("helloworld",1,6,500,800,mPaint_Black);

//        mPicture.draw(canvas);
//        canvas.drawPicture(mPicture,new Rect(0,0,mPicture.getWidth(),200));

//        PictureDrawable drawable = new PictureDrawable(mPicture);
//        // 设置绘制区域 -- 注意此处所绘制的实际内容不会缩放
//        drawable.setBounds(0,0,250,mPicture.getHeight());
//        // 绘制
//        drawable.draw(canvas);
    }

    //创建 Picture
    private Picture mPicture = new Picture();


    //录制picture内容、方法
    private void recording() {

        //开始录制
        Canvas canvas = mPicture.beginRecording(500,500);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        canvas.translate(250,250);
        canvas.drawCircle(0,0,50,paint);
        mPicture.endRecording();

    }


}
