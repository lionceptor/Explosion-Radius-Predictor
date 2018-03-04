package com.example.lyonlee.projected;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    LatLng coordinates;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Grapevine High School and move the camera
        coordinates = new LatLng(32.9155352, -97.1197092);
        mMap.addMarker(new MarkerOptions().position(coordinates).title("Grapevine High School"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinates));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
    }

    List<Address> addressList = null;
    String name;

    public void clickSearch(View view)
    {
        //get text input from edittext
        EditText address = (EditText) findViewById(R.id.editText);
        name = address.getText().toString();
        //convert text input to coordinates
        Geocoder converter = new Geocoder(getBaseContext());
        try {
            //check if addresssCollection is blank and return null if it is
            addressList = converter.getFromLocationName(name, 5);
            if (addressList != null && addressList.size() > 0)
            {
                Address xycoord = addressList.get(0);
                coordinates = new LatLng(xycoord.getLatitude(), xycoord.getLongitude());
            }
        }
        //could throw exception
        catch(
                IOException exception)
        {
            exception.printStackTrace();
        }
        //change map as needed
        mMap.clear();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinates));
        mMap.addMarker(new MarkerOptions().position(coordinates).title(name));
    }

    public void drawCircle(int radius, int fc)
    {
        CircleOptions options = new CircleOptions();
        options.center(coordinates);
        options.radius(radius);
        options.strokeColor(000000);
        options.fillColor(fc);
        options.strokeWidth(2);
        mMap.addCircle(options);
    }

    public void drawNuke(View view)
    {
       drawCircle(50000,0x22FF0000);
    }

    public void drawMoab(View view)
    {
        drawCircle(10000, 0x22FFD700);
    }

    public void drawCruise(View view)
    {
        drawCircle(5000, 0x2200FF00);
    }

    public void clearMap(View view)
    {
        mMap.clear();
    }

    public void zoomIn(View view)
    {
        mMap.moveCamera(CameraUpdateFactory.zoomIn());
    }

    public void zoomOut (View view)
    {
        mMap.moveCamera((CameraUpdateFactory.zoomOut()));
    }
}
