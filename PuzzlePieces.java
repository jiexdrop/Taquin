package com.jnvarzea.taquin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * https://developer.android.com/guide/topics/ui/layout/gridview.html
 * Created by Jorge Nogueira on 28/02/17.
 */

public class PuzzlePieces extends BaseAdapter {
    private ArrayList<ImageView> puzzlePiecesViews = new ArrayList<>();
    private Bitmap image;
    private Context context;
    private int size;

    @Override
    public int getCount() {
        return puzzlePiecesViews.size();
    }

    @Override
    public ImageView getItem(int position) {
        return puzzlePiecesViews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return puzzlePiecesViews.get(position);
    }

    public boolean NearMe(Coordinate a, Coordinate b) {
        //System.out.println(a.x + " " + a.y + " - " + b.x + " " + b.y );
        if (a.x + 1 == b.x && a.y == b.y) {
            return true;
        }
        if (a.x - 1 == b.x && a.y == b.y) {
            return true;
        }
        if (a.y + 1 == b.y && a.x == b.x) {
            return true;
        }
        if (a.y - 1 == b.y && a.x == b.x) {
            return true;
        }
        return false;
    }

    public PuzzlePieces(Context context, String name, int size) {
        this.context = context;
        this.size = size;
        image = BitmapFactory.decodeResource(context.getResources(),
                context.getResources().getIdentifier(name, "drawable", context.getPackageName()));
        GeneratePuzzlePieces();

    }

    public void Shuffle() {
        puzzlePiecesViews.get(size * size - 1).setAlpha(0f);
    }

    private void GeneratePuzzlePieces() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Bitmap toAdd = Bitmap.createBitmap(image, j * (image.getWidth() / size),
                        i * (image.getWidth() / size), (image.getWidth() / size),
                        (image.getWidth() / size));

                ImageView imageView = new ImageView(context);
                imageView.setAdjustViewBounds(true);
                imageView.setImageBitmap(toAdd);
                imageView.setOnTouchListener(new OnTouchPuzzleListener(this));

                puzzlePiecesViews.add(imageView);
            }
        }

    }

}
