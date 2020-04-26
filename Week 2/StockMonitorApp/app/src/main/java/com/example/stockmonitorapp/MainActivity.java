package com.example.stockmonitorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements StockPriceHttpFetcher.ReporterInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StockPriceHttpFetcher task = new StockPriceHttpFetcher();
        task.setCallbackInterface(this);
        task.execute();
    }

    @Override
    public void networkFetchDone(ArrayList data) {
        ArrayList<StockData> stockData = (ArrayList<StockData>) data;
        ListView stockPriceListView = findViewById(R.id.listStocks);
        HashMap<String, String> stockNameMap = new HashMap<>();
        stockNameMap.put("AAPL", "Apple");
        stockNameMap.put("GOOGL", "Alphabet (Google)");
        stockNameMap.put("FB", "Facebook");
        stockNameMap.put("NOK", "Nokia");
        StockPriceListAdapter stockAdapter = new StockPriceListAdapter(this, stockData, stockNameMap);
        stockPriceListView.setAdapter(stockAdapter);
    }
}
