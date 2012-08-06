package com.example.septoken;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.readystatesoftware.mapviewballoons.BalloonItemizedOverlay;
import com.readystatesoftware.mapviewballoons.BalloonOverlayView;


public class IconItemizedOverlay  extends BalloonItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> tOverlays = new ArrayList<OverlayItem>();
	private Context c;
	final private int ICON_SIZE = 24;

	public IconItemizedOverlay(Drawable defaultMarker, MapView mapView) {
		super(boundCenterBottom(defaultMarker),mapView);
		c = mapView.getContext();
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return tOverlays.get(i);

	}

	@Override
	public int size() {
		return tOverlays.size();
	}

	public void addOverlay(OverlayItem overlay) {
		setBalloonBottomOffset(ICON_SIZE);
		tOverlays.add(overlay);
		populate();
	}
	
	
	@Override
	protected boolean onBalloonTap(int index, OverlayItem item) {
		
		return true;
	}
	
	@Override
	protected BalloonOverlayView<OverlayItem> createBalloonOverlayView() {
		// use our custom balloon view with our custom overlay item type:
		return new CustomBalloon<OverlayItem>(getMapView().getContext(), getBalloonBottomOffset());
	}

}