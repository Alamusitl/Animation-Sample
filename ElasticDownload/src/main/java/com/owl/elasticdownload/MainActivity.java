package com.owl.elasticdownload;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

import com.owl.elasticdownload.view.ElasticDownloadView;
import com.owl.elasticdownload.view.ProgressDownloadView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private ElasticDownloadView mElasticDownloadView;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSeekBar = (SeekBar) findViewById(R.id.id_main_progress);
        mSeekBar.setOnSeekBarChangeListener(this);
        mElasticDownloadView = (ElasticDownloadView) findViewById(R.id.id_main_download_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_run) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    mElasticDownloadView.startIntro();
                }
            });
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mElasticDownloadView.setProgress(20);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        mSeekBar.setProgress(20, true);
                    } else {
                        mSeekBar.setProgress(20);
                    }
                }
            }, 2 * ProgressDownloadView.ANIMATION_DURATION_BASE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onResetClick(View view) {
        mSeekBar.setProgress(0);
        mElasticDownloadView.reset();
    }

    public void onRunSuccessClick(View view) {
        mSeekBar.setProgress(100);
        mElasticDownloadView.startIntro();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mElasticDownloadView.success();
            }
        }, 1000);
    }

    public void onRunFailClick(View view) {
        mElasticDownloadView.startIntro();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mElasticDownloadView.fail();
            }
        }, 1000);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mElasticDownloadView.setProgress(progress);
        if (progress == 100) {
            mElasticDownloadView.success();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // ignore
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // ignore
    }
}
