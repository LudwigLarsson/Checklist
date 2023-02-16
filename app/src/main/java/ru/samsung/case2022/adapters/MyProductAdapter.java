package ru.samsung.case2022.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ru.samsung.case2022.R;

public class MyProductAdapter extends ArrayAdapter<String> {

    public MyProductAdapter(Context context, ArrayList<String> products) {
        super(context, R.layout.adapter_item, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item, null);
        }


        ((TextView) convertView.findViewById(R.id.productName)).setText("tebrtbrtb");


        return convertView;
    }
}