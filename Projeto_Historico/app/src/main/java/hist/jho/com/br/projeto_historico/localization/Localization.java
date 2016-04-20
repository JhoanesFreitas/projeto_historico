/*
 * Copyright (c) 2016. jhoanesfreitas
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *            http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package hist.jho.com.br.projeto_historico.localization;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;

import hist.jho.com.br.projeto_historico.R;
import hist.jho.com.br.projeto_historico.toast_trace.ToastTrace;

import static hist.jho.com.br.projeto_historico.Constants.MIN_DISTANCE_CHANCE_FOR_UPDATES;
import static hist.jho.com.br.projeto_historico.Constants.MIN_TIME_BW_UPDATES;
import static hist.jho.com.br.projeto_historico.Constants.NOT_FOUND;
import static hist.jho.com.br.projeto_historico.Constants.TITLE;
import static hist.jho.com.br.projeto_historico.Constants.MSG;
import static hist.jho.com.br.projeto_historico.Constants.CANCEL;

/**
 * Created by jhoanesfreitas on 19/04/16.
 */
public class Localization extends Service implements LocationListener{

  private final Context mContext;
  private boolean isGPSEnable = false;
  private boolean isNetworkEnable = false;
  private boolean canGetLocation = false;

  private Location mLocation;
  private double mLatitude;
  private double mLongitude;

  private LocationManager mLocationManager;
  private ToastTrace toastTrace;
  private Activity mActivity;

  public Localization(Context mContext, Activity mActivity){
    this.mContext = mContext;
    toastTrace = new ToastTrace(getmContext());
    this.mActivity = mActivity;
    getLocation();
  }

  private Location getLocation(){

    try{
      setmLocationManager((LocationManager) mContext
          .getSystemService(LOCATION_SERVICE));

      setGPSEnable(mLocationManager
          .isProviderEnabled(LocationManager.GPS_PROVIDER));

      setNetworkEnable(mLocationManager
          .isProviderEnabled(LocationManager.NETWORK_PROVIDER));

      Log.d("Error", mLocationManager
          .isProviderEnabled(LocationManager.NETWORK_PROVIDER) + " ||");

      if(!isGPSEnable() && !isNetworkEnable()){
        Log.d("Error", "Error");
        Log.d("Error", isGPSEnable() + " | " + isNetworkEnable());
        toastTrace.trace(NOT_FOUND);
      } else{
        setCanGetLocation(true);

        if(isNetworkEnable()){
          getmLocationManager()
              .requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                  MIN_TIME_BW_UPDATES,
                  MIN_DISTANCE_CHANCE_FOR_UPDATES,
                  this);
          Log.d("Network", "Network");

          if(getmLocationManager() != null){
            setmLocation(getmLocationManager()
                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
            if(getmLocation() != null){
              setmLatitude(getmLocation().getLatitude());
              setmLongitude(getmLocation().getLongitude());
            }
          }
        }

        if(isGPSEnable()){
          if(getmLocation() == null){
            getmLocationManager().requestLocationUpdates(LocationManager.GPS_PROVIDER,
                MIN_TIME_BW_UPDATES,
                MIN_DISTANCE_CHANCE_FOR_UPDATES, this);
            Log.d("GPS_Enable", "GPS_ENABLE");

            if(getmLocationManager() != null){
              setmLocation(getmLocationManager()
                  .getLastKnownLocation(LocationManager.GPS_PROVIDER));
              if(getmLocation() != null){
                setmLatitude(getmLocation().getLatitude());
                setmLongitude(getmLocation().getLongitude());
              }
            }
          }
        }
      }
    } catch(Exception e){
      toastTrace.trace(NOT_FOUND);
    }
    return getmLocation();
  }

  public void stopUsingGPS(){
    if(getmLocationManager() != null){
      getmLocationManager().removeUpdates(Localization.this);
    }
  }

  public Context getmContext(){
    return mContext;
  }

  public boolean isGPSEnable(){
    return isGPSEnable;
  }

  public void setGPSEnable(boolean GPSEnable){
    isGPSEnable = GPSEnable;
  }

  public boolean isNetworkEnable(){
    return isNetworkEnable;
  }

  public void setNetworkEnable(boolean networkEnable){
    isNetworkEnable = networkEnable;
  }

  public boolean isCanGetLocation(){
    return canGetLocation;
  }

  public void setCanGetLocation(boolean canGetLocation){
    this.canGetLocation = canGetLocation;
  }

  public Location getmLocation(){
    return mLocation;
  }

  public void setmLocation(Location mLocation){
    this.mLocation = mLocation;
  }

  public double getmLatitude(){
    if(getmLocation() != null)
      setmLatitude(getmLocation().getLatitude());
    return mLatitude;
  }

  public void setmLatitude(double mLatitude){
    this.mLatitude = mLatitude;
  }

  public double getmLongitude(){
    if(getmLocation() != null)
      setmLongitude(getmLocation().getLongitude());
    return mLongitude;
  }

  public void setmLongitude(double mLongitude){
    this.mLongitude = mLongitude;
  }

  public LocationManager getmLocationManager(){
    return mLocationManager;
  }

  public void setmLocationManager(LocationManager mLocationManager){
    this.mLocationManager = mLocationManager;
  }

  @Override public void onLocationChanged(Location location){

  }

  @Override public void onStatusChanged(String provider, int status, Bundle extras){

  }

  @Override public void onProviderEnabled(String provider){

  }

  @Override public void onProviderDisabled(String provider){

  }

  @Nullable @Override public IBinder onBind(Intent intent){
    return null;
  }
}
