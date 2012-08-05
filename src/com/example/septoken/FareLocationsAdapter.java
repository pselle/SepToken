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
/*		TextView routeShortName = (TextView)alertView.findViewById(R.id.txtRouteShortName);
		TextView routeLongName = (TextView)alertView.findViewById(R.id.txtRouteLongName);*/

		//Assign the appropriate data from our alert object above
		//Old code that contained better names for Night Owl service
		/*if(al.short_name.equals("BSO")){
			routeShortName.setText("Broad Street Line (Owl Service)");
			routeLongName.setText(al.long_name);
		}else if(al.short_name.equals("MFO")){
			routeShortName.setText("Market Frankford Line (Owl Service)");
			routeLongName.setText(al.long_name);
		}else{
		routeShortName.setText(al.short_name);
		routeLongName.setText(al.long_name);
		}*/
		return alertView;
	}
}
