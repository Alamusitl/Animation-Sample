package com.owl.vectorsample;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ActivityModel> mModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        ListView listView = (ListView) findViewById(R.id.id_main_listView);

        ArrayAdapter<ActivityModel> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mModelList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ActivityModel model = mModelList.get(position);
                startActivity(new Intent(MainActivity.this, model.getActivityClass()));
            }
        });
    }

    private void initData() {
        mModelList = new ArrayList<>();
        mModelList.add(new ActivityModel("简单Example Pre L", PreLActivity.class));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mModelList.add(new ActivityModel("简单Example L", LActivity.class));
            mModelList.add(new ActivityModel("笑脸", SmileActivity.class));
            mModelList.add(new ActivityModel("Love It", FillInHeartActivity.class));
        }
        mModelList.add(new ActivityModel("Clock", ClockActivity.class));
        mModelList.add(new ActivityModel("Simpson", VectorActivity.class));
        mModelList.add(new ActivityModel("Gift Heart", GiftActivity.class));
        mModelList.add(new ActivityModel("Search Icon", SearchIconActivity.class));
        mModelList.add(new ActivityModel("Search Bar", SearchBarActivity.class));
        mModelList.add(new ActivityModel("Star", StarActivity.class));
        mModelList.add(new ActivityModel("Arrow", ArrowActivity.class));
        mModelList.add(new ActivityModel("Color 渐变", ColorGradientActivity.class));
    }

    public class ActivityModel {
        private String mTitle;
        private Class mActivityClass;

        public ActivityModel(String title, Class aClass) {
            mTitle = title;
            mActivityClass = aClass;
        }

        public String getTitle() {
            return mTitle;
        }

        public Class getActivityClass() {
            return mActivityClass;
        }

        @Override
        public String toString() {
            return getTitle();
        }
    }
}
