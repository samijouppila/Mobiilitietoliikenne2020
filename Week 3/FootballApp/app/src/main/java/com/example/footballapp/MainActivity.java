package com.example.footballapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();

        String url = "https://api.football-data.org/v2/competitions?areas=2072";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

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

        requestQueue.add(jsonObjectRequest);


    }

    private void handleResponse(JSONObject leagueResponse) {
        Log.d("Testi","Jotain3");
        ArrayList<String> leagues = parseLeagues(leagueResponse);

        ListView leagueListView = findViewById(R.id.listLeagues);
        LeagueListAdapter adapter = new LeagueListAdapter(this, 0, leagues);
        leagueListView.setAdapter(adapter);

        requestQueue.stop();
    }

    private ArrayList<String> parseLeagues(JSONObject data) {

        ArrayList<String> leagueList = new ArrayList<>();

        try {
            JSONArray competitionsArray = data.getJSONArray("competitions");

            for (int i = 0; i < competitionsArray.length(); i++) {
                JSONObject competitionObject = competitionsArray.getJSONObject(i);
                String name = competitionObject.getString("name");
                leagueList.add(name);
                Log.d("Testi","League " + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return leagueList;
    }
}
