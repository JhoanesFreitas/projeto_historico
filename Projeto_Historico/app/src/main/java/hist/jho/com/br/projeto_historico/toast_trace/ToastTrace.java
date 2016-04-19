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

package hist.jho.com.br.projeto_historico.toast_trace;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by jhoanesfreitas on 19/04/16.
 */
public class ToastTrace{

  private Context mContext;

  public ToastTrace(Context context){
    this.mContext = context;
  }

  public void trace(String string){
    toast(string);
  }

  private void toast(String string){
    Toast.makeText(mContext, string, Toast.LENGTH_SHORT).show();
  }

}
