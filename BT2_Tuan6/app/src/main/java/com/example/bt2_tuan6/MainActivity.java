package com.example.bt2_tuan6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.sip.SipAudioCall;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button myButton;
    EditText myText;
    CheckBox checkColor;
    CheckBox checkBold;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkColor=(CheckBox)findViewById(R.id.checkbox_color);
        checkBold=(CheckBox)findViewById(R.id.checkbox_bold);
        myButton=(Button)findViewById(R.id.button_Clickme);
        myText=(EditText)findViewById(R.id.edit_text);
        myText.setSingleLine();
        myText.setInputType(InputType.TYPE_NULL);
        myButton.setOnClickListener(v -> {
            if(checkColor.isChecked())
                myText.setTextColor(Color.RED);
            else
                myText.setTextColor(Color.BLACK);
            if(checkBold.isChecked())
                myText.setTypeface(Typeface.DEFAULT_BOLD);
            else
                myText.setTypeface(Typeface.DEFAULT);
            myText.setText("You've clicked "+count+"time");
        });
    }
}