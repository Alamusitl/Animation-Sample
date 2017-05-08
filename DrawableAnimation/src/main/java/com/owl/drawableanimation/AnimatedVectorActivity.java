package com.owl.drawableanimation;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

/**
 * Created by Alamusi on 2017/4/28.
 */

public class AnimatedVectorActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animated_vector);

        ImageView imageView = (ImageView) findViewById(R.id.id_animation_vector_iv_2);
        Drawable drawable = imageView.getDrawable();
        if (drawable != null && drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }
}
