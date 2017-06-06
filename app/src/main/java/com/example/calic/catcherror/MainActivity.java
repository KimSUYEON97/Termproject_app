package com.example.calic.catcherror;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    public void onButton1Clicked(View v){
        Intent intent = new Intent(getApplicationContext(),PlayActivity.class);
        startActivity(intent);
    }//게임화면으로 들어감

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back button pressed.", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }//뒤로가기 나가기
}
