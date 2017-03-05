package com.jnvarzea.taquin;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 *
 */

public class ImageAdapter extends PagerAdapter {

    Context mContext;

    ImageAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 8;
    }



    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == ((ImageView) obj);
    }



    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ChooseImageActivity)mContext).toGameActivity();
            }
        });
        imageView.setImageResource(mContext.getResources().getIdentifier("m"+i,"drawable", mContext.getPackageName()));
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((ImageView) obj);
    }
}

