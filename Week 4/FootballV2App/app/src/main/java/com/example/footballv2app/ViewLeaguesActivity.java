package com.example.footballv2app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewLeaguesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_leagues);

        JSONObject requestHeader = null;
        // Football-data.org limits API requests. Try putting token and authenticating the request if it fails. Template below --Sami
        /*HashMap<String, String> params = new HashMap<>();
        params.put("X-Auth-Token","YOUR_TOKEN_HERE");
        JSONObject requestHeader = new JSONObject(params);*/

        int id = getIntent().getExtras().getInt("id");
        String url = "https://api.football-data.org/v2/competitions?areas=" + id;

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

    private void handleResponse(JSONObject leagueResponse) {
        ArrayList<String> leagues = parseLeagues(leagueResponse);

        ListView leagueListView = findViewById(R.id.listLeagues);
        FootballLeagueListAdapter adapter = new FootballLeagueListAdapter(this, 0, leagues);
        leagueListView.setAdapter(adapter);
    }

    private ArrayList<String> parseLeagues(JSONObject data) {

        ArrayList<String> leagueList = new ArrayList<>();
        Log.d("Area",data.toString());

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
