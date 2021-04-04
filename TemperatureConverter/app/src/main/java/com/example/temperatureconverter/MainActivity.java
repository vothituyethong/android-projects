package com.example.temperatureconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    RadioButton radio0, radio1;
    EditText edit1;
    TextView txt2;
    Button btn1;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radio0 = findViewById(R.id.radio0);
        radio1 = findViewById(R.id.radio1);
        edit1 = findViewById(R.id.edit1);
        txt2 = findViewById(R.id.txt2);
        btn1 = findViewById(R.id.btn1);
        list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<>(this,
                R.layout.activity_listview, list);

        ListView listView = (ListView) findViewById(R.id.lsv);
        listView.setAdapter(adapter);
        btn1.setOnClickListener(view -> {
            String chuoi1 = edit1.getText().toString();
            double so1 = Double.parseDouble(chuoi1);
            String message="";
            if (radio0.isChecked()) {
                double celsius = (double) 5 / 9 * (so1 - 32);
                double f = Math.round((celsius) *100.0)/100.0;
                txt2.setText(String.valueOf(f));
                message="F to C: ";
            } else if (radio1.isChecked()) {
                double fahrenheit = (double) 9 / 5 * so1 + 32;
                double f = Math.round((fahrenheit) *100.0)/100.0;
                txt2.setText(String.valueOf(f));
                message="C to F: ";
            }
            message=message+chuoi1+" --> "+txt2.getText();
            list.add(0,message);
            adapter.notifyDataSetChanged();
        });
    }

}