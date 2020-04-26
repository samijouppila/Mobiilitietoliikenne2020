package com.example.gpsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;
    TextView gpsField;
    TextView locationField;
    int locationRequestCode;
    Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        gpsField = findViewById(R.id.gpsField);
        locationField = findViewById(R.id.locationField);
        geocoder = new Geocoder(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, locationRequestCode);
        }
        updateLocation();
    }

    private void updateLocation() {
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location!= null) {
                    gpsField.setText(location.getLatitude() + ", " + location.getLongitude());
                    try {
                        List<Address> list = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        Address address = list.get(0);
                        locationField.setText(address.getLocality() + "\n" + address.getCountryName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }


}
