package com.example.septoken;

import java.util.ArrayList;

import com.cloudmine.api.CMApiCredentials;
import com.cloudmine.api.DeviceIdentifier;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class DisplayListFragment extends Fragment {
	
	//Cloudmine
	private static final String APP_ID = "b2dff1d7ef384e0f82a749fb6c723f81"; // Find this in your developer console
	private static final String API_KEY = "Hackathon";// Find this in your developer 
	
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
	}
}
