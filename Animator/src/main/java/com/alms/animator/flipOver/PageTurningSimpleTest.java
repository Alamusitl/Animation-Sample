package com.alms.animator.flipOver;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.alms.animator.flipOver.widget.PageTurningWidgetSimpleTest;

public class PageTurningSimpleTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 绘制贝塞尔曲线
        setContentView(new PageTurningWidgetSimpleTest(this));
    }
}