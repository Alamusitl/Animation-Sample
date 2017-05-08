package com.owl.turn;

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

import com.owl.page.turn.R;
import com.owl.turn.util.BookPageFactory;
import com.owl.turn.widget.PageTurningWidgetCompleteTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PageTurningCompleteTest extends Activity {

    Bitmap mCurPageBitmap, mNextPageBitmap;
    Canvas mCurPageCanvas, mNextPageCanvas;
    BookPageFactory pagefactory;
    private PageTurningWidgetCompleteTest mPageWidget;

    @SuppressLint("SdCardPath")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mPageWidget = new PageTurningWidgetCompleteTest(this);
        setContentView(mPageWidget);

		/* 创建书页 */
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        mCurPageBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mNextPageBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        mCurPageCanvas = new Canvas(mCurPageBitmap);
        mNextPageCanvas = new Canvas(mNextPageBitmap);
        /* 获得书的内容 */
        /* 设置书页的背景 */
        pagefactory = new BookPageFactory(width, height);
        pagefactory.setBgBitmap(BitmapFactory.decodeResource(
                this.getResources(), R.drawable.bg));
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
		/* 设置部件的当前页和下一页，初始化时，当前页和下一页相同 */
		/* 设置部件的触摸屏事件 */
        mPageWidget.setBitmaps(mCurPageBitmap, mCurPageBitmap);
        mPageWidget.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                boolean ret = false;
                if (v == mPageWidget) {
					/* 当向下触摸时 */
                    if (e.getAction() == MotionEvent.ACTION_DOWN) {
                        mPageWidget.abortAnimation();
                        mPageWidget.calcCornerXY(e.getX(), e.getY());
                        pagefactory.Draw(mCurPageCanvas);
						/* 当向下触摸时，判断是向右，还是向左 */
						/* 若向右，则向前翻页，否则，向后翻页 */
                        if (mPageWidget.DragToRight()) { // 向后翻
                            try {
                                pagefactory.prePage();
                            } catch (IOException e1) {
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
						/* 设置部件的当前页和下一页 */
                        mPageWidget.setBitmaps(mCurPageBitmap, mNextPageBitmap);
                    }

                    ret = mPageWidget.onTouchEvent(e);
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