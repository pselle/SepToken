package com.example.septoken;

import com.cloudmine.api.CMApiCredentials;
import com.cloudmine.api.DeviceIdentifier;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class MainActivity extends FragmentActivity {
	
	private static final String APP_ID = "b2dff1d7ef384e0f82a749fb6c723f81"; // Find this in your developer console
	private static final String API_KEY = "Hackathon";// Find this in your developer 

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //CloudMine
        DeviceIdentifier.initialize(getApplicationContext()); // This initializes the unique ID that will be sent with each request to identify this user
        CMApiCredentials.initialize(APP_ID, API_KEY); // This will initialize your credentials
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
