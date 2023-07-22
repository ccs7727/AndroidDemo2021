package com.example.androiddemo.ui.watermark;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * 图片添加水印，文字  水印  边框
 */
public class WatermarkView extends AppCompatImageView {

    private Paint mPaint = new Paint();
    private int mWidth, mHeight;
    private int mTextSize = 30;
    private String mText;//文字
    private Bitmap mLogo;//水印
    private Bitmap mFrame;//边框

    public WatermarkView(Context context) {
        super(context);
    }

    public WatermarkView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setColor(Color.parseColor("#8800FF"));
        mPaint.setTextSize(dp2px(context, mTextSize));
    }

    private int dp2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = 300 * 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mFrame != null) {
            canvas.drawBitmap(mFrame, null, new Rect(0, 0, mWidth, mHeight), mPaint);
        }
        if (!TextUtils.isEmpty(mText)) {
            int textHeight = (int) getTextHeight(mText, mTextSize);
            canvas.drawText(mText, 0, Math.abs(mHeight - textHeight), mPaint);
        }
        if (null != mLogo) {
            canvas.drawBitmap(mLogo, mWidth - mLogo.getWidth(), mHeight - mLogo.getHeight(), mPaint);
        }
    }

    public void showNone() {
        mText = "";
        mLogo = null;
        mFrame = null;
        postInvalidate();
    }

    public void showText(String text, boolean isReset) {
        if (isReset) {
            showNone();
        }
        mText = text;
        postInvalidate();
    }

    public void showLogo(Bitmap bitmap, boolean isReset) {
        if (isReset) {
            showNone();
        }
        mLogo = bitmap;
        postInvalidate();
    }

    public void showFrame(Bitmap bitmap, boolean isReset) {
        if (isReset) {
            showNone();
        }
        mFrame = bitmap;
        postInvalidate();
    }


    /**
     * 测量文字的宽度
     */
    public static float measureTextWidth(String txt, int size) {
        Paint paint = new Paint();
        paint.setTextSize(size);
        return paint.measureText(txt);
    }

    public static Rect measureTextRectByBounds(String str) {
        Paint paint = new Paint();
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        int w = rect.width();
        int h = rect.height();
        return rect;
    }

    public static int measureTextWidthByBounds(String str) {
        return measureTextRectByBounds(str).width();
    }

    public static int measureTextHeightByBounds(String str) {
        return measureTextRectByBounds(str).height();
    }

    // 获取指定文本的高度
    public static float getTextHeight(String text, float textSize) {
        Paint paint = new Paint(); // 创建一个画笔对象
        paint.setTextSize(textSize); // 设置画笔的文本大小
        Paint.FontMetrics fm = paint.getFontMetrics(); // 获取画笔默认字体的度量衡
        return fm.descent - fm.ascent; // 返回文本自身的高度
    }
//————————————————
//    版权声明：本文为CSDN博主「lichong951」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/lichong951/article/details/125978266
}

