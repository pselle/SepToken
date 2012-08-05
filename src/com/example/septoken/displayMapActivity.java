package com.example.septoken;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class displayMapActivity extends MapActivity {

	private MapView mapView;
	private List<Overlay> mapOverlays;
	private IconItemizedOverlay iconToken;
	private Drawable drawableToken;

	// For GPS things
	private LocationManager locationManager;
	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 3; // in
																		// Meters
	private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in
																	// Milliseconds
	private Drawable gpsDrawable;
	private MyLocationOverlay gpsLoc;

	// For determining map position
	private int minLatitude = (int) (+81 * 1E6);
	private int maxLatitude = (int) (-81 * 1E6);
	private int minLongitude = (int) (+181 * 1E6);
	private int maxLongitude = (int) (-181 * 1E6);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapOverlays = mapView.getOverlays();

		// Getting JSON
		Bundle bundle = new Bundle();
		bundle = getIntent().getExtras();
		String jsonBundle = bundle.getString("jsonBundle");
		JSONArray response;
		try {
			response = new JSONArray(jsonBundle);
			addIcons(response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void addIcons(JSONArray response) {
		drawableToken = this.getResources().getDrawable(
				R.drawable.map_icon_token);
		iconToken = new IconItemizedOverlay(drawableToken, mapView);

		drawIcons("Fare", response, iconToken);
	}

	public void drawIcons(String type, JSONArray response,
			IconItemizedOverlay icon) {
		int stopInt = 0;

		for (int i = 0; i < response.length(); i++) {
			JSONObject row;
			try {
				row = response.getJSONObject(i);
				FareLocations fl = new FareLocations();
				fl.gps_lat = row.getString("gps_lat");
				fl.gps_long = row.getString("gps_long");
				fl.location_address = row.getString("location_address");
				fl.location_hours = row.getString("location_hours");
				GeoPoint pointV = new GeoPoint(
						(int) (Double.parseDouble(fl.gps_lat) * 1E6),
						(int) (Double.parseDouble(fl.gps_long) * 1E6));
				String locationAddress = "<font color=\"#F24829\" size=\"17dip\"><b>"
						+ fl.location_address + "</b><br/>";

				String locationHours = "<font color=\"#888888\" size=\"14dip\"><b>"
						+ fl.location_hours + "</b><br/>";

				String title = locationAddress + locationHours;
				String details = "Click for more details";
				OverlayItem overlayvehicle = new OverlayItem(pointV, title,
						details);
				icon.addOverlay(overlayvehicle);
				stopInt++;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (stopInt > 0) {
				mapOverlays.add(icon);
			}
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
