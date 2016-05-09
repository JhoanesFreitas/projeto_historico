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

package hist.jho.com.br.projeto_historico.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import hist.jho.com.br.projeto_historico.model.ImageModelGallery;

/**
 * Created by jhoanesfreitas on 09/05/16.
 */
public class SectionPageAdapter extends FragmentPagerAdapter{

  ArrayList<ImageModelGallery> data = new ArrayList<>();

  public SectionPageAdapter(FragmentManager fm, ArrayList<ImageModelGallery> data) {
    super(fm);
    this.data = data;
  }

  @Override public Fragment getItem(int position){
    /*return Main2Activity.PlaceholderFragment.newInstance(position, data.get(position).getName(),
        data.get(position).getPhotoId());*/
    return null;
  }

  @Override public int getCount(){
    return 0;
  }
}
