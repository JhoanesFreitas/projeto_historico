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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import hist.jho.com.br.projeto_historico.R;

/**
 * Created by jhoanesfreitas on 19/04/16.
 */
public class SplashActivity extends Activity{

  private boolean splashLoaded = false;

  @Override protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);

    if (!splashLoaded) {
      setContentView(R.layout.splash_activity);
      int secondsDelayed = 1;
      new Handler().postDelayed(new Runnable() {
        public void run() {
          startActivity(new Intent(SplashActivity.this, MainActivity.class));
          finish();
        }
      }, secondsDelayed * 500);

      splashLoaded = true;
    }
    else {
      Intent goToMainActivity = new Intent(SplashActivity.this, MainActivity.class);
      goToMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
      startActivity(goToMainActivity);
      finish();
    }
  }
}
