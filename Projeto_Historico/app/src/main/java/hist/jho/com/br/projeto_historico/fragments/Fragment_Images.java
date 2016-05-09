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

package hist.jho.com.br.projeto_historico.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hist.jho.com.br.projeto_historico.R;
import hist.jho.com.br.projeto_historico.activities.GalleryImagesActivity;
import hist.jho.com.br.projeto_historico.activities.MainActivity;
import hist.jho.com.br.projeto_historico.adapter.RecyclersViewAdapter;
import hist.jho.com.br.projeto_historico.interfaces.RecyclerViewOnClickListenerHack;
import hist.jho.com.br.projeto_historico.model.ImageModelGallery;
import hist.jho.com.br.projeto_historico.model.ImageViewCard;

/**
 * Created by jhoanesfreitas on 07/05/16.
 */
public class Fragment_Images extends Fragment implements RecyclerViewOnClickListenerHack{

  private RecyclerView mRecyclerView;
  private List<ImageViewCard> mImageList;
  private Activity mActivity;

  public Activity getmActivity(){
    return mActivity;
  }

  public void setmActivity(Activity mActivity){
    this.mActivity = mActivity;
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

    View view = inflater.inflate(R.layout.fragment_images, container, false);

    mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){

      @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState){
        super.onScrollStateChanged(recyclerView, newState);
      }

      @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy){
        super.onScrolled(recyclerView, dx, dy);

        LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();

        RecyclersViewAdapter adapter = (RecyclersViewAdapter) mRecyclerView.getAdapter();

        if(mImageList.size() == llm.findLastCompletelyVisibleItemPosition() + 1){
          List<ImageViewCard> listAux = ((MainActivity) getActivity()).getSetCarList(10);

          for(int i = 0; i < listAux.size(); i++){
            adapter.addListItem(listAux.get(i), mImageList.size());
          }
        }
      }
    });

    mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), mRecyclerView, this));

    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
    llm.setOrientation(LinearLayoutManager.HORIZONTAL);

    mRecyclerView.setLayoutManager(llm);

    mImageList = ((MainActivity) getActivity()).getSetCarList(8);
    RecyclersViewAdapter adapter = new RecyclersViewAdapter(getActivity(), mImageList);
    mRecyclerView.setAdapter(adapter);

    return view;//super.onCreateView(inflater, container, savedInstanceState);
  }


  @Override public void onClickListener(View view, int position){
    //Toast.makeText(getActivity(), "onClickListener(): " + position, Toast.LENGTH_SHORT).show();
    //getActivity().startActivity(getActivity().getIntent(), GalleryImagesActivity);
    getActivity().startActivity(new Intent(getActivity(), GalleryImagesActivity.class));
  }

  @Override public void onLongPressClickListener(View view, int position){

  }

  private static class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener{
    private Context mContext;
    private GestureDetector mGestureDetector;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public RecyclerViewTouchListener(Context c, final RecyclerView rv, RecyclerViewOnClickListenerHack rvoclh){
      mContext = c;
      mRecyclerViewOnClickListenerHack = rvoclh;

      mGestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener(){
        @Override
        public void onLongPress(MotionEvent e){
          super.onLongPress(e);

          View cv = rv.findChildViewUnder(e.getX(), e.getY());

          if(cv != null && mRecyclerViewOnClickListenerHack != null){
            mRecyclerViewOnClickListenerHack.onLongPressClickListener(cv,
                rv.getChildPosition(cv));
          }
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e){
          View cv = rv.findChildViewUnder(e.getX(), e.getY());

          if(cv != null && mRecyclerViewOnClickListenerHack != null){
            mRecyclerViewOnClickListenerHack.onClickListener(cv,
                rv.getChildPosition(cv));
          }

          return (true);
        }
      });
    }

    @Override public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e){
      mGestureDetector.onTouchEvent(e);
      return false;
    }

    @Override public void onTouchEvent(RecyclerView rv, MotionEvent e){

    }

    @Override public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept){

    }
  }
}
