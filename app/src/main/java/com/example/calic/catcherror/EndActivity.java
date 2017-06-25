package com.example.calic.catcherror;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {
    TextView text,Last;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end2);
        Intent intent = getIntent();
        //Score total=(Score)intent.getSerializableExtra("score");
        String data= intent.getStringExtra("score");
        String last=intent.getStringExtra("lastlife");
        text=(TextView)findViewById(R.id.textView);
        Last=(TextView)findViewById(R.id.textView3);
        text.setText(data+"% 디버깅 성공!");
        Last.setText(last+"Life");
    }


}
