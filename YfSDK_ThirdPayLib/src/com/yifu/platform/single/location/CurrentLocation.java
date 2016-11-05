package com.yifu.platform.single.location;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;


import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class CurrentLocation  {
	private Context context;
	private static CurrentLocation instance;

	
	
	public void initlocation(){
		LocationManager locationManager;
		String serviceName = Context.LOCATION_SERVICE;
		//locationManager = (LocationManager)getSystemService(serviceName);
		locationManager = (LocationManager)context.getSystemService(serviceName);
		// String provider = LocationManager.GPS_PROVIDER;
		String provider = LocationManager.NETWORK_PROVIDER;

		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		// String provider = locationManager.getBestProvider(criteria, true);

		Location location = locationManager.getLastKnownLocation(provider);
		
		locationManager.requestLocationUpdates(provider, 2000, 10,
		locationListener);
	}

	private final LocationListener locationListener = new LocationListener() {
		public void onLocationChanged(Location location) {
		}

		public void onProviderDisabled(String provider) {
		}

		public void onProviderEnabled(String provider) {
		}

		public void onStatusChanged(String provider, int status,
		Bundle extras) {
		}
	};

	
}