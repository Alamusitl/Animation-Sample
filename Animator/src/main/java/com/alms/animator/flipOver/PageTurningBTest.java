package com.alms.animator.flipOver;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.alms.animator.R;
import com.alms.animator.flipOver.util.BookPageFactory;
import com.alms.animator.flipOver.widget.PageTurningWidgetBTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PageTurningBTest extends Activity {
    Bitmap mCurPageBitmap, mNextPageBitmap;
    Canvas mCurPageCanvas, mNextPageCanvas;
    BookPageFactory pagefactory;
    private PageTurningWidgetBTest mPageWidget;

    @SuppressLint("SdCardPath")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mPageWidget = new PageTurningWidgetBTest(this);
        setContentView(mPageWidget);

		/* 创建书页 */
        mCurPageBitmap = Bitmap.createBitmap(480, 800, Bitmap.Config.ARGB_8888);
        mNextPageBitmap = Bitmap
                .createBitmap(480, 800, Bitmap.Config.ARGB_8888);

        mCurPageCanvas = new Canvas(mCurPageBitmap);
        mNextPageCanvas = new Canvas(mNextPageBitmap);

        pagefactory = new BookPageFactory(480, 800);
        pagefactory.setBgBitmap(BitmapFactory.decodeResource(
                this.getResources(), R.drawable.bg));
        // 获得书的内容
        try {
            String fileName = Environment.getExternalStorageDirectory() + File.separator + "test.txt";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File file = new File(fileName);
                if (!file.exists()) {
                    copyFileToSD(fileName);
                }
            }
            pagefactory.openbook(fileName);
            pagefactory.Draw(mCurPageCanvas);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            Toast.makeText(this, "电子书不存在，请将《test.txt》放在SD卡根目录下。",
                    Toast.LENGTH_SHORT).show();
        }

        mPageWidget.setBitmaps(mCurPageBitmap, mCurPageBitmap);

        mPageWidget.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                boolean ret = false;
                if (v == mPageWidget) {
                    if (e.getAction() == MotionEvent.ACTION_DOWN) {
                        mPageWidget.abortAnimation();
                        mPageWidget.calcCornerXY(e.getX(), e.getY());

                        pagefactory.Draw(mCurPageCanvas);
                        if (mPageWidget.DragToRight()) {
                            try {
                                pagefactory.prePage();
                            } catch (IOException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            if (pagefactory.isfirstPage())
                                return false;
                            pagefactory.Draw(mNextPageCanvas);
                        } else {
                            try {
                                pagefactory.nextPage();
                            } catch (IOException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            if (pagefactory.islastPage())
                                return false;
                            pagefactory.Draw(mNextPageCanvas);
                        }
                        mPageWidget.setBitmaps(mCurPageBitmap, mNextPageBitmap);
                    }

                    ret = mPageWidget.doTouchEvent(e);
                    return ret;
                }
                return false;
            }

        });
    }

    private void copyFileToSD(String strOutFileName) throws IOException {
        InputStream is;
        OutputStream os = new FileOutputStream(strOutFileName);
        is = this.getAssets().open("test.txt");
        byte[] buffer = new byte[1024];
        int length = is.read(buffer);
        while (length > 0) {
            os.write(buffer, 0, length);
            length = is.read(buffer);
        }
        os.flush();
        is.close();
        os.close();
    }
}