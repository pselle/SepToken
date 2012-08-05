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

import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class DisplayMapActivity extends MapActivity {

	private MapView mapView;
	private List<Overlay> mapOverlays;
	private IconItemizedOverlay iconToken;
	private MyLocationOverlay currentLocationOverlay;
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
		setContentView(R.layout.mapview);
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapOverlays = mapView.getOverlays();

		// Getting JSON
		Bundle bundle = new Bundle();
		bundle = getIntent().getExtras();
		FareLocations fl = new FareLocations();
		fl.location_name = bundle.getString("name");
		fl.location_address = bundle.getString("address");
		fl.location_hours = bundle.getString("hours");
		fl.payment_accepted = bundle.getString("payment_accepted");
		fl.gps_lat = bundle.getDouble("latitude");
		Log.i("Geo Tags", Double.toString(bundle.getDouble("latitude")));
		fl.gps_long = bundle.getDouble("longitude");
		
		LinearLayout overlayLayout = (LinearLayout)findViewById(R.id.moreInformation);
		TextView hours = (TextView)findViewById(R.id.hours);
		hours.setText(fl.location_hours);
		TextView soldItems = (TextView)findViewById(R.id.soldItems);
		soldItems.setText(getSoldItems(fl.payment_accepted));
		TextView passes =  (TextView)findViewById(R.id.passes);
		passes.setText(getPasses(fl.payment_accepted));
		TextView paymentAccepted = (TextView)findViewById(R.id.paymentAccepted);
		paymentAccepted.setText(paymentsAccepted(fl.payment_accepted));
		overlayLayout.setVisibility(View.VISIBLE);
		
		//boolean singleLocation = bundle.getBoolean("singleLocation");
		/*String jsonBundle = bundle.getString("jsonBundle");
		JSONArray response;
		try {
			response = new JSONArray(jsonBundle);
			addIcons(response);
		} catch (JSONException e) {
			e.printStackTrace();
		}*/
		addIcons(fl);
	}

	public void addIcons(FareLocations fl) {
		drawableToken = this.getResources().getDrawable(
				R.drawable.map_icon_token);
		iconToken = new IconItemizedOverlay(drawableToken, mapView);

		drawIcon(fl, iconToken);
	}
	
	public void drawIcon(FareLocations fl,IconItemizedOverlay icon) {
		int stopInt = 0;

		GeoPoint pointV = new GeoPoint(
				(int) ((fl.gps_lat) * 1E6),
				(int) ((fl.gps_long) * 1E6));
		String locationName = "<font color=\"#F24829\" size=\"17dip\"><b>"
				+ fl.location_name + "</b><br/>";

		String locationAddress = "<font color=\"#888888\" size=\"14dip\"><b>"
				+ fl.location_address + "</b><br/>";

		String title = locationName + locationAddress;
		String details = "";
		OverlayItem overlayvehicle = new OverlayItem(pointV, title,
				details);
		icon.addOverlay(overlayvehicle);
		stopInt++;

		if (stopInt > 0) {
			mapOverlays.add(icon);
		}
	}
	
	public String getSoldItems(String pa){
		String paCorrect = "";
		if (pa.contains("T")){
			paCorrect += "Tokens|";
		}
		if (pa.contains("M")){
			paCorrect += "Maps|";
		}
		if (pa.contains("X")){
			paCorrect += "Train Tickets|";
		}
		return paCorrect;
	}
	
	public String getPasses(String pa){
		String paCorrect = "Passes: ";
		if (pa.contains("P")){
			paCorrect += "Transpass|";
		}
		if (pa.contains("1") || pa.contains("2") || pa.contains("3") || pa.contains("4") ||
				pa.contains("A") || pa.contains("S") || pa.contains("C") || pa.contains("I")||
				pa.contains("D")){
			paCorrect += "Passes: ";
			if (pa.contains("1")){
				paCorrect += " 1 ";
			}
			if (pa.contains("2")){
				paCorrect += " 2 ";
			}
			if (pa.contains("3")){
				paCorrect += " 3 ";
			}
			if (pa.contains("4")){
				paCorrect += " 4 ";
			}
			if (pa.contains("A")){
				paCorrect += "Anywhere";
			}
			if (pa.contains("S")){
				paCorrect += " Suburban ";
			}
			if (pa.contains("C")){
				paCorrect += " One Day ";
			}
			if (pa.contains("I")){
				paCorrect += " Monthly ";
			}
			if (pa.contains("D")){
				paCorrect += " Independence ";
			}
		}
		
		return paCorrect;
	}
	
	public String paymentsAccepted (String payments){
		String cardsAccepted = "Cash Only";
		if (payments.contains("Z")){
			cardsAccepted = "Cards and Cash Accepted";
		}	
		return cardsAccepted;
	}


	/*public void drawIcons(String type, JSONArray response,
			IconItemizedOverlay icon) {
		int stopInt = 0;

		for (int i = 0; i < response.length(); i++) {
			JSONObject row;
			try {
				row = response.getJSONObject(i);
				FareLocations fl = new FareLocations();
				fl.gps_lat = row.getDouble("gps_lat");
				fl.gps_long = row.getDouble("gps_long");
				fl.location_address = row.getString("location_address");
				fl.location_hours = row.getString("location_hours");
				GeoPoint pointV = new GeoPoint(
						(int) ((fl.gps_lat) * 1E6),
						(int) ((fl.gps_long) * 1E6));
				String locationName = "<font color=\"#F24829\" size=\"17dip\"><b>"
						+ fl.location_name + "</b><br/>";

				String locationAddress = "<font color=\"#888888\" size=\"14dip\"><b>"
						+ fl.location_address + "</b><br/>"
						+ fl.payment_accepted + "<br/>";

				String title = locationName + locationAddress;
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
	}*/

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Display a users location on the map
	 */
	public void useGPS(){
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 3, (LocationListener) this);
		
		currentLocationOverlay = new MyLocationOverlay(this, mapView);
		if (currentLocationOverlay != null)
			mapOverlays.add(currentLocationOverlay);
	}
}
