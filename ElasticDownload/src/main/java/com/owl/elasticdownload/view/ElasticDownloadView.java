package com.owl.elasticdownload.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.owl.elasticdownload.R;

public class ElasticDownloadView extends FrameLayout implements IntroView.EnterAnimationListener {

    private IntroView mIntroView;
    private ProgressDownloadView mProgressDownloadView;
    private int mBackgroundColor;

    public ElasticDownloadView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ColorOptionsView, 0, 0);
        mBackgroundColor = a.getColor(R.styleable.ColorOptionsView_backgroundColor,
                getResources().getColor(R.color.orange_salmon));
        a.recycle();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_elasticdownload, this, true);
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);

        mIntroView.init();
        mIntroView.setVisibility(VISIBLE);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mIntroView = (IntroView) findViewById(R.id.intro_view);
        mIntroView.setListener(this);
        mProgressDownloadView = (ProgressDownloadView) findViewById(R.id.progress_download_view);

        ViewTreeObserver vto = mProgressDownloadView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mProgressDownloadView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mIntroView.getLayoutParams().width = mProgressDownloadView.getWidth();
                mIntroView.getLayoutParams().height = mProgressDownloadView.getHeight();

                mProgressDownloadView.setBackgroundColor(mBackgroundColor);
            }
        });
    }

    @Override
    public void setBackgroundColor(int passedColor) {
        super.setBackgroundColor(passedColor);
        this.mBackgroundColor = passedColor;
        this.mProgressDownloadView.setBackgroundColor(mBackgroundColor);
    }

    public void startIntro() {
        mIntroView.startAnimation();
    }

    public void reset() {
        setBackgroundColor(mBackgroundColor);
        mProgressDownloadView.setVisibility(INVISIBLE);
        mProgressDownloadView.reset();
        mIntroView.setVisibility(VISIBLE);
        mIntroView.init();
    }

    public void setProgress(float progress) {
        mProgressDownloadView.setPercentage(progress);
        if (progress == 100) {
            success();
        }
    }

    public void success() {
        mProgressDownloadView.drawSuccess();
    }

    public void fail() {
        mProgressDownloadView.drawFail();
    }

    @Override
    public void onEnterAnimationFinished() {
        mIntroView.setVisibility(INVISIBLE);
        mProgressDownloadView.setVisibility(VISIBLE);
        mProgressDownloadView.setProgress(mProgressDownloadView.getProgress());

        // Do further actions if necessary
    }

}
