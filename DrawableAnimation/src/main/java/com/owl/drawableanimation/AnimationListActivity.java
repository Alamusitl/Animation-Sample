package com.owl.drawableanimation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * Created by Alamusi on 2017/4/28.
 */

public class AnimationListActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_list);

        final ImageView imageView = (ImageView) findViewById(R.id.id_animation_list_iv);
        imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ((AnimationDrawable) imageView.getDrawable()).start();
            }
        });
    }
}
