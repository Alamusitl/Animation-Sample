package com.owl.vectorsample;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Alamusi on 2017/5/5.
 */

public class FillInHeartActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_fill_in_heart;
    }

    @Override
    public void onClickView(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View view1 = viewGroup.getChildAt(i);
                view1.setActivated(!view1.isActivated());
            }
        } else {
            view.setActivated(!view.isActivated());
        }
    }
}
