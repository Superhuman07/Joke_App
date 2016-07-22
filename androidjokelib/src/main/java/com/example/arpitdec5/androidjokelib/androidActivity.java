package com.example.arpitdec5.androidjokelib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class androidActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);

        textView = (TextView) findViewById(R.id.text_joke);
        Intent intent = getIntent();
        String joke = intent.getStringExtra("joke");
        textView.setText(joke);

    }
}
