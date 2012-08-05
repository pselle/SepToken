package com.example.septoken;

import java.util.ArrayList;
import java.util.List;

import com.cloudmine.api.CMApiCredentials;
import com.cloudmine.api.CMGeoPoint;
import com.cloudmine.api.DeviceIdentifier;
import com.cloudmine.api.SimpleCMObject;
import com.cloudmine.api.rest.AndroidCMWebService;
import com.cloudmine.api.rest.CMStore;
import com.cloudmine.api.rest.CMWebService;
import com.cloudmine.api.rest.callbacks.SimpleCMObjectResponseCallback;
import com.cloudmine.api.rest.response.SimpleCMObjectResponse;
import com.google.android.maps.MapActivity;
import com.septrip.android.transitView.TransitViewActivity;
import com.septrip.android.transitView.displayClasses.BusRoutesDisplayFragment;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class DisplayListFragment extends Fragment {
/*	
	private LocationManager locationManager = (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);
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
*/	
	//Cloudmine
	private static final String APP_ID = "89134dac5dff4cb7bc27b68b2488c40f"; // Find this in your developer console
	private static final String API_KEY = "b2dff1d7ef384e0f82a749fb6c723f81";// Find this in your developer 
	
	// List that will host our items
	private ArrayList<FareLocations> alfl = null;
	private ListView lstTest;
	private FareLocationsAdapter flAdapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment, used for dividers
		return inflater.inflate(R.layout.listview, container, false);
	}
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// Initialize ListView
		lstTest = (ListView) getActivity().findViewById(R.id.list);
		alfl = new ArrayList<FareLocations>();
		flAdapter = new FareLocationsAdapter(DisplayListFragment.this.getActivity(), R.layout.listrow, alfl);
		// Set the above adapter as the adapter of choice for our list
		lstTest.setAdapter(flAdapter);
		lstTest.setTextFilterEnabled(false);
		lstTest.setFastScrollEnabled(true);
		
		//CloudMine
        DeviceIdentifier.initialize(this.getActivity().getApplicationContext()); // This initializes the unique ID that will be sent with each request to identify this user
        CMApiCredentials.initialize(APP_ID, API_KEY); // This will initialize your credentials
        
      //  double longitude = lastKnownLocation.getLongitude();
       // double latitude = lastKnownLocation.getLatitude();
        
        // Update data with last known lat/long
        updateData("[region_id=/philawest/]", false);
        
        lstTest.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String rId = alfl.get(arg2).location_name;
				
				Intent intent = new Intent(DisplayListFragment.this.getActivity(),
						DisplayMapActivity.class);
				intent.putExtra("shortName", rId);
				intent.putExtra("vehicle", "Bus");
				intent.putExtra("reload", 0);
				DisplayListFragment.this.startActivity(intent);

				// Toast.makeText(getApplicationContext(), "Selected Item: " +
				// rId , Toast.LENGTH_LONG).show();
				Log.d("BRoutes", "Item clicked "
						+ alrts.get(arg2).short_name);

			}
		});
	}
	
	public void updateData(String search, Boolean location) {
    	// if we're refreshing the location data
    	if(location) {
    	//	locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);
    	}
		CMWebService service = AndroidCMWebService.getService().getService();
		service.asyncLoadObjects(new SimpleCMObjectResponseCallback() {
			@Override
			public void onCompletion(SimpleCMObjectResponse response) {
				//System.out.println("Got response!");
				// System.out.println(response.getObjects());
				List<SimpleCMObject> list = response.getObjects();
				for (int i = 0; i < list.size(); i++) {
					FareLocations fare = new FareLocations();
					fare.location_name = list.get(i).getString("location_name");
					fare.location_address = list.get(i).getString("location_address");
					fare.location_hours = list.get(i).getString("hours");
					fare.payment_accepted = list.get(i).getString("payment_accepted");
					CMGeoPoint gp = list.get(i).getGeoPoint("location");
					fare.gps_lat = gp.getLatitude();
					fare.gps_long = gp.getLongitude();
					
					alfl.add(fare);
				}
					
				flAdapter.notifyDataSetChanged();
				
			}
        });
		
    }
}
