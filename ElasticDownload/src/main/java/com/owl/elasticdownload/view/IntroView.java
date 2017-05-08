package com.owl.elasticdownload.view;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.owl.elasticdownload.R;

public class IntroView extends ImageView {

    private static final String LOG_TAG = IntroView.class.getSimpleName();
    private EnterAnimationListener mListener;

    public IntroView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setListener(EnterAnimationListener listener) {
        mListener = listener;
    }

    public void init() {
        // Reset the image view drawable if needed, we use the transparent color to remove it
        setImageResource(android.R.color.transparent);

        AnimatedVectorDrawableCompat animatedVectorDrawableCompat = AnimatedVectorDrawableCompat.create(getContext(), R.drawable.animated_vector_drawable_start);
        setImageDrawable(animatedVectorDrawableCompat);

        Drawable drawable = getDrawable();
        drawable.invalidateSelf();
    }

    public void startAnimation() {
        Drawable drawable = getDrawable();
        Animatable animatable = (Animatable) drawable;

        animatable.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mListener.onEnterAnimationFinished();
            }
        }, getContext().getResources().getInteger(R.integer.enter_animation_duration));
    }

    public interface EnterAnimationListener {
        void onEnterAnimationFinished();
    }

}
