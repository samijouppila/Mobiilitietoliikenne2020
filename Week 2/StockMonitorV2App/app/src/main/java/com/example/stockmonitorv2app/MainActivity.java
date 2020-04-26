package com.example.stockmonitorv2app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, StockPriceHttpFetcher.ReporterInterface{

    private StockPriceHttpFetcher task;
    private ArrayList<String> identifiers;
    private HashMap<String, String> stockNameMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        identifiers = new ArrayList<>();
        stockNameMap = new HashMap<>();
        identifiers.add("AAPL");
        stockNameMap.put("AAPL", "Apple");
        identifiers.add("GOOGL");
        stockNameMap.put("GOOGL", "Alphabet (Google)");
        identifiers.add("FB");
        stockNameMap.put("FB","Facebook");
        identifiers.add("NOK");
        stockNameMap.put("NOK","Nokia");

        task = new StockPriceHttpFetcher();
        task.setCallbackInterface(this);
        task.execute(identifiers);

        Button addButton = findViewById(R.id.buttonAddStock);
        addButton.setOnClickListener(this);
    }

    @Override
    public void networkFetchDone(ArrayList data) {
        ArrayList<StockData> stockData = (ArrayList<StockData>) data;
        ListView stockPriceListView = findViewById(R.id.listStocks);
        StockPriceListAdapter stockAdapter = new StockPriceListAdapter(this, stockData, stockNameMap);
        stockPriceListView.setAdapter(stockAdapter);
    }

    @Override
    public void onClick(View v) {
        EditText identifierField = findViewById(R.id.stockIdField);
        String identifier = identifierField.getText().toString();
        identifiers.add(identifier);
        EditText nameField = findViewById(R.id.stockNameField);
        String name = nameField.getText().toString();
        stockNameMap.put(identifier, name);

        task = new StockPriceHttpFetcher();
        task.setCallbackInterface(this);
        task.execute(identifiers);
    }
}
