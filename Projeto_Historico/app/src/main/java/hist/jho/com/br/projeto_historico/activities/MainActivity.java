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
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hist.jho.com.br.projeto_historico.Constants;
import hist.jho.com.br.projeto_historico.R;
import hist.jho.com.br.projeto_historico.activities.settings_activity.SettingsActivity;
import hist.jho.com.br.projeto_historico.adapter.RecyclersViewAdapter;
import hist.jho.com.br.projeto_historico.adapter.wrapping.layoutmanager.WrappingLinearLayoutManager;
import hist.jho.com.br.projeto_historico.async.HistAsync;
import hist.jho.com.br.projeto_historico.fragments.Fragment_Images;
import hist.jho.com.br.projeto_historico.model.ImageViewCard;

import static hist.jho.com.br.projeto_historico.Constants.CANCEL;
import static hist.jho.com.br.projeto_historico.Constants.MSG;
import static hist.jho.com.br.projeto_historico.Constants.SECONDS_DELAYED;
import static hist.jho.com.br.projeto_historico.Constants.TIME_UPDATES_REFRESH;
import static hist.jho.com.br.projeto_historico.Constants.TITLE;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener{

  private CollapsingToolbarLayout collapsingToolbarLayout;
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private CardView cardView1;
  private CardView cardView2;
  private CardView cardView3;
  private CardView cardViewGallery;
  private CardView cardViewImage;
  private RecyclerView recyclerView;
  private List<ImageViewCard> imagesCard;
  private ImageView imageView;

  @TargetApi(Build.VERSION_CODES.JELLY_BEAN) @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE); //LayoutInflater.from(this);
    ViewGroup container = (ViewGroup) toolbar.getParent();
    final View rootView = inflater.inflate(R.layout.app_bar_main, container, false);
    final View rootView2 = inflater.inflate(R.layout.item_card_images, container, false);

    cardView1 = (CardView) rootView.findViewById(R.id.cardview1);
    cardView2 = (CardView) rootView.findViewById(R.id.cardview2);
    cardView3 = (CardView) rootView.findViewById(R.id.cardview3);
    cardViewGallery = (CardView) rootView2.findViewById(R.id.cV_Gallery);

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
      cardViewGallery.setElevation(8);
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

    mSwipeRefreshLayout.setOnRefreshListener(this);

    Log.d("Swipe", "onRefresh");
    mSwipeRefreshLayout.setColorSchemeResources(R.color.blue, R.color.red);
    Log.d("Swipe", "onRefresh1");

    //gridGallery();

    Fragment_Images frag = (Fragment_Images) getSupportFragmentManager().findFragmentByTag("mainFrag");
    //frag.setmActivity(this);
    if(frag == null) {
      frag = new Fragment_Images();
      FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
      View view = inflater.inflate(R.layout.content_main, container, false);
      View inflater1 =  view.findViewById(R.id.rl_fragment_container);
      ft.replace(inflater1.getId(), frag, "mainFrag");
      ft.commit();
    }

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
  }

  private void gridGallery(){
    recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    //recyclerView.setLayoutManager(new WrappingLinearLayoutManager(this));
    recyclerView.setHasFixedSize(false);
    initializeData();
    //recyclerView.setNestedScrollingEnabled(false);
    RecyclersViewAdapter adapter = new RecyclersViewAdapter(imagesCard);
    //RecyclersViewAdapter.setListViewHeightBasedOnChildren();
    recyclerView.setAdapter(adapter);
    //recyclerView.setNestedScrollingEnabled(false);
  }

  private void initializeData(){
    imagesCard = new ArrayList<>();
    imagesCard.add(new ImageViewCard(R.drawable.ic_camara));
    imagesCard.add(new ImageViewCard(R.drawable.side_nav_bar));
    imagesCard.add(new ImageViewCard(R.drawable.ic_camara));
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

  public void textScreen(View view){

    TextView mTextView = (TextView) view;

    int id = checkId(mTextView);
    TextView mTextViewTitle = (TextView) findViewById(id);

    Intent intent = new Intent(MainActivity.this, TextViewActivity.class);

    intent.putExtra(Constants.TEXT_ACTIVITY, mTextView.getText().toString());
    intent.putExtra(Constants.TEXT_ACTIVITY_TITLE, mTextViewTitle.getText().toString());
    startActivity(intent);
  }

  private int checkId(TextView textView){
    switch(textView.getId()){

      case R.id.tv1:
        return R.id.tvinfo1;
      case R.id.tv2:
        return R.id.tvinfo2;
      case R.id.tv3:
        return R.id.tvinfo3;
    }
    return -1;
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

  public List<ImageViewCard> getSetCarList(int qtd){
    String[] models = new String[]{"Gallardo", "Vyron", "Corvette", "Pagani Zonda", "Porsche 911 Carrera", "BMW 720i", "DB77", "Mustang", "Camaro", "CT6"};
    String[] brands = new String[]{"Lamborghini", " bugatti", "Chevrolet", "Pagani", "Porsche", "BMW", "Aston Martin", "Ford", "Chevrolet", "Cadillac"};
    int[] photos = new int[]{R.drawable.ic_camara, R.drawable.ic_menu_camera, R.drawable.ic_camara, R.drawable.ic_menu_camera, R.drawable.ic_camara, R.drawable.ic_camara, R.drawable.ic_camara, R.drawable.ic_camara, R.drawable.ic_camara, R.drawable.ic_camara};
    List<ImageViewCard> listAux = new ArrayList<>();

    for(int i = 0; i < qtd; i++){
      ImageViewCard c = new ImageViewCard(photos[i % models.length]);
      listAux.add(c);
    }
    return(listAux);
  }

}

