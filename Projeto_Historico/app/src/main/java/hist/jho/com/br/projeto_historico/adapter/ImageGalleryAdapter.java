package hist.jho.com.br.projeto_historico.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.preference.PreferenceActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import hist.jho.com.br.projeto_historico.R;
import hist.jho.com.br.projeto_historico.extras.ImageHelper;
import hist.jho.com.br.projeto_historico.interfaces.RecyclerViewOnClickListenerHack;
import hist.jho.com.br.projeto_historico.model.ImageModelGallery;

/**
 * Created by jhoanesfreitas on 08/05/16.
 */
public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.ImageViewHolderGallery>{

  List<ImageModelGallery> imagesView;
  private Context mContext;
  private List<ImageModelGallery> mList;
  private LayoutInflater mLayoutInflater;
  protected RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;
  private float scale;
  private int width;
  private int height;

  public ImageGalleryAdapter(List<ImageModelGallery> imagesView){
    this.imagesView = imagesView;
  }

  public ImageGalleryAdapter(Context mContext, List<ImageModelGallery> imagesView){
    this.mContext = mContext;
    this.imagesView = imagesView;

    scale = mContext.getResources().getDisplayMetrics().density;
    width = mContext.getResources().getDisplayMetrics().widthPixels - (int)(14 * scale + 0.5f);
    height = (width / 16) * 9;

  }

  @Override public ImageViewHolderGallery onCreateViewHolder(ViewGroup parent, int viewType){
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_gallery, parent, false);//list_item_gallery
    ImageViewHolderGallery imageViewHolder = new ImageViewHolderGallery(view);
    return imageViewHolder;
  }

  @Override public void onBindViewHolder(ImageViewHolderGallery holder, int position){

    Glide.with(mContext).load(imagesView.get(position).getPhotoId())
        .thumbnail(0.5f)
        .crossFade()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(((ImageViewHolderGallery) holder).personPhoto);

    /*try{
      YoYo.with(Techniques.Tada)
          .duration(700)
          .playOn(holder.itemView);
    }
    catch(Exception e){}*/

    /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
      holder.personPhoto.setImageResource(imagesView.get(position).getPhotoId());
    }
    else{
      Bitmap bitmap = BitmapFactory.decodeResource( mContext.getResources(), imagesView.get(position).getPhotoId());
      bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);

      bitmap = ImageHelper.getRoundedCornerBitmap(mContext, bitmap, 4, width, height, false, false, true, true);
      holder.personPhoto.setImageBitmap(bitmap);
    }*/


  }

  @Override public int getItemCount(){
    return imagesView.size();
  }

  public class ImageViewHolderGallery extends RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView personPhoto;

    ImageViewHolderGallery(View itemView){
      super(itemView);
      personPhoto = (ImageView) itemView.findViewById(R.id.item_img); //iv_car
      Log.d("photo", "photo");
    }

    @Override public void onClick(View v){
      if(mRecyclerViewOnClickListenerHack != null){
        mRecyclerViewOnClickListenerHack.onClickListener(v, getLayoutPosition());
      }
    }
  }
}
