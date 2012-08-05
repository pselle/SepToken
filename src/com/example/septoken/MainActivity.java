package com.example.septoken;

import java.util.List;
import java.util.concurrent.Future;

import com.cloudmine.api.CMApiCredentials;
import com.cloudmine.api.DeviceIdentifier;
import com.cloudmine.api.SimpleCMObject;
import com.cloudmine.api.rest.CMStore;
import com.cloudmine.api.rest.callbacks.SimpleCMObjectResponseCallback;
import com.cloudmine.api.rest.response.SimpleCMObjectResponse;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends FragmentActivity {
	
	private static final String APP_ID = "b2dff1d7ef384e0f82a749fb6c723f81"; // Find this in your developer console
	private static final String API_KEY = "Hackathon";// Find this in your developer 

	private LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
    private String locationProvider = LocationManager.NETWORK_PROVIDER;
    
    private Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
    private LocationListener locationListener = new LocationListener(){
        public void onLocationChanged(Location location) {
          // Called when a new location is found by the network location provider.
          lastKnownLocation = location;
        }

		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
			
		}

		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
    };
        
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /** Home button */
        ImageButton homeButton = (ImageButton) findViewById(R.id.actionLogoIcon);
        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,
                        MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        
        if( savedInstanceState == null ) {
            FragmentManager fragmentManager = getSupportFragmentManager();
          	FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
          	
          	DisplayListFragment dlf = new DisplayListFragment();
  			fragmentTransaction.add(R.id.blank_fragment, dlf);
            fragmentTransaction.commit();
		}
        
        //CloudMine
        DeviceIdentifier.initialize(getApplicationContext()); // This initializes the unique ID that will be sent with each request to identify this user
        CMApiCredentials.initialize(APP_ID, API_KEY); // This will initialize your credentials
        
        double longitude = lastKnownLocation.getLongitude();
        double latitude = lastKnownLocation.getLatitude();
        
        // Update data with last known lat/long
        updateData("[region_id=/philawest/]", false);
    }
    
    public void updateData(String search, Boolean location) {
    	// if we're refreshing the location data
    	if(location) {
    		locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);
    	}
        CMStore.getStore().loadApplicationObjectsSearch(search, new SimpleCMObjectResponseCallback(){
        	public void onComplete(SimpleCMObjectResponse response){
        		List<SimpleCMObject> objects = response.getObjects();
        		System.out.println(objects);
        		locationManager.removeUpdates(locationListener);
        	}
        });
    }
}
