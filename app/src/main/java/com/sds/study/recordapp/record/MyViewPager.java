package com.sds.study.recordapp.record;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by wbhlkc0 on 2016-11-17.
 */

/*
* ViewPager는 무조건 슬라이딩 되므로, 그 기능을 막아버리자...
* */



public class MyViewPager extends ViewPager{
    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
