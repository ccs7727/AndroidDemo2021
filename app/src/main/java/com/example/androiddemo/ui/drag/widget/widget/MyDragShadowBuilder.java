package com.example.androiddemo.ui.drag.widget.widget;

import android.graphics.Point;
import android.view.View;

public class MyDragShadowBuilder extends View.DragShadowBuilder {

    @Override
    public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
        outShadowSize.set(1,1);
        outShadowTouchPoint.set(0,0);
    }
}