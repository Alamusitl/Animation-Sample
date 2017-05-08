package com.owl.drawableanimation;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Alamusi on 2017/4/28.
 */

public class AnimatedSelectorActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animated_selector);

        final ImageView imageView = (ImageView) findViewById(R.id.id_animation_selector_iv);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    imageView.setActivated(!imageView.isActivated());
                }
            }
        });
    }
}
