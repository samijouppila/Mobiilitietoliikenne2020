package com.example.footballv2app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.url = "https://api.football-data.org/v2/areas";

        JSONObject requestHeader = null;
        // Football-data.org limits API requests. Try putting token and authenticating the request if it fails. Template below --Sami
        /*HashMap<String, String> params = new HashMap<>();
        params.put("X-Auth-Token","YOUR_TOKEN_HERE");
        JSONObject requestHeader = new JSONObject(params);*/

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, requestHeader, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        handleResponse(response);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Volley Error", error.getMessage());
                    }
                });

        SuperApplication.getInstance().getRequestQueue().add(jsonObjectRequest);
    }

    private void handleResponse(JSONObject areaResponse) {
        ArrayList<FootballArea> areas = parseAreas(areaResponse);

        ListView areaListView = findViewById(R.id.listAreas);
        FootballAreaListAdapter adapter = new FootballAreaListAdapter(this, 0, areas);
        areaListView.setAdapter(adapter);

        areaListView.setOnItemClickListener( new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                FootballArea clickedArea = (FootballArea) parent.getItemAtPosition(position);
                Intent viewLeaguesIntent = new Intent(getApplicationContext(),ViewLeaguesActivity.class);
                Log.d("Area",clickedArea.getName());
                viewLeaguesIntent.putExtra("id",clickedArea.getId());
                startActivity(viewLeaguesIntent);
            }
        });
    }

    private ArrayList<FootballArea> parseAreas(JSONObject data) {

        ArrayList<FootballArea> areaList = new ArrayList<>();

        try {
            JSONArray competitionsArray = data.getJSONArray("areas");
            for (int i = 0; i < competitionsArray.length(); i++) {
                JSONObject selectedAreaObject = competitionsArray.getJSONObject(i);
                String name = selectedAreaObject.getString("name");
                int id = selectedAreaObject.getInt("id");
                areaList.add(new FootballArea(id, name));
                Log.d("Area",name + " " + Integer.toString(id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return areaList;
    }
}
