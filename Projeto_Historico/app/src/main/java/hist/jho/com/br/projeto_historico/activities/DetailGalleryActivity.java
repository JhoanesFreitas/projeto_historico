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

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import hist.jho.com.br.projeto_historico.R;
import hist.jho.com.br.projeto_historico.model.ImageModelGallery;

public class DetailGalleryActivity extends AppCompatActivity{

  /**
   * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
   * sections. We use a {@link FragmentPagerAdapter} derivative, which will keep every loaded
   * fragment in memory. If this becomes too memory intensive, it may be best to switch to a {@link
   * android.support.v4.app.FragmentStatePagerAdapter}.
   */
  private SectionsPagerAdapter mSectionsPagerAdapter;

  /**
   * The {@link ViewPager} that will host the section contents.
   */
  private ViewPager mViewPager;
  protected ArrayList<ImageModelGallery> data = new ArrayList<>();
  protected int pos;
  private boolean status = false;
  private int statusAux = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail_gallery);

    data = getIntent().getParcelableArrayListExtra("data");
    pos = getIntent().getIntExtra("pos", 0);

    final AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
    final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

    toolbar.setTitle(data.get(pos).getName());
    toolbar.setTitleTextColor(Color.WHITE);
    toolbar.setSubtitleTextColor(Color.WHITE);
    //toolbar.setVisibility(View.VISIBLE);
    //toolbar.setBackgroundColor(Color.TRANSPARENT);
    setSupportActionBar(toolbar);

    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
    //appBarLayout.setVisibility(View.VISIBLE);
    // Create the adapter that will return a fragment for each of the three
    // primary sections of the activity.
    mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

    // Set up the ViewPager with the sections adapter.
    mViewPager = (ViewPager) findViewById(R.id.container);

    //mViewPager.onInterceptTouchEvent();
    mViewPager.setOnTouchListener(new View.OnTouchListener(){
      @TargetApi(Build.VERSION_CODES.M) @Override public boolean onTouch(View v, MotionEvent event){

        /*if(mViewPager.isTransitionGroup()){
          Log.d("st", "st");
          if(!status){
            appBarLayout.setVisibility(View.VISIBLE);
            toolbar.setVisibility(View.VISIBLE);
            //if(statusAux == 3)
              status = !status;
          } else{
            appBarLayout.setVisibility(View.GONE);
            toolbar.setVisibility(View.GONE);
            status = !status;
          }
        }*/
        return false;
      }
    });

    /*mViewPager.setOnClickListener(new ViewPager.OnClickListener(){
      @Override public void onClick(View v){
        Log.d("click", "click");
      }
    });*/

    mViewPager.setAdapter(mSectionsPagerAdapter);

    setTitle(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view){
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });

  }

  @Override public void onBackPressed(){
    super.onBackPressed();
    finish();
  }

  private void setTitle(final Toolbar mTitle){
    mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
      @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){

      }

      @Override public void onPageSelected(int position){
        mTitle.setTitle(data.get(position).getName());
      }

      @Override public void onPageScrollStateChanged(int state){

      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu){
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_detail_gallery, menu);
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
    } else if(id == android.R.id.home){
      finish();
    }

    return super.onOptionsItemSelected(item);
  }


  /**
   * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the
   * sections/tabs/pages.
   */
  public class SectionsPagerAdapter extends FragmentPagerAdapter{

    public SectionsPagerAdapter(FragmentManager fm){
      super(fm);
    }

    @Override
    public Fragment getItem(int position){
      // getItem is called to instantiate the fragment for the given page.
      // Return a PlaceholderFragment (defined as a static inner class below).
      return PlaceholderFragment.newInstance(position, data.get(position).getName(),
          data.get(position).getPhotoId());//PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount(){
      // Show 3 total pages.
      return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position){

      if(!data.isEmpty())
        return data.get(position).getName();

      return null;

      /*switch(position){
        case 0:
          return "SECTION 1";
        case 1:
          return "SECTION 2";
        case 2:
          return "SECTION 3";
      }*/
    }
  }

  /**
   * A placeholder fragment containing a simple view.
   */
  public static class PlaceholderFragment extends Fragment{
    /**
     * The fragment argument representing the section number for this fragment.
     */
    private static final String ARG_SECTION_NUMBER = "pos";
    private static final String ARG_SECTION_NAME = "section_number";
    private static final String ARG_SECTION_ID = "section_number";
    private int pos;
    private int id;
    private String name;

    /**
     * Returns a new instance of this fragment for the given section number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber){
      PlaceholderFragment fragment = new PlaceholderFragment();
      Bundle args = new Bundle();
      args.putInt(ARG_SECTION_NUMBER, sectionNumber);
      fragment.setArguments(args);
      return fragment;
    }

    /**
     * Returns a new instance of this fragment for the given section number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber, String name, int id){
      PlaceholderFragment fragment = new PlaceholderFragment();
      Bundle args = new Bundle();
      args.putInt(ARG_SECTION_NUMBER, sectionNumber);
      args.putString(ARG_SECTION_NAME, name);
      args.putInt(ARG_SECTION_ID, id);
      fragment.setArguments(args);
      return fragment;
    }

    public PlaceholderFragment(){
    }

    @Override public void setArguments(Bundle args){
      super.setArguments(args);
      this.pos = args.getInt(ARG_SECTION_NUMBER);
      this.name = args.getString(ARG_SECTION_NAME);
      this.id = args.getInt(ARG_SECTION_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
      View rootView = inflater.inflate(R.layout.fragment_detail_gallery, container, false);
      /*TextView textView = (TextView) rootView.findViewById(R.id.section_label);
      textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));*/

      ImageView imageView = (ImageView) rootView.findViewById(R.id.detail_image);
      Glide.with(getActivity()).load(id).into(imageView);

      return rootView;
    }
  }
}
