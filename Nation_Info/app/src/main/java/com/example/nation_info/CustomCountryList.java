package com.example.nation_info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.java2blog.androidrestjsonexample.Country;
//import com.java2blog.androidrestjsonexample.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomCountryList extends BaseAdapter {


    private Context context;
    ArrayList<Country> countries;

    public CustomCountryList(Context context, ArrayList<Country> countries) {
        this.context = context;
        this.countries=countries;

    }

    private class ViewHolder {
        TextView textViewName;

        public ViewHolder(View view) {
            textViewName = (TextView) view.findViewById(R.id.textViewCountry);
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textViewName.setText(countries.get(position).getCountryName());
        return convertView;
    }

    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        return position;
    }

    public int getCount() {

        if(countries.size()<=0)
            return 1;
        return countries.size();
    }
}
