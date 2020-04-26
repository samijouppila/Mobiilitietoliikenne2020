package com.example.footballv2app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FootballLeagueListAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private ArrayList<String> leagues;

    public FootballLeagueListAdapter(@NonNull Context context, int resource, ArrayList<String> leagues) {
        super(context, resource, leagues);
        this.mContext = context;
        this.leagues = leagues;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        String currentLeague = leagues.get(position);

        TextView nameTextView = (TextView) listItem.findViewById(R.id.listItemName);
        nameTextView.setText(currentLeague);

        return listItem;
    }
}
