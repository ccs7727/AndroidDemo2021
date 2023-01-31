package com.example.androiddemo.ui.slidelock2.wdiget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

import androidx.customview.widget.ViewDragHelper;

import org.jetbrains.annotations.NotNull;

/**
 * create time: 2017/1/10
 * author: liaoym
 * description:
 */

public class SlideLockView extends ViewGroup {

    private ViewDragHelper viewDragHelper;

    /**
     * 解锁触发阀值百分比
     */
    private final float unlockTriggerValue = 0.5f;

    /**
     * 动画时长
     */
    private final int animationTimeDuration = 300;

    /**
     * 回弹动画实现
     */
    private ObjectAnimator oa;

    /**
     * 锁视图
     */
    private View mLockView;

    private CallBack mCallBack;//回调

    public SlideLockView(Context context) {
        this(context, null);
    }

    public SlideLockView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideLockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    public void init() {
        viewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(@NotNull View child, int pointerId) {
                int minX = 0;
                int maxX = getWidth() - mLockView.getWidth();
                return isEnabled() && (child.getLeft() > minX || child.getRight() < maxX) && child == mLockView;
            }

            @Override
            public void onViewReleased(@NotNull View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                int movedDistance = releasedChild.getRight() - releasedChild.getWidth();
                if (movedDistance >= getWidth() * unlockTriggerValue) {
                    //到终点
                    animToXToPosition(releasedChild, getWidth() - mLockView.getWidth(), animationTimeDuration);
                    //回调
                    if (mCallBack != null) {
                        mCallBack.onUnlocked();
                    }
                } else {
                    //回到起点
                    animToXToPosition(releasedChild, 0, animationTimeDuration);
                    //回调
                    if (mCallBack != null) {
                        mCallBack.onAlpha(1.0f);
                    }
                }
            }


            @Override
            public int clampViewPositionHorizontal(@NotNull View child, int left, int dx) {
                final int oldLeft = child.getLeft();
                int minX = 0;
                int maxX = getWidth() - mLockView.getWidth();
                if (left > minX && left < maxX) {
                    child.layout(left, (getHeight() - child.getHeight()) / 2, left + child.getWidth(), (getHeight() - child.getHeight()) / 2 + child.getHeight());
                }
                int movedDistance = child.getRight() - child.getWidth();
                resetTextViewAlpha(movedDistance);
                return oldLeft;
            }

            @Override
            public int clampViewPositionVertical(@NotNull View child, int top, int dy) {
                return child.getTop();
            }
        });
    }

    /**
     * 重置提示文本的透明度
     */
    private void resetTextViewAlpha(int distance) {
        if (Math.abs(distance) >= Math.abs(getWidth() * unlockTriggerValue)) {
            //回调
            if (mCallBack != null) {
                mCallBack.onAlpha(0.0f);
            }
        } else {
            //回调
            if (mCallBack != null) {
                mCallBack.onAlpha(1.0f - Math.abs(distance) * 1.0f / Math.abs(getWidth() * unlockTriggerValue));
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.AT_MOST);
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.AT_MOST);

        this.mLockView = getChildAt(0);

        mLockView.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        int lockHeight = mLockView.getMeasuredHeight();
        int lockWidth = mLockView.getMeasuredWidth();
        int width;

        switch (MeasureSpec.getMode(widthMeasureSpec)) {
            case MeasureSpec.EXACTLY:
                width = MeasureSpec.getSize(widthMeasureSpec);
                break;
            default:
                width = lockWidth;
                break;
        }
        setMeasuredDimension(width, lockHeight);
    }

    /**
     * 使用动画转到指定的位置
     *
     * @param view 需要作用动画的视图
     * @param toX  需要转到的位置
     */
    public void animToXToPosition(final View view, int toX, long animationTime) {
        Property<View, Integer> layoutProperty = new Property<View, Integer>(Integer.class, "layout") {

            @Override
            public void set(View object, Integer value) {
                object.layout(value, (getHeight() - mLockView.getHeight()) / 2,
                        value + object.getWidth(), (getHeight() - mLockView.getHeight()) / 2 + object.getHeight());
            }

            @Override
            public Integer get(View object) {
                return view.getLeft();
            }
        };

        //原来的动画正在执行
        //取消掉，防止多重动画冲突
        if (oa != null && oa.isRunning()) {
            oa.cancel();
        }
        oa = ObjectAnimator.ofInt(view, layoutProperty, view.getLeft(), toX);
        oa.setInterpolator(new AccelerateInterpolator());
        oa.setDuration(animationTime);
        oa.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int viewHeight = b - t;

        int lockViewWidth = mLockView.getMeasuredWidth();
        int lockViewHeight = mLockView.getMeasuredHeight();

        mLockView.layout(0, (viewHeight - lockViewHeight) / 2, lockViewWidth, (viewHeight - lockViewHeight) / 2 + lockViewHeight);
    }

    public void resetView() {
        //回到起点
        animToXToPosition(mLockView, 0, animationTimeDuration);
        //回调
        if (mCallBack != null) {
            mCallBack.onAlpha(1.0f);
        }
    }

    public interface CallBack {
        void onAlpha(float alpha);//透明度

        void onUnlocked();//滑动到了右边,事件回调
    }

    public void setmCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
    }
}
