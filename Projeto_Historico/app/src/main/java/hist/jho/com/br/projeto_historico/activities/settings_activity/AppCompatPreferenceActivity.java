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

package hist.jho.com.br.projeto_historico.activities.settings_activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import hist.jho.com.br.projeto_historico.activities.MainActivity;

/**
 * A {@link PreferenceActivity} which implements and proxies the necessary calls
 * to be used with AppCompat.
 */
public abstract class AppCompatPreferenceActivity extends PreferenceActivity{

  private AppCompatDelegate mDelegate;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    getDelegate().installViewFactory();
    getDelegate().onCreate(savedInstanceState);
    super.onCreate(savedInstanceState);
    Log.d("AppCompat", "Entrou");
  }

  /*@Override
  public boolean onOptionsItemSelected(MenuItem item){
    int id = item.getItemId();
    if(id == android.R.id.home){
      startActivity(new Intent(AppCompatPreferenceActivity.this, MainActivity.class));
      return true;
    }
    return super.onOptionsItemSelected(item);
  }*/

  @Override
  protected void onPostCreate(Bundle savedInstanceState){
    super.onPostCreate(savedInstanceState);
    getDelegate().onPostCreate(savedInstanceState);
  }

  public ActionBar getSupportActionBar(){
    return getDelegate().getSupportActionBar();
  }

  public void setSupportActionBar(@Nullable Toolbar toolbar){
    getDelegate().setSupportActionBar(toolbar);
  }

  @Override
  public MenuInflater getMenuInflater(){
    return getDelegate().getMenuInflater();
  }

  @Override
  public void setContentView(@LayoutRes int layoutResID){
    getDelegate().setContentView(layoutResID);
  }

  @Override
  public void setContentView(View view){
    getDelegate().setContentView(view);
  }

  @Override
  public void setContentView(View view, ViewGroup.LayoutParams params){
    getDelegate().setContentView(view, params);
  }

  @Override
  public void addContentView(View view, ViewGroup.LayoutParams params){
    getDelegate().addContentView(view, params);
  }

  @Override
  protected void onPostResume(){
    super.onPostResume();
    getDelegate().onPostResume();
  }

  @Override
  protected void onTitleChanged(CharSequence title, int color){
    super.onTitleChanged(title, color);
    getDelegate().setTitle(title);
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig){
    super.onConfigurationChanged(newConfig);
    getDelegate().onConfigurationChanged(newConfig);
  }

  @Override
  protected void onStop(){
    super.onStop();
    getDelegate().onStop();
  }

  @Override
  protected void onDestroy(){
    super.onDestroy();
    getDelegate().onDestroy();
  }

  public void invalidateOptionsMenu(){
    getDelegate().invalidateOptionsMenu();
  }

  private AppCompatDelegate getDelegate(){
    if(mDelegate == null){
      mDelegate = AppCompatDelegate.create(this, null);
    }
    return mDelegate;
  }
}
