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

package hist.jho.com.br.projeto_historico.adapter.wrapping.layoutmanager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jhoanesfreitas on 21/04/16.
 */
public class WrappingLinearLayoutManager extends LinearLayoutManager{

  private Context mContext;
  private int[] mMeasuredDimension = new int[2];

  public WrappingLinearLayoutManager(Context context){
    super(context);
    this.mContext = context;
    setAutoMeasureEnabled(true);
  }

  public WrappingLinearLayoutManager(Context context, int orientation, boolean reverseLayout){
    super(context, orientation, reverseLayout);
    this.mContext = context;
  }

  public WrappingLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
    super(context, attrs, defStyleAttr, defStyleRes);
    this.mContext = context;
  }

  @Override public boolean canScrollVertically(){
    return super.canScrollVertically();
  }

  @Override public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec){
    super.onMeasure(recycler, state, widthSpec, heightSpec);

    final int widthMode = View.MeasureSpec.getMode(widthSpec);
    final int heightMode = View.MeasureSpec.getMode(heightSpec);

    final int widthSize = View.MeasureSpec.getSize(widthSpec);
    final int heightSize = View.MeasureSpec.getSize(heightSpec);

    int width = 0;
    int height = 0;

    for (int i = 0; i < getItemCount(); i++) {
      if (getOrientation() == HORIZONTAL) {
        measureScrapChild(recycler, i,
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
            heightSpec,
            mMeasuredDimension);

        width = width + mMeasuredDimension[0];
        if (i == 0) {
          height = mMeasuredDimension[1];
        }
      } else {
        measureScrapChild(recycler, i,
            widthSpec,
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
            mMeasuredDimension);

        height = height + mMeasuredDimension[1];
        if (i == 0) {
          width = mMeasuredDimension[0];
        }
      }
    }

    switch (widthMode) {
      case View.MeasureSpec.EXACTLY:
        width = widthSize;
      case View.MeasureSpec.AT_MOST:
      case View.MeasureSpec.UNSPECIFIED:
    }

    switch (heightMode) {
      case View.MeasureSpec.EXACTLY:
        height = heightSize;
      case View.MeasureSpec.AT_MOST:
      case View.MeasureSpec.UNSPECIFIED:
    }

    setMeasuredDimension(width, height);
  }

  private void measureScrapChild(RecyclerView.Recycler recycler, int position, int widthSpec,
                                 int heightSpec, int[] measuredDimension) {

    View view = recycler.getViewForPosition(position);
    if (view.getVisibility() == View.GONE) {
      measuredDimension[0] = 0;
      measuredDimension[1] = 0;
      return;
    }
    // For adding Item Decor Insets to view
    super.measureChildWithMargins(view, 0, 0);
    RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();
    int childWidthSpec = ViewGroup.getChildMeasureSpec(
        widthSpec,
        getPaddingLeft() + getPaddingRight() + getDecoratedLeft(view) + getDecoratedRight(view),
        p.width);
    int childHeightSpec = ViewGroup.getChildMeasureSpec(
        heightSpec,
        getPaddingTop() + getPaddingBottom() + getDecoratedTop(view) + getDecoratedBottom(view),
        p.height);
    view.measure(childWidthSpec, childHeightSpec);

    // Get decorated measurements
    measuredDimension[0] = getDecoratedMeasuredWidth(view) + p.leftMargin + p.rightMargin;
    measuredDimension[1] = getDecoratedMeasuredHeight(view) + p.bottomMargin + p.topMargin;
    recycler.recycleView(view);
  }

}
