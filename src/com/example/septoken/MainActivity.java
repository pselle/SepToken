package com.example.septoken;

import java.util.List;
import java.util.concurrent.Future;

import com.cloudmine.api.CMApiCredentials;
import com.cloudmine.api.DeviceIdentifier;
import com.cloudmine.api.SimpleCMObject;
import com.cloudmine.api.rest.CMStore;
import com.cloudmine.api.rest.callbacks.SimpleCMObjectResponseCallback;
import com.cloudmine.api.rest.response.SimpleCMObjectResponse;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
        
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /** Home button */
        ImageButton homeButton = (ImageButton) findViewById(R.id.actionLogoIcon);
        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,
                        MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        
        if( savedInstanceState == null ) {
            FragmentManager fragmentManager = getSupportFragmentManager();
          	FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
          	
          	DisplayListFragment dlf = new DisplayListFragment();
  			fragmentTransaction.add(R.id.blank_fragment, dlf);
            fragmentTransaction.commit();
		}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int ID = item.getItemId();
        	if(ID == R.id.CenterCity){
        		
        	}else if(ID == R.id.WestPhiladelphia){
        		
        	}else if(ID == R.id.SouthPhiladelphia){
        		
        	}else if(ID == R.id.NorthPhiladelphia){
        		
        	}else if(ID == R.id.NortheastPhiladelphia){
        		
        	}else if(ID == R.id.NorthwestPhiladelphia){
        		
        	}else if(ID == R.id.BucksCounty){
        		
        	}else if(ID == R.id.ChesterCounty){
        		
        	}else if(ID == R.id.DelawareCounty){
        		
        	}else if(ID == R.id.MontgomeryCounty){
        		
        	}else if(ID == R.id.DelNJersey){
        		
        	}
        	
        	
        		onSearchRequested();
        		
        		return true;
            
/*            
        default:
            return super.onOptionsItemSelected(item);
*/            
        
    }
    
}
