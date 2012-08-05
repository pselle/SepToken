package com.example.septoken;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FareLocationsAdapter extends ArrayAdapter<FareLocations> {
	int resource;
	String response;
	Context context;
	//Initialize adapter
	public FareLocationsAdapter(Context context, int resource, List<FareLocations> items) {
		super(context, resource, items);
		this.resource=resource;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LinearLayout alertView;
		//Get the current alert object
		FareLocations fl = getItem(position);
		

		//Inflate the view
		if(convertView==null)
		{
			alertView = new LinearLayout(getContext());
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater vi;
			vi = (LayoutInflater)getContext().getSystemService(inflater);
			vi.inflate(resource, alertView, true);
		}
		else
		{
			alertView = (LinearLayout) convertView;
		}
		//Get the text boxes from the listitem.xml file
		TextView locationName = (TextView)alertView.findViewById(R.id.name);
		TextView locationAddress = (TextView)alertView.findViewById(R.id.address);
		TextView locationHour = (TextView) alertView.findViewById(R.id.hours);
		TextView distant = (TextView) alertView.findViewById(R.id.distance);

		locationName.setText(fl.location_name);
		locationAddress.setText(fl.location_address);
		locationHour.setText(fl.location_hours);
		distant.setVisibility(View.GONE);
		
		
		
		
		
		return alertView;
	}
}
