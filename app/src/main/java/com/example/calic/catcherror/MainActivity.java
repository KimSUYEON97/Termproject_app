package com.example.calic.catcherror;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClicked(View v){
        Intent intent = new Intent(getApplicationContext(),PlayActivity.class);
        startActivity(intent);
    }//게임화면으로 들어감

}
