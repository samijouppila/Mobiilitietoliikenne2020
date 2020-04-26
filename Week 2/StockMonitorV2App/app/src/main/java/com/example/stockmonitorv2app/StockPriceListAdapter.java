package com.example.stockmonitorv2app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class StockPriceListAdapter extends ArrayAdapter<StockData> {

    private Context mContext;
    private ArrayList<StockData> stockDataList;
    private HashMap<String, String> stockNameMap;

    public StockPriceListAdapter(@NonNull Context context, @LayoutRes ArrayList<StockData> list, HashMap<String,String> stockNameMap) {
        super(context, 0, list);
        this.mContext = context;
        this.stockDataList = list;
        this.stockNameMap = stockNameMap;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        StockData currentStock = stockDataList.get(position);

        TextView nameTextView = (TextView) listItem.findViewById(R.id.listItemStockName);
        String identifier = currentStock.getIdentifier();
        String stockName = stockNameMap.get(identifier) + ":";
        nameTextView.setText(stockName);

        TextView priceTextView = (TextView) listItem.findViewById(R.id.listItemStockPrice);
        priceTextView.setText(String.format("%.2f",currentStock.getPrice()) + " USD");

        return listItem;
    }
}
