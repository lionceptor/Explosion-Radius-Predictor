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


private FusedLocationProviderClient FLPClient;

public class MainActivity extends AppCompatActivity {

    String passedAddress;
    LatLng coordinates;
    List<Address> addressCollection;
    Bundle arguments;
    Object context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //relay passedAddress to MapsActivity
    public void addressRelay(View view)
    {
        //get address that was typed in and send it to MapsActivity
        Intent firstIntent = new Intent(MainActivity.this, MapsActivity.class);
        getAddress();
        convertToCoordinates(context, passedAddress);
        //parcel coordinates into bundle
        arguments.putParcelable("longitudeLatitude", coordinates);
        firstIntent.putExtras(arguments);
        startActivity(firstIntent);
    }

    //get address from EditText and add it to passedAddress string
    public void getAddress(Intent addressIntent)
    {
        EditText address = (EditText)findViewById(R.id.editText2);
        String realAddress = address.getText().toString();
        addressIntent.putExtra(passedAddress, realAddress);
    }

    //convert passedAddress to latitude and longitude coordinates
    public void convertToCoordinates (Context context, String address)
    {
        Geocoder converter = new converter(context);
        try {
            //check if string is blank and return null if it is
            if (address.equals("") || address.equals("null")){
                return null;
            }
            addressCollection = converter.getFromLocationName(address, 6);
            Address coord = addressCollection.get(0);
            coordinates = new LatLng(coord.getLatitude(), coord.getLongitude());
        }
        //could throw exception
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }

    public


}
