package com.owl.propertyanimation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onValueAnimatorClick(final View view) {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        final int maxWidth = dm.widthPixels;
        ValueAnimator valueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.value_animator);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentValue = (int) animation.getAnimatedValue();
                view.getLayoutParams().width = maxWidth * currentValue / 100;
                view.requestLayout();
            }
        });
        valueAnimator.start();
    }

    public void onObjectAnimatorClick(View view) {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        final int maxWidth = dm.widthPixels;

        ViewWrapper wrapper = new ViewWrapper(view, maxWidth);
        ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.object_animator);
        objectAnimator.setTarget(wrapper);
        objectAnimator.start();
    }

    public void onAnimatorSetClick(View view) {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        final int maxWidth = dm.widthPixels;
        ViewWrapper wrapper = new ViewWrapper(view, maxWidth);
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.property_set);
        animatorSet.setTarget(wrapper);
        animatorSet.start();
    }
}
