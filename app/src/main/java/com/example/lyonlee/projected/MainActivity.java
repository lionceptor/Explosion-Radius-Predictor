package com.example.lyonlee.projected;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class MainActivity extends AppCompatActivity {

    String passedAddress;
    LatLng coordinates;
    List<Address> addressCollection;
    Bundle arguments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //relay passedAddress to MapsActivity
    public void addressRelay(View view) {
        //get address that was typed in and send it to MapsActivity
        Intent firstIntent = new Intent(MainActivity.this, MapsActivity.class);
        //parcel coordinates into bundle
        arguments.putParcelable("long", convertToCoordinates(getAddress()));
        firstIntent.putExtras(arguments);
        startActivity(firstIntent);
    }

    //get address from EditText and add it to passedAddress string
    public String getAddress() {
        EditText address = (EditText) findViewById(R.id.editText2);
        String realAddress = address.getText().toString();
        // addressIntent.putExtra(passedAddress, realAddress);
        return realAddress;
    }

    //convert passedAddress to latitude and longitude coordinates
    public LatLng convertToCoordinates(String address) {
        Geocoder converter = new Geocoder(MainActivity.this);
        try {
            //check if addresssCollection is blank and return null if it is
            addressCollection = converter.getFromLocationName(address, 5);
            if (addressCollection != null && addressCollection.size() > 0) {
                Address coord = addressCollection.get(0);
                coord.getLatitude();
                coord.getLongitude();
                coordinates = new LatLng(coord.getLatitude(), coord.getLongitude());
            }
        }
    //could throw exception
    catch(
    IOException exception)
    {
        exception.printStackTrace();
    }
    return coordinates;
    }
}
