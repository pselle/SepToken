package com.example.septoken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class MapActivity extends Activity {
	
	private MapView mapView;
	
	// For GPS things
	private LocationManager locationManager;
	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 3; // in
																		// Meters
	private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in
																	// Milliseconds
	private Drawable gpsDrawable;
	private MyLocationOverlay gpsLoc;
	
	//For determining map position
	private int minLatitude = (int) (+81 * 1E6);
	private int maxLatitude = (int) (-81 * 1E6);
	private int minLongitude = (int) (+181 * 1E6);
	private int maxLongitude = (int) (-181 * 1E6);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /** Getting route info */
		Bundle bundle = new Bundle();
		bundle = getIntent().getExtras();
		String jsonBundle = bundle.getString("jsonBundle");
		
		JSONArray response;
		try {
			response = new JSONArray(jsonBundle);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		/*for (int i = 0; i < response.length(); i++) {
		    JSONObject row = response.getJSONObject(i);
		    //id = row.getInt("id");
		    //name = row.getString("name");
		}*/

    }
    
    public void drawIcons(String type, JSONArray response){
    	
    }

}
