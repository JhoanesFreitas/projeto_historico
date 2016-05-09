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
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import hist.jho.com.br.projeto_historico.R;
import hist.jho.com.br.projeto_historico.adapter.ImageGalleryAdapter;
import hist.jho.com.br.projeto_historico.model.ImageModelGallery;

/**
 * Created by jhoanesfreitas on 08/05/16.
 */
public class GalleryImagesActivity extends AppCompatActivity{

  private RecyclerView mRecyclerView;
  ArrayList<ImageModelGallery> data = new ArrayList<>();
  ImageGalleryAdapter mAdapter;

  public static String IMGS[] = {

  };

  public static int IMGSID[] = {
      R.drawable.ic_camara,
      R.drawable.ic_camara,
      R.drawable.ic_menu_gallery
  };

  @Override public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.image_gallery_layout);

    /*for(int i = 0; i < IMGS.length; i++){
      //Adding images & title to POJO class and storing in Array (our data)
      ImageModelGallery imageModel = new ImageModelGallery();
      imageModel.setName("Image " + i);
      imageModel.setUrl(IMGS[i]);
      data.add(imageModel);
    }*/

    for(int i = 0; i < IMGSID.length; i++){
      ImageModelGallery imageModel = new ImageModelGallery();
      imageModel.setName("Image " + i);
      imageModel.setPhotoId(IMGSID[i]);
      data.add(imageModel);
    }

    mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
    mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    mRecyclerView.setHasFixedSize(true); // Helps improve performance
    mAdapter = new ImageGalleryAdapter(GalleryImagesActivity.this, data);
    mRecyclerView.setAdapter(mAdapter);
  }
}
