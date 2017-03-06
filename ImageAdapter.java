package com.jnvarzea.taquin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 *
 */

public class ImageAdapter extends PagerAdapter {
    ArrayList<ImageView> myImages = new ArrayList<>();
    Context mContext;
    int NB_IMAGES =8;

    ImageAdapter(Context context) {
        this.mContext = context;
        GenerateImages();
    }

    @Override
    public int getCount() {
        return NB_IMAGES;
    }


    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == ((ImageView) obj);
    }

    public void GenerateImages(){
        for(int i = 0;i<8;i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ChooseImageActivity) mContext).toGameActivity();
                }
            });
            imageView.setImageResource(mContext.getResources().getIdentifier("m" + i, "drawable", mContext.getPackageName()));
            myImages.add(imageView);
        }
    }

    public void AddImage(ImageView imageView){
        myImages.add(imageView);
        NB_IMAGES++;
    }

    public Bitmap getBitmapAt(int position){
        return ((BitmapDrawable) myImages.get(position).getDrawable()).getBitmap();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        container.addView(myImages.get(i), 0);
        return myImages.get(i);
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((ImageView) obj);
    }
}

