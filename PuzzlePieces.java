package com.jnvarzea.taquin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * https://developer.android.com/guide/topics/ui/layout/gridview.html
 * Created by Jorge Nogueira on 28/02/17.
 */

public class PuzzlePieces extends BaseAdapter{
    private ArrayList<Bitmap> puzzlePieces;
    private ArrayList<ImageView> puzzlePiecesViews = new ArrayList<>();
    private Bitmap image;
    private Context context;
    private int size;

    @Override
    public int getCount() {
        return puzzlePieces.size();
    }

    @Override
    public Object getItem(int position) {
        return puzzlePiecesViews.get(position);
    }

    public Object getItem(int x, int y){
        int index = x * size + y;
        return puzzlePiecesViews.get(index+1);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setAdjustViewBounds(true);
        imageView.setImageBitmap(puzzlePieces.get(position));
        imageView.setOnTouchListener(new OnTouchPuzzleListener(puzzlePiecesViews));
        if(puzzlePiecesViews.size()==puzzlePieces.size()) {
            imageView.setAlpha(0f);
        }
        puzzlePiecesViews.add(imageView);
        return imageView;
    }

    public PuzzlePieces(Context context, String name, int size){
        this.context = context;
        this.size = size;
        image = BitmapFactory.decodeResource(context.getResources(),
                context.getResources().getIdentifier(name,"drawable", context.getPackageName()));
        puzzlePieces = genPuzzlePieces(image, size);
    }

    private ArrayList<Bitmap> genPuzzlePieces(Bitmap image, int size){
        ArrayList<Bitmap> results = new ArrayList<>();

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                results.add(Bitmap.createBitmap(image, j * (image.getWidth()/size),
                        i * (image.getWidth()/size), (image.getWidth()/size),
                        (image.getWidth()/size)));
            }
        }

        return results;
    }

}
