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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import hist.jho.com.br.projeto_historico.Constants;
import hist.jho.com.br.projeto_historico.R;

/**
 * Created by jhoanesfreitas on 20/04/16.
 */
public class TextViewActivity extends AppCompatActivity{

  /*@Bind(R.id.tvText)*/
  private TextView mTextView;
  //@Bind(R.id.fab) FloatingActionButton fab;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.text_activity);
    setTitle(getIntent().getStringExtra(Constants.TEXT_ACTIVITY_TITLE));
    mTextView = (TextView) findViewById(R.id.tvText);
    mTextView.setText(getIntent().getStringExtra(Constants.TEXT_ACTIVITY));

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    if(fab != null){
      fab.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
          Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
              .setAction("Action", null).show();
        }
      });
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu){
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main_text_activity, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item){
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if(id == R.id.action_download){
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

}
