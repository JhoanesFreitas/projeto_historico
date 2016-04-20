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

package hist.jho.com.br.projeto_historico.activities;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.LayoutRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import hist.jho.com.br.projeto_historico.Constants;
import hist.jho.com.br.projeto_historico.R;
import hist.jho.com.br.projeto_historico.activities.settings_activity.SettingsActivity;
import hist.jho.com.br.projeto_historico.async.HistAsync;
import hist.jho.com.br.projeto_historico.toast_trace.ToastTrace;

import static hist.jho.com.br.projeto_historico.Constants.CANCEL;
import static hist.jho.com.br.projeto_historico.Constants.MSG;
import static hist.jho.com.br.projeto_historico.Constants.TIME_UPDATES_REFRESH;
import static hist.jho.com.br.projeto_historico.Constants.TITLE;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener{

  private CollapsingToolbarLayout collapsingToolbarLayout;
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private CardView cardView1;
  private CardView cardView2;
  private CardView cardView3;

  @TargetApi(Build.VERSION_CODES.JELLY_BEAN) @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE); //LayoutInflater.from(this);
    ViewGroup container = (ViewGroup) toolbar.getParent();
    final View rootView = inflater.inflate(R.layout.app_bar_main, container, false);

    cardView1 = (CardView) rootView.findViewById(R.id.cardview1);
    cardView2 = (CardView) rootView.findViewById(R.id.cardview2);
    cardView3 = (CardView) rootView.findViewById(R.id.cardview3);

    mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    try{
      fab.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
          Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
              .setAction("Action", null).show();
        }
      });
    } catch(Exception e){
      e.printStackTrace();
    }

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
      cardView1.setElevation(8);
      cardView2.setElevation(8);
      cardView3.setElevation(8);
      //cardView2.setCardElevation(88);
      Log.d("card", "enter");
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();


    collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
    //collapsingToolbarLayout.setTitle("Iuiuiu");
    collapsingToolbarLayout.setExpandedTitleColor(Color.CYAN);
    collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.CollapsingToolbarExpanded);
    Resources icon = getResources();
    @SuppressLint({ "NewApi", "LocalSuppress" }) Drawable drawable = icon.getDrawable(R.drawable.ic_camara, getTheme());
    collapsingToolbarLayout.setBackground(drawable);

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
      collapsingToolbarLayout.setElevation(99);
      toolbar.setElevation(99);
    }

    //ToastTrace toastTrace = new ToastTrace(getBaseContext());

    //TextView textView = (TextView) rootView.findViewById(R.id.tvinfo);
    //toastTrace.trace(textView.getText().toString());
    //textView.setEllipsize(TextUtils.TruncateAt.END);
    mSwipeRefreshLayout.setOnRefreshListener(this);

    Log.d("Swipe", "onRefresh");
    //mSwipeRefreshLayout.setColorSchemeColors(R.color.blue, R.color.red);
    mSwipeRefreshLayout.setColorSchemeResources(R.color.blue, R.color.red);
    Log.d("Swipe", "onRefresh1");

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
  }

  @Override public void onRefresh(){
    new Handler().postDelayed(new Runnable(){
      @Override
      public void run(){
        HistAsync histAsync = new HistAsync(getBaseContext(), mSwipeRefreshLayout, MainActivity.this);
        histAsync.execute();
      }
    }, TIME_UPDATES_REFRESH);
    Log.d("Swipe", "onRefreshSwipe");
  }

  public void isPop(){
    if(Constants.POP){
      showSettingsAlert();
      Constants.POP = false;
    }
  }

  public void showSettingsAlert(){

    try{
      AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);

      alBuilder.setTitle(TITLE);
      alBuilder.setMessage(MSG);

      alBuilder.setPositiveButton(R.string.action_settings,
          new DialogInterface.OnClickListener(){
            @Override public void onClick(DialogInterface dialog, int which){
              Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
              startActivity(intent);
            }
          });

      alBuilder.setNegativeButton(CANCEL,
          new DialogInterface.OnClickListener(){
            @Override public void onClick(DialogInterface dialog, int which){
              dialog.cancel();
            }
          });

      alBuilder.show();
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  @Override
  public void onBackPressed(){
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if(drawer.isDrawerOpen(GravityCompat.START)){
      drawer.closeDrawer(GravityCompat.START);
    } else{
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu){
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item){
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if(id == R.id.action_settings){
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item){
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if(id == R.id.nav_camera){
      // Handle the camera action
    } else if(id == R.id.nav_gallery){

    } else if(id == R.id.nav_slideshow){

    } else if(id == R.id.nav_manage){
      startActivity(new Intent(MainActivity.this, SettingsActivity.class));
    } else if(id == R.id.nav_share){

    } else if(id == R.id.nav_send){

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}
