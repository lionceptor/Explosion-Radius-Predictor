package com.example.lyonlee.projected;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void addressRelay(View view)
    {
        Intent firstIntent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(firstIntent);
    }
}
