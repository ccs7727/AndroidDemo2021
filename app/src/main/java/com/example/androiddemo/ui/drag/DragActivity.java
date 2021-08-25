package com.example.androiddemo.ui.drag;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;
import com.example.androiddemo.ui.drag.widget.EasyTipDragView;
import com.example.androiddemo.ui.drag.widget.bean.Tip;
import com.example.androiddemo.ui.drag.widget.widget.TipItemView;

import java.util.ArrayList;

public class DragActivity extends AppCompatActivity /*implements AbsTipAdapter.DragDropListener, TipItemView.OnSelectedListener, TipItemView.OnDeleteClickListener */ {
    private EasyTipDragView easyTipDragView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
        easyTipDragView = (EasyTipDragView) findViewById(R.id.easy_tip_drag_view);
        //设置已包含的标签数据
        easyTipDragView.setAddData(TipDataModel.getAddTips());
        //设置可以添加的标签数据
        easyTipDragView.setDragData(TipDataModel.getDragTips());
        //在easyTipDragView处于非编辑模式下点击item的回调（编辑模式下点击item作用为删除item）
        easyTipDragView.setSelectedListener(new TipItemView.OnSelectedListener() {
            @Override
            public void onTileSelected(Tip entity, int position, View view) {
//                toast(((SimpleTitleTip) entity).getTip());
            }
        });
        //设置每次数据改变后的回调（例如每次拖拽排序了标签或者增删了标签都会回调）
        easyTipDragView.setDataResultCallback(new EasyTipDragView.OnDataChangeResultCallback() {
            @Override
            public void onDataChangeResult(ArrayList<Tip> tips) {
                Log.e("数据改变", tips.toString());
            }
        });
        //设置点击“确定”按钮后最终数据的回调
        easyTipDragView.setOnCompleteCallback(new EasyTipDragView.OnCompleteCallback() {
            @Override
            public void onComplete(ArrayList<Tip> tips) {
                Log.e("最终数据：", tips.toString());
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            //点击返回键
            case KeyEvent.KEYCODE_BACK:
                //判断easyTipDragView是否已经显示出来
                if (easyTipDragView.isOpen()) {
                    if (!easyTipDragView.onKeyBackDown()) {
                        //  btn.setVisibility(View.VISIBLE);
                    }
                    return true;
                }
                //....自己的业务逻辑
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
