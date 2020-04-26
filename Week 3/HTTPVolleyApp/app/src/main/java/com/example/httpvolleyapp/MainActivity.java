package com.example.httpvolleyapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

            RequestQueue queue = Volley.newRequestQueue(this);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, address,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            TextView httpField = findViewById(R.id.httpField);
                            httpField.setText(response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            TextView httpField = findViewById(R.id.httpField);
                            httpField.setText("That didn't work!");
                        }
                    });

            queue.add(stringRequest);
        }
    }
}
