package com.alms.animator;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    private String[] mAllAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAllAnimator = getResources().getStringArray(R.array.animator_list);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, R.layout.list_item_animator_name,
                mAllAnimator);

        setListAdapter(mAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        try {
            Class clz = Class.forName(getPackageName() + "." + mAllAnimator[position]);
            Intent intent = new Intent(this, clz);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
