package com.example.example;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

class LocationRetriever extends Thread implements DefaultLifecycleObserver, LocationListener {



    private static final String TAG = "LocationRetriever";

    private final Context context;
    private final LocationManager locationManager;
    private final SuccessListener successListener;
    private final FailureListener failureListener;

   private boolean fitLocation=true;

    public boolean isFitLocation() {
        return fitLocation;
    }

    public void setFitLocation(boolean fitLocation) {
        this.fitLocation = fitLocation;
    }

    // private final   FirstFragment firstFragment;
 public   LocationRetriever(@NonNull Context context, @NonNull LifecycleOwner lifecycleOwner, @NonNull SuccessListener successListener, @NonNull FailureListener failureListener) {
        Log.e(TAG, "LocationRetriever");
       // thread.start();


        this.context = context;
       //  this.locationManager=(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        this.locationManager = ServiceUtil.getLocationManager(this.context);//Context.LOCATION_SERVICE
        this.successListener = successListener;//interface
        this.failureListener = failureListener;//interface

        lifecycleOwner.getLifecycle().addObserver(this);
    }

  /*  public static LocationManager getLocationManager(@NonNull Context context) {
        return ContextCompat.getSystemService(context, LocationManager.class);
    }*/





    @Override
    public void onStart(@NonNull LifecycleOwner owner) {


    //  Boolean  Myprovider=firstFragment.getProviderNETWORK();//GET TRUE OR FALSE

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            Log.w(TAG, "No location permission!");
            failureListener.onFailure();
        }


       /* if(Myprovider) {
           // LocationProvider provider = locationManager.getProvider(LocationManager.NETWORK_PROVIDER);
           provider = locationManager.getProvider(LocationManager.NETWORK_PROVIDER);
            Log.e(TAG, "LocationProvider of network provider  is :" + provider);
        }else {
              provider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
            Log.e(TAG, "LocationProvider of GPS provider  is :"+provider);
        }*/
        Log.e(TAG, "line 85 LocationRetriever boolean = "+fitLocation);
        if(fitLocation) {

            LocationProvider provider = locationManager.getProvider(LocationManager.NETWORK_PROVIDER);
            if (provider == null) {
                Log.w(TAG, "network provider is null. Trying GPS provider .");
                //  provider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
                provider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
            }
            if (provider == null) {
                Log.w(TAG, "Network provider is null. Unable to retrieve location.");
                failureListener.onFailure();
                return;
            }
            Location lastKnown = locationManager.getLastKnownLocation(provider.getName());
            if (lastKnown != null) {
                Log.i(TAG, "Using last known location. line 109");
                successListener.onSuccess(lastKnown);

            } else {
                Log.i(TAG, "No last known location. Requesting a single update.");
                // Toast.makeText(context, "No last known location.\n\n Requesting a single update.:" , Toast.LENGTH_LONG).show();
                //  provider = locationManager.getProvider(LocationManager.NETWORK_PROVIDER);
                locationManager.requestSingleUpdate(provider.getName(), this, null);
            }
        }else {

            LocationProvider provider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
            if (provider == null) {
                Log.w(TAG, "network provider is null. Trying GPS provider .");
                //  provider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
                provider = locationManager.getProvider(LocationManager.NETWORK_PROVIDER);
            }
            if (provider == null) {
                Log.w(TAG, "Network provider is null. Unable to retrieve location.");
                failureListener.onFailure();
                return;
            }
            Location lastKnown = locationManager.getLastKnownLocation(provider.getName());
            if (lastKnown != null) {
                Log.i(TAG, "Using last known location. line 109");
                successListener.onSuccess(lastKnown);

            } else {
                Log.i(TAG, "No last known location. Requesting a single update.");
                // Toast.makeText(context, "No last known location.\n\n Requesting a single update.:" , Toast.LENGTH_LONG).show();
                //  provider = locationManager.getProvider(LocationManager.NETWORK_PROVIDER);
                locationManager.requestSingleUpdate(provider.getName(), this, null);
            }


        }



    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        Log.e(TAG, "Removing any possible location listeners.");
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(@Nullable Location location) {
        Log.d(TAG, "[onLocationChanged] Successfully retrieved location." + location);
        if (location != null) {
            Log.w(TAG, "[onLocationChanged] Successfully retrieved location.");
            successListener.onSuccess(location);
            Log.w(TAG, "[onLocationChanged] Successfully retrieved location" + location);
        } else {
            Log.w(TAG, "[onLocationChanged] Null location.");
            failureListener.onFailure();
        }
    }

    @Override
    public void onStatusChanged(@NonNull String provider, int status, @Nullable Bundle extras) {
        Log.i(TAG, "[onStatusChanged] Provider: " + provider + " Status: " + status);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        Log.i(TAG, "[onProviderEnabled] Provider: " + provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        Log.i(TAG, "[onProviderDisabled] Provider: " + provider);
    }

     interface SuccessListener {// back call the result
         void onSuccess(@NonNull Location location);
     }


     interface FailureListener {
         void onFailure();
     }
}
