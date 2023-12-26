package com.example.androiddemo.ui.list;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androiddemo.R;

import java.util.ArrayList;

/**
 * Created by chawei on 2018/4/29.
 */
public class HRecyclerView extends RelativeLayout {

    //头部title布局
    private LinearLayout mRightTitleLayout;
    //手指按下时的位置
    private float mStartX = 0;
    //滑动时和按下时的差值
    private int mMoveOffsetX = 0;
    //最大可滑动差值
    private int mFixX = 0;
    //左边标题集合
    private String[] mLeftTextList;
    //左边标题的宽度集合
    private int[] mLeftTextWidthList;
    //右边标题集合
    private String[] mRightTitleList = new String[]{};
    //右边标题的宽度集合
    private int[] mRightTitleWidthList = null;
    //展示数据时使用的RecycleView
    private RecyclerView mRecyclerView;
    //RecycleView的Adapter
    private Object mAdapter;
    //需要滑动的View集合
    private ArrayList<View> mMoveViewList = new ArrayList();
    private Context context;
    //右边可滑动的总宽度
    private int mRightTotalWidth = 0;
    //右边单个view一个字的宽度
    private int mRightItemWidth = 15;
    //左右宽度
    private int mLeftRightItemWidth = 30;
    //左边view的宽度
    private int mLeftViewWidth = 60;
    //左边view的高度
    private int mLeftViewHeight = 40;
    //触发拦截手势的最小值
    private int mTriggerMoveDis = 30;

    public HRecyclerView(Context context) {
        this(context, null);
    }

    public HRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    private void initView() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(createHeadLayout());
        linearLayout.addView(createMoveRecyclerView());
        addView(linearLayout, new LayoutParams(LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }

    /**
     * 创建头部布局
     *
     * @return
     */
    private View createHeadLayout() {
        LinearLayout headLayout = new LinearLayout(getContext());
        headLayout.setGravity(Gravity.CENTER);
        headLayout.setBackgroundColor(getResources().getColor(R.color.c_F4F8F9));
        LinearLayout leftLayout = new LinearLayout(getContext());
        addListHeaderTextView(mLeftTextList[0], mLeftTextWidthList[0], leftLayout);
        addListHeaderLineView(leftLayout);
        leftLayout.setGravity(Gravity.CENTER);
        headLayout.addView(leftLayout, 0, new ViewGroup.LayoutParams(dip2px(context, mLeftViewWidth) + 1, dip2px(context, mLeftViewHeight)));

        mRightTitleLayout = new LinearLayout(getContext());
        for (int i = 0; i < mRightTitleList.length; i++) {
            addListHeaderTextView(mRightTitleList[i], mRightTitleWidthList[i], mRightTitleLayout);
            if (i != mRightTitleList.length - 1) {
                addListHeaderLineView(mRightTitleLayout);
            }
        }
        headLayout.addView(mRightTitleLayout);

        MarginLayoutParams params = new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(1, 1, 1, 0);//left,top,right,bottom
        headLayout.setLayoutParams(params);
        return headLayout;
    }

    /**
     * 创建数据展示布局
     *
     * @return
     */
    private View createMoveRecyclerView() {
        RelativeLayout linearLayout = new RelativeLayout(getContext());
        mRecyclerView = new RecyclerView(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        if (null != mAdapter) {
            if (mAdapter instanceof CommonAdapter) {
                mRecyclerView.setAdapter((CommonAdapter) mAdapter);
                mMoveViewList = ((CommonAdapter) mAdapter).getMoveViewList();
            }
        }

        linearLayout.addView(mRecyclerView, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        return linearLayout;
    }

    /**
     * 设置adapter
     *
     * @param adapter
     */
    public void setAdapter(Object adapter) {
        mAdapter = adapter;
        initView();
    }

    /**
     * 设置头部title单个布局
     *
     * @param headerName
     * @param width
     * @param leftLayout
     * @return
     */
    private TextView addListHeaderTextView(String headerName, int width, LinearLayout leftLayout) {
        TextView textView = new TextView(getContext());
        textView.setText(headerName);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(getResources().getColor(R.color.c_303133));
        leftLayout.addView(textView, width, dip2px(context, mLeftViewHeight));
        return textView;
    }

    private View addListHeaderLineView(LinearLayout leftLayout) {
        View textView = new View(getContext());
        textView.setBackgroundColor(getResources().getColor(R.color.c_e5e5e5));
        leftLayout.addView(textView, 1, dip2px(context, mLeftViewHeight));
        return textView;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = (int) Math.abs(ev.getX() - mStartX);
                if (offsetX > mTriggerMoveDis) {//水平移动大于30触发拦截
                    return true;
                } else {
                    return false;
                }
        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 右边可滑动的总宽度
     *
     * @return
     */
    private int rightTitleTotalWidth() {
        if (0 == mRightTotalWidth) {
            for (int i = 0; i < mRightTitleWidthList.length; i++) {
                mRightTotalWidth = mRightTotalWidth + mRightTitleWidthList[i];
            }
        }
        return mRightTotalWidth;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                int offsetX = (int) Math.abs(event.getX() - mStartX);
                if (offsetX > 30) {
                    mMoveOffsetX = (int) (mStartX - event.getX() + mFixX);
                    if (0 > mMoveOffsetX) {
                        mMoveOffsetX = 0;
                    } else {
                        //当滑动大于最大宽度时，不在滑动（右边到头了）
                        if ((mRightTitleLayout.getWidth() + mMoveOffsetX) > rightTitleTotalWidth()) {
                            mMoveOffsetX = rightTitleTotalWidth() - mRightTitleLayout.getWidth();
                        }
                    }
                    //跟随手指向右滚动
                    mRightTitleLayout.scrollTo(mMoveOffsetX, 0);
                    if (null != mMoveViewList) {
                        for (int i = 0; i < mMoveViewList.size(); i++) {
                            //使每个item随着手指向右滚动
                            mMoveViewList.get(i).scrollTo(mMoveOffsetX, 0);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                mFixX = mMoveOffsetX; //设置最大水平平移的宽度
                break;

        }

        return super.onTouchEvent(event);
    }

    /**
     * 列表头部数据
     *
     * @param headerListData
     */
    public void setHeaderListData(String leftTitle, String[] headerListData) {
        mRightTitleList = headerListData;
        mRightTitleWidthList = new int[headerListData.length];
        for (int i = 0; i < headerListData.length; i++) {
            mRightTitleWidthList[i] = dip2px(context, headerListData[i].length() * mRightItemWidth + mLeftRightItemWidth);
        }
        mLeftTextWidthList = new int[]{dip2px(context, mLeftViewWidth)};
        mLeftTextList = new String[]{leftTitle};
    }

    private static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}

