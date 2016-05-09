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

package hist.jho.com.br.projeto_historico.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jhoanesfreitas on 08/05/16.
 */
public class ImageModelGallery implements Parcelable{

  private String name, url;
  private int photoId;

  public ImageModelGallery(){}

  protected ImageModelGallery(Parcel in){
    name = in.readString();
    url = in.readString();
    photoId = in.readInt();
  }

  public static final Creator<ImageModelGallery> CREATOR = new Creator<ImageModelGallery>(){
    @Override
    public ImageModelGallery createFromParcel(Parcel in){
      return new ImageModelGallery(in);
    }

    @Override
    public ImageModelGallery[] newArray(int size){
      return new ImageModelGallery[size];
    }
  };

  public String getName(){
    return name;
  }

  public void setName(String name){
    this.name = name;
  }

  public String getUrl(){
    return url;
  }

  public void setUrl(String url){
    this.url = url;
  }

  public int getPhotoId(){
    return photoId;
  }

  public void setPhotoId(int photoId){
    this.photoId = photoId;
  }

  @Override public int describeContents(){
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags){
    dest.writeString(name);
    dest.writeString(url);
    dest.writeInt(photoId);
  }
}
