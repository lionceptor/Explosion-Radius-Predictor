package com.example.lyonlee.projected;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


private FusedLocationProviderClient FLPClient;

public class MainActivity extends AppCompatActivity {

    String passedAddress;

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
        firstIntent.putExtra(passedAddress, realAddress);
        startActivity(firstIntent);
    }

    //get address from EditText and add it to passedAddress string
    public void getAddress(Intent addressIntent)
    {
        EditText address = (EditText)findViewById(R.id.editText2);
        String realAddress = address.getText().toString();
        addressIntent.putExtra(passedAddress, realAddress);
    }
}
