package com.example.bt2_tuan5;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button callButton= findViewById(R.id.btnCall);
        final EditText phoneNumber= findViewById(R.id.phoneNum);
        final AlertDialog ad=new AlertDialog.Builder(this).create();
        callButton.setOnClickListener(v -> {
            String message="Đang gọi "+phoneNumber.getText();
            ad.setMessage(message);
            ad.show();
        });
    }

}