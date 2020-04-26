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

public class FootballAreaListAdapter extends ArrayAdapter<FootballArea> {
    private Context mContext;
    private ArrayList<FootballArea> areas;

    public FootballAreaListAdapter(@NonNull Context context, int resource, ArrayList<FootballArea> areas) {
        super(context, resource, areas);
        this.mContext = context;
        this.areas = areas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        FootballArea currentArea = areas.get(position);

        TextView nameTextView = (TextView) listItem.findViewById(R.id.listItemName);
        nameTextView.setText(currentArea.getName());

        return listItem;
    }
}
