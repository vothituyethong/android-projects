package com.example.bt3_tuan5;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button callButton= findViewById(R.id.btnGetCurentTime);
        final AlertDialog ad=new AlertDialog.Builder(this).create();
        callButton.setOnClickListener(v -> {
            Date t=new Date();
            String message= String.format("Thời gian hiện hành: %s", t);
            ad.setMessage(message);
            ad.show();
        });
    }
}