package com.example.septoken;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class DisplayListFragment extends Fragment {
	
	// List that will host our items and allow us to modify that array adapter
	private ArrayList<FareLocations> alfl = null;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment, used for dividers
		return inflater.inflate(R.layout.listview, container, false);
	}
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		// Initialize ListView
		ListView lstTest = (ListView) getActivity().findViewById(R.id.list);
		
		
	}
}
