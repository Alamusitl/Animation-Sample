package com.owl.turn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.owl.page.turn.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PageTurningActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipover);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.id_page_turn_btn_simple)
    void openSimpleTest() {
        Intent intent = new Intent(this, PageTurningSimpleTest.class);
        startActivity(intent);
    }

    @OnClick(R.id.id_page_turn_btn_b_test)
    void openPageTurningBTest() {
        Intent intent = new Intent(this, PageTurningBTest.class);
        startActivity(intent);
    }

    @OnClick(R.id.id_page_turn_btn_complete_test)
    void openPageTurningCompleteTest() {
        Intent intent = new Intent(this, PageTurningCompleteTest.class);
        startActivity(intent);
    }
}