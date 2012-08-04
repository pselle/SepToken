package com.example.septoken;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.maps.OverlayItem;
import com.readystatesoftware.mapviewballoons.BalloonOverlayView;
import com.readystatesoftware.mapviewballoons.R;

public class CustomBalloon<Item extends OverlayItem> extends BalloonOverlayView<OverlayItem> {
	
	private TextView title;
	private TextView snippet;
	
	public CustomBalloon(Context context, int balloonBottomOffset) {
		super(context, balloonBottomOffset);
	}
	
	@Override
	protected void setupView(Context context, final ViewGroup parent) {
		
		// inflate our custom layout into parent
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.balloon_overlay, parent);
		
		// setup our fields
		title = (TextView) v.findViewById(R.id.balloon_item_title);
		snippet = (TextView) v.findViewById(R.id.balloon_item_snippet);

		// implement balloon close
		ImageView close = (ImageView) v.findViewById(R.id.balloon_close);
		close.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				parent.setVisibility(GONE);
			}
		});
		
	}
	
	@Override
	protected void setBalloonData(OverlayItem item, ViewGroup parent) {
		
		if (item.getTitle() != null) {
			title.setVisibility(VISIBLE);
			title.setText(Html.fromHtml(item.getTitle()));
		} else {
			title.setText("");
			title.setVisibility(INVISIBLE);
		}
		if (item.getSnippet() != null) {
			snippet.setVisibility(VISIBLE);
			snippet.setText(Html.fromHtml(item.getSnippet()));
		} else {
			snippet.setText("");
			snippet.setVisibility(INVISIBLE);
		}
	}

}
