package com.example.androiddemo.ui.drag.widget.adapter;

import android.content.ClipData;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;


import com.example.androiddemo.R;
import com.example.androiddemo.ui.drag.widget.bean.Tip;
import com.example.androiddemo.ui.drag.widget.widget.DragDropGirdView;
import com.example.androiddemo.ui.drag.widget.widget.MyDragShadowBuilder;
import com.example.androiddemo.ui.drag.widget.widget.TipItemView;

import java.util.ArrayList;

/**
 *
 */
public class DragTipAdapter extends AbsTipAdapter implements View.OnLongClickListener, TipItemView.OnDeleteClickListener {
    private boolean isEditing = false;
    private static final ClipData EMPTY_CLIP_DATA = ClipData.newPlainText("", "");
    private TipItemView.OnSelectedListener mListener;
    private TipItemView.OnDeleteClickListener deleteClickListener;
    private OnFirstDragStartCallback callback;

    public DragTipAdapter(Context context, DragDropListener dragDropListener, TipItemView.OnDeleteClickListener deleteClickListener) {
        super(context, dragDropListener);
        this.deleteClickListener = deleteClickListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TipItemView view = null;
        if (convertView != null && convertView instanceof TipItemView) {
            view = (TipItemView) convertView;
        } else {
            view = (TipItemView) View.inflate(mContext, R.layout.view_tag_item, null);
        }
        if (isEditing) {
            view.showDeleteImg();
        } else {
            view.hideDeleteImg();
        }
        //设置点击监听
        view.setItemListener(position, mListener);
        view.setOnLongClickListener(this);
        //设置删除监听
        view.setDeleteClickListener(position, deleteClickListener);
        //绑定数据
        view.renderData(getItem(position));
        return view;
    }

    @Override
    protected Tip getDragEntity(View view) {
        return ((TipItemView) view).getDragEntity();
    }

    public void setItemSelectedListener(TipItemView.OnSelectedListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public boolean onLongClick(View v) {
        //开启编辑模式
//        startEdittingStatus(v);
        return true;
    }

    //删除按钮点击时
    @Override
    public void onDeleteClick(Tip entity, int position, View view) {
        tips.remove(position);
        refreshData();
    }

    public void refreshData() {
        notifyDataSetChanged();
        mDragDropListener.onDataSetChangedForResult(tips);
    }

    public ArrayList<Tip> getData() {
        return tips;
    }

    public void setFirtDragStartCallback(OnFirstDragStartCallback callback) {
        this.callback = callback;
    }

    public interface OnFirstDragStartCallback {
        void firstDragStartCallback();
    }

    public boolean isEditing() {
        return isEditing;
    }

    //取消编辑模式
    public void cancelEditingStatus() {
        isEditing = false;
        notifyDataSetChanged();
    }

    //开启编辑模式
    public void startEdittingStatus(View v) {
        if (!isEditing) {
            isEditing = true;
//            if (callback != null) {
//                callback.firstDragStartCallback();
//            }
            notifyDataSetChanged();
        }
        v.startDrag(EMPTY_CLIP_DATA, new MyDragShadowBuilder(),
                DragDropGirdView.DRAG_FAVORITE_TILE, 0);
    }
}
