package com.example.asynchttpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AsyncHTTPFetcher.ReporterInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.fetchButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fetchButton) {
            EditText addressEditText = findViewById(R.id.addressField);
            String address = addressEditText.getText().toString();
            AsyncHTTPFetcher task = new AsyncHTTPFetcher();
            task.setCallbackInterface(this);
            task.execute(address);
        }
    }

    @Override
    public void networkFetchDone(String data) {
        TextView httpField = findViewById(R.id.httpField);
        httpField.setText(data);
    }
}
