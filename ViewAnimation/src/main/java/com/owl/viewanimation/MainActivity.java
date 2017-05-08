package com.owl.viewanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onTranslateClick(View view) {
        TranslateAnimation translateAnimation = (TranslateAnimation) AnimationUtils.loadAnimation(this, R.anim.translate);
        view.startAnimation(translateAnimation);
    }

    public void onAlphaClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha));
    }

    public void onScaleClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale));
    }

    public void onRotateClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate));
    }

    public void onSetClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.set));
    }
}
