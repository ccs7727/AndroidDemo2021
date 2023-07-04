package com.example.androiddemo.ui.touchsign;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class GestureSignatureView extends View {

    private static final String TAG = "GestureSignatureView";
    private Path mPath;//绘制路径
    private Paint mPaint;// 绘制画笔
    private Canvas mCanvas;//背景画布
    private Bitmap mMBitmap;//背景bitmap
    private boolean isTouchedSignature = false;//是否签名 默认为false

    public GestureSignatureView(Context context) {
        this(context, null);
    }

    public GestureSignatureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GestureSignatureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure: 测量的宽高：" + getMeasuredWidth() + "-----------" + getMeasuredHeight());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mMBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mMBitmap);
        mCanvas.drawColor(Color.TRANSPARENT);
        mCanvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10.0f);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setDither(true);
        mPath = new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        canvas.drawBitmap(mMBitmap, 0, 0, mPaint);
        // 通过画布绘制多点形成的图形
        canvas.drawPath(mPath, mPaint);
    }

    private float[] downPoint = new float[2];

    private float[] previousPoint = new float[2];

    /**
     * 监听触摸事件的回调
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取距离自身（点击位置）左边界的距离
        downPoint[0] = event.getX();
        //获取距离自身（点击位置）上边界的距离
        downPoint[1] = event.getY();
        switch (event.getAction()) {
            //手势开始
            case MotionEvent.ACTION_DOWN:
                previousPoint[0] = downPoint[0];
                previousPoint[1] = downPoint[1];
                // moveTo 不会进行绘制，只用于移动移动画笔。
                mPath.moveTo(downPoint[0], downPoint[1]);
                break;
            //手势过程
            case MotionEvent.ACTION_MOVE:
                float dX = Math.abs(downPoint[0] - previousPoint[0]);
                float dY = Math.abs(downPoint[1] - previousPoint[1]);

                // 两点之间的距离大于等于3时，生成贝塞尔绘制曲线
                if (dX >= 3 || dY >= 3) {
                    // 设置贝塞尔曲线的操作点为起点和终点的一半
                    float cX = (downPoint[0] + previousPoint[0]) / 2;
                    float cY = (downPoint[1] + previousPoint[1]) / 2;
                    //quadTo 用于绘制圆滑曲线，即贝塞尔曲线 二次贝塞尔，实现平滑曲线；previousX, previousY为操作点，cX, cY为终点
                    mPath.quadTo(previousPoint[0], previousPoint[1], cX, cY);
                    // 第二次执行时，第一次结束调用的坐标值将作为第二次调用的初始坐标值
                    previousPoint[0] = downPoint[0];
                    previousPoint[1] = downPoint[1];
                }
                break;
            //手势结束
            case MotionEvent.ACTION_UP:
                //设置签名成功状态
                isTouchedSignature = true;
                mCanvas.drawPath(mPath, mPaint);
                mPath.reset();
                break;
        }

        invalidate();
        return true;
    }

    public void clear() {
        if (mCanvas != null) {
            isTouchedSignature = false;
            mPath.reset();
            mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            invalidate();
        }
    }

    /**
     * 保存画板
     *
     * @param path 保存到路径
     */

    public void save(String path) {
        try {
            save(path, true, 50);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存画板
     *
     * @param path       保存到路径
     * @param clearBlank 是否清除空白区域
     * @param blank      边缘空白区域
     */
    public void save(String path, boolean clearBlank, int blank) throws IOException {

        Bitmap bitmap = mMBitmap;
        if (clearBlank) {
            bitmap = clearBlank(mMBitmap, blank);
        }
        Bitmap littleBmp = ConstantsUtil.smallImage(bitmap, 700);
        Bitmap newBitmap = Bitmap.createBitmap(littleBmp.getWidth(), littleBmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        canvas.drawBitmap(littleBmp, 0, 0, null);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 50, bos);
        byte[] buffer = bos.toByteArray();
        if (buffer != null) {
            File file = new File(path);

            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(buffer);
            outputStream.close();
        }
    }

    /**
     * 是否有签名
     *
     * @return
     */
    public boolean getTouched() {
        return isTouchedSignature;
    }

    /**
     * 逐行扫描 清除边界空白。
     *
     * @param bp
     * @param blank 边距留多少个像素
     * @return
     */
    private Bitmap clearBlank(Bitmap bp, int blank) {
        int HEIGHT = bp.getHeight();
        int WIDTH = bp.getWidth();
        int top = 0, left = 0, right = 0, bottom = 0;
        int[] pixs = new int[WIDTH];
        boolean isStop;
        for (int y = 0; y < HEIGHT; y++) {
            bp.getPixels(pixs, 0, WIDTH, 0, y, WIDTH, 1);
            isStop = false;
            for (int pix : pixs) {
                if (pix != Color.TRANSPARENT) {
                    top = y;
                    isStop = true;
                    break;
                }
            }
            if (isStop) {
                break;
            }
        }
        for (int y = HEIGHT - 1; y >= 0; y--) {
            bp.getPixels(pixs, 0, WIDTH, 0, y, WIDTH, 1);
            isStop = false;
            for (int pix : pixs) {
                if (pix != Color.TRANSPARENT) {
                    bottom = y;
                    isStop = true;
                    break;
                }
            }
            if (isStop) {
                break;
            }
        }
        pixs = new int[HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            bp.getPixels(pixs, 0, 1, x, 0, 1, HEIGHT);
            isStop = false;
            for (int pix : pixs) {
                if (pix != Color.TRANSPARENT) {
                    left = x;
                    isStop = true;
                    break;
                }
            }
            if (isStop) {
                break;
            }
        }
        for (int x = WIDTH - 1; x > 0; x--) {
            bp.getPixels(pixs, 0, 1, x, 0, 1, HEIGHT);
            isStop = false;
            for (int pix : pixs) {
                if (pix != Color.TRANSPARENT) {
                    right = x;
                    isStop = true;
                    break;
                }
            }
            if (isStop) {
                break;
            }
        }
        if (blank < 0) {
            blank = 0;
        }
        left = left - blank > 0 ? left - blank : 0;
        top = top - blank > 0 ? top - blank : 0;
        right = right + blank > WIDTH - 1 ? WIDTH - 1 : right + blank;
        bottom = bottom + blank > HEIGHT - 1 ? HEIGHT - 1 : bottom + blank;
        return Bitmap.createBitmap(bp, left, top, right - left, bottom - top);
    }

}
