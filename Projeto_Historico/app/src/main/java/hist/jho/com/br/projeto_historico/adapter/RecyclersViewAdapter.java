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

import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import hist.jho.com.br.projeto_historico.R;
import hist.jho.com.br.projeto_historico.model.ImageViewCard;

/**
 * Created by jhoanesfreitas on 20/04/16.
 */
public class RecyclersViewAdapter extends RecyclerView.Adapter<RecyclersViewAdapter.ImageViewHolder>{

  List<ImageViewCard> imagesView;
  private int mCurrentType;
  private static final int LARGE_GRID_ITEM = -1;
  private static final int SMALL_GRID_ITEM = -2;

  public RecyclersViewAdapter(List<ImageViewCard> imagesView){
    this.imagesView = imagesView;
    //setListViewHeightBasedOnChildren(imagesView);
    Log.d("photo", "photo1");
  }

  @Override public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
    Log.d("photo", "photo2");
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_main, parent, false);
    ImageViewHolder pvh = new ImageViewHolder(v);
    return pvh;
  }

  @Override public void onBindViewHolder(ImageViewHolder holder, int position){
    holder.personPhoto.setImageResource(imagesView.get(position).getPhotoId());
    Log.d("photo", "photo3");
  }

  @Override public int getItemCount(){
    Log.d("photo", "photo4");
    return imagesView.size();
  }

  @Override
  public int getItemViewType (int position) {
    return mCurrentType;
  }

  @Override
  public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);
    Log.d("photo", "photo5");
  }

  public static void setListViewHeightBasedOnChildren(ListView listView) {
    ListAdapter listAdapter = listView.getAdapter();
    if (listAdapter == null) {
      // pre-condition
      return;
    }

    int totalHeight = 0;
    for (int i = 0; i < listAdapter.getCount(); i++) {
      View listItem = listAdapter.getView(i, null, listView);
      listItem.measure(0, 0);
      totalHeight += listItem.getMeasuredHeight();
    }

    ViewGroup.LayoutParams params = listView.getLayoutParams();
    params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
    listView.setLayoutParams(params);
    listView.requestLayout();
  }


  public static class ImageViewHolder extends RecyclerView.ViewHolder {
    CardView cv;
    ImageView personPhoto;

    ImageViewHolder(View itemView) {
      super(itemView);
      cv = (CardView) itemView.findViewById(R.id.card_view_Image);
      cv.setPreventCornerOverlap(false);
      personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
      Log.d("photo", "photo");
    }
  }
}
