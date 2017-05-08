package com.owl.propertyanimation;

import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Alamusi on 2017/5/2.
 */

public class ViewWrapper {

    private View mTarget;// 目标对象
    private int mMaxWidth;// 最长宽度

    public ViewWrapper(View target, int maxWidth) {
        mTarget = target;
        mMaxWidth = maxWidth;
    }

    public int getWidth() {
        return mTarget.getLayoutParams().width;
    }

    public void setWidth(int width) {
        mTarget.getLayoutParams().width = mMaxWidth * width / 100;
        mTarget.requestLayout();
    }

    public void setMarginTop(int margin) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mTarget.getLayoutParams();
        System.out.println(layoutParams.topMargin);
        layoutParams.setMargins(0, margin, 0, 0);
        mTarget.setLayoutParams(layoutParams);
    }
}
