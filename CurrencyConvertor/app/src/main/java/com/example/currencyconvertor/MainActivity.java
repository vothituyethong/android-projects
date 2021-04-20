package com.example.currencyconvertor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String cur_option1;
    String cur_option2;
    MaterialSpinner option1;
    MaterialSpinner option2;
    ArrayList<String> curCode;
    ArrayList<Float> value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btnSubmit);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        curCode=new ArrayList<>();
        value=new ArrayList<>();

        String[] list = new String[]{"USD - United States Dollar", "EUR - Euro", "GBP - British Pound", "JPY - Japanese Yen",
                "CHF - Swiss Franc", "CAD - Canadian Dollar", "AUD - Australian Dollar",
                "CNY - Chinese Yuan", "HKD - Hong Kong Dollar", "RUB - Russian Rouble",
                "MXN - Mexican Peso", "ZAR - South African Rand",
                "AUD - Australian Dollar", "ALL - Albanian Lek", "DZD - Algerian Dinar",
                "ARS - Argentine Peso", "BSD - Bahamian Dollar", "BHD - Bahraini Dinar",
                "BDT - Bangladesh Taka", "BBD - Barbados Dollar", "BZD - Belize Dollar",
                "BTN - Bhutan Ngultrum", "BOB - Bolivian Boliviano", "BWP - Botswana Pula",
                "BRL - Brazilian Real", "BND - Brunei Dollar", "BGN - Bulgarian Lev",
                "BIF - Burundi Franc", "KHR - Cambodia Riel", "CVE - Cape Verde Escudo",
                "XOF - CFA Franc (BCEAO)", "XAF - CFA Franc (BEAC)", "CLP - Chilean Peso",
                "COP - Colombian Peso", "KMF - Comoros Franc", "CRC - Costa Rica Colon",
                "HRK - Croatian Kuna", "CUP - Cuban Peso", "CZK - Czech Koruna",
                "DKK - Danish Krone", "DJF - Djibouti Franc", "DOP - Dominican Peso",
                "XCD - East Caribbean Dollar", "EGP - Egyptian Pound", "ETB - Ethiopian Birr",
                "ETB - Ethiopian Birr", "FJD - Fiji Dollar", "IDR - Indonesian Rupiah",
                "INR - Indian Rupee", "GMD - Gambian Dalasi", "GTQ - Guatemala Quetzal0",
                "GNF - Guinea Franc", "GYD - Guyana Dollar", "HTG - Haiti Gourde",
                "HNL - Honduras Lempira", "HUF - Hungarian Forint", "ISK - Iceland Krona",
                "IRR - Iran Rial", "IQD - Iraqi Dinar", "ILS - Israeli Shekel",
                "JMD - Jamaican Dollar", "JOD - Jordanian Dinar", "KZT - Kazakhstan Tenge",
                "KES - Kenyan Shilling", "KRW - Korean Won", "KWD - Kuwaiti Dinar",
                "LAK - Lao Kip", "LBP - Lebanese Pound", "LSL - Lesotho Loti",
                "LRD - Liberian Dollar", "LYD - Libyan Dinar", "MOP - Macau Pataca",
                "MKD - Macedonian Denar", "MWK - Malawi Kwacha", "MYR - Malaysian Ringgit",
                "MVR - Maldives Rufiyaa", "MRO - Mauritania Ougulya", "MUR - Mauritius Rupee",
                "MDL - Moldovan Leu", "MNT - Mongolian Tugrik", "MAD - Moroccan Dirham",
                "MMK - Myanmar Kyat", "NAD - Namibian Dollar", "NPR - Nepalese Rupee",
                "NZD - New Zealand Dollar", "NIO - Nicaragua Cordoba", "NGN - Nigerian Naira",
                "KPW - North Korean Won", "NOK - Norwegian Krone", "OMR - Omani Rial",
                "PKR - Pakistani Rupee", "PGK - Papua New Guinea Kina", "PYG - Paraguayan Guarani",
                "PEN - Peruvian Nuevo Sol", "PHP - Philippine Peso", "PLN - Polish Zloty",
                "QAR - Qatar Rial", "RON - Romanian New Leu", "RWF - Rwanda Franc",
                "WST - Samoa Tala", "SAR - Saudi Arabian Riyal", "SCR - Seychelles Rupee",
                "SLL - Sierra Leone Leone", "SGD - Singapore Dollar", "SBD - Solomon Islands Dollar",
                "SOS - Somali Shilling", "LKR - Sri Lanka Rupee", "SDG - Sudanese Pound",
                "SZL - Swaziland Lilageni", "SEK - Swedish Krona", "SYP - Syrian Pound",
                "THB - Thai Baht", "TRY - Turkish Lira", "TWD - Taiwan Dollar", "TZS - Tanzanian Shilling",
                "TOP - Tongan paʻanga", "TTD - Trinidad Tobago Dollar", "TND - Tunisian Dinar", "AED - UAE Dirham",
                "UGX - Ugandan Shilling", "UAH - Ukraine Hryvnia", "UYU - Uruguayan New Peso",
                "VUV - Vanuatu Vatu", "VND - Vietnam Dong", "YER - Yemen Riyal", "UZS - Uzbekistan Sum",
                "KGS - Kyrgyzstan Som", "GHS - Ghanaian Cedi", "BYN - Belarusian ruble", "AFN - Afghan afghani",
                "AOA - Angolan kwanza", "AMD - Armenian dram", "AZN - Azerbaijani manat", "BAM - Convertible mark",
                "CDF - Congolese franc", "ERN - Eritrean nakfa", "GEL - Georgian lari", "MGA - Malagasy ariary",
                "MZN - Mozambican metical", "RSD - Serbian dinar", "SRD - Surinamese dollar",
                "TJS - Tajikistani somoni", "TMT - Turkmenistan manat", "ZMW - Zambian kwacha"};

        option1.setItems(list);
        option1.setSelectedIndex(0);
        cur_option1=list[option1.getSelectedIndex()].substring(0,3).toLowerCase();
        option1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

                cur_option1=list[option1.getSelectedIndex()].substring(0,3).toLowerCase();
            }
        });
        option2.setItems(list);
        option2.setSelectedIndex(1);
        cur_option2=list[option2.getSelectedIndex()].substring(0,3);
        option2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                cur_option2=list[option2.getSelectedIndex()].substring(0,3);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curCode.clear();
                value.clear();
                new ReadRSS().execute("https://"+cur_option1+".fxexchangerate.com/rss.xml");
            }
        });
    }
    private class ReadRSS extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content=new StringBuilder();
            try {
                URL url=new URL(strings[0]);
                InputStreamReader inputStreamReader=new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line="";
                while((line=bufferedReader.readLine())!=null){
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }
        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            XMLDOMParser parser=new XMLDOMParser();
            Document document=parser.getDocument(s);
            NodeList nodeList=document.getElementsByTagName("item");
            String  currency2="";

            for(int i=0;i<nodeList.getLength();i++){
                Element element= (Element) nodeList.item(i);
                currency2=parser.getValue(element,"title");
                currency2=currency2.substring(currency2.length()-4, currency2.length()-1);
                curCode.add(currency2);
                String str=parser.getValue(element,"description");
                String[] output = str.split(" ");
                for(int j=0;j<output.length;j++){
                    if (output[j].equalsIgnoreCase("="))
                        value.add(Float.parseFloat(output[j+1]));
                }
            }

            for(int i=0;i<curCode.size();i++)
                if(cur_option2.equalsIgnoreCase(curCode.get(i))){
                    TextView tv=findViewById(R.id.result);
                    EditText ed=findViewById(R.id.ed1);
                    Float kq=value.get(i)*Float.parseFloat(ed.getText().toString());
                    tv.setText(""+kq);
                    break;
                }
        }

    }
}