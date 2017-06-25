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
        int percent=Integer.parseInt(data);
        int lastlife=Integer.parseInt(last);
        text=(TextView)findViewById(R.id.textView);
        Last=(TextView)findViewById(R.id.textView3);
        Last.setText("Last Life : " +lastlife);
        if(percent==0) {
            text.setText(data+"%DeepSleep");
        }
        if(percent>50&&percent<100){
            text.setText(data+"% Nice");
        }
        if(percent<50&&percent>0){
            text.setText(data+"% TooBAD");
        }
        if(percent==100){
            text.setText(data+"% PerFect!");
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }//뒤로가기 나가기
}
