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

package hist.jho.com.br.projeto_historico.ImageActivity;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.AttributeSet;
import android.widget.ImageView;

import hist.jho.com.br.projeto_historico.R;

/**
 * Created by jhoanesfreitas on 17/04/16.
 */
public class ImageViewToolbar extends ImageView{

  private Context mContext;
  private CollapsingToolbarLayout collapsingToolbarLayout;

  public ImageViewToolbar(Context context){
    super(context);
    mContext = context;
  }

  public ImageViewToolbar(Context context, AttributeSet attributeSet){
    super(context, attributeSet);
    mContext = context;
  }

  public ImageViewToolbar(Context context, AttributeSet attributeSet, int defineStyleAttr){
    super(context, attributeSet, defineStyleAttr);
    mContext = context;
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int width = getMeasuredWidth();
    setMeasuredDimension(width, width);
  }
}