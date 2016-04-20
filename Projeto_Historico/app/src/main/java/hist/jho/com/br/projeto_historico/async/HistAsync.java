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

package hist.jho.com.br.projeto_historico.async;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Looper;
import android.provider.Settings;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import hist.jho.com.br.projeto_historico.Constants;
import hist.jho.com.br.projeto_historico.R;
import hist.jho.com.br.projeto_historico.activities.MainActivity;
import hist.jho.com.br.projeto_historico.localization.Localization;
import hist.jho.com.br.projeto_historico.toast_trace.ToastTrace;

import static hist.jho.com.br.projeto_historico.Constants.CANCEL;
import static hist.jho.com.br.projeto_historico.Constants.MSG;
import static hist.jho.com.br.projeto_historico.Constants.TITLE;

/**
 * Created by jhoanesfreitas on 19/04/16.
 */
public class HistAsync extends AsyncTask<Void, Void, String>{

  private Context mContext;
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private Localization mLocalization;
  private Activity mActivity;

  private double latitude = 0;
  private double longitude = 0;

  public HistAsync(Context context, SwipeRefreshLayout swipeRefreshLayout, Activity mActivity){
    setmContext(context);
    setmSwipeRefreshLayout(swipeRefreshLayout);
    this.mActivity = mActivity;
    Log.d("OnPostExecute", "Constructor");
  }

  @Override protected void onPreExecute(){
    super.onPreExecute();
    setmLocalization(new Localization(getmContext(), mActivity));
    //mLocalization = new Localization(mContext);
    Log.d("OnPostExecute", "OnPreExecute");
  }

  @Override protected String doInBackground(Void... params){
    Log.d("OnPostExecute", "doInBackground");
    if(getmLocalization().isCanGetLocation()){
      setLatitude(getmLocalization().getmLatitude());
      setLongitude(getmLocalization().getmLongitude());
    }else {
      Constants.POP = true;
      //Looper.prepare();
      //getmLocalization().showSettingsAlert();
    }
    return null;
  }

  @Override protected void onPostExecute(String s){
    super.onPostExecute(s);
    getmSwipeRefreshLayout().setRefreshing(false);
    Log.d("OnPostExecute", getmLocalization().getmLatitude() + "");
    Log.d("OnPostExecute", getmLocalization().getmLongitude() + "");
    ToastTrace toastTrace = new ToastTrace(mContext);
    toastTrace.trace(getLatitude() + "\n" + getLongitude());
    getmLocalization().stopUsingGPS();
    ((MainActivity) mActivity).isPop();
    Log.d("OnPostExecute", "Finish");
  }

  public double getLatitude(){
    return latitude;
  }

  public void setLatitude(double latitude){
    this.latitude = latitude;
  }

  public double getLongitude(){
    return longitude;
  }

  public void setLongitude(double longitude){
    this.longitude = longitude;
  }

  public Context getmContext(){
    return mContext;
  }

  public void setmContext(Context mContext){
    this.mContext = mContext;
  }

  public SwipeRefreshLayout getmSwipeRefreshLayout(){
    return mSwipeRefreshLayout;
  }

  public void setmSwipeRefreshLayout(SwipeRefreshLayout mSwipeRefreshLayout){
    this.mSwipeRefreshLayout = mSwipeRefreshLayout;
  }

  public Localization getmLocalization(){
    return mLocalization;
  }

  public void setmLocalization(Localization mLocalization){
    this.mLocalization = mLocalization;
  }
}
