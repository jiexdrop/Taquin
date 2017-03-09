package com.jnvarzea.taquin;

import java.util.Map;
import java.util.Random;

/**
 * Coodinate is used in order to have something like a "2D model" to get
 * the columns [i][j] in the x and y
 * RealX and RealY are the values form the ImageViews
 */

public class Coordinate {
    float x, y, realX, realY, width, size;

    public Coordinate(float realX, float realY, float width) {
        this.realX = realX;
        this.realY = realY;
        this.width = width;

        if(width!=0) {
            x = Math.round(realX / width);
            y = Math.round(realY / width);
        }
    }

    public Coordinate(float realX, float realY, float width, int size) {
        this.realX = realX;
        this.realY = realY;
        this.width = width;

        x = Math.round(realX / width);
        y = Math.round(realY / width);

        this.size = size;
    }

    /**
     * In order to shuffle the puzzle at first
     * @return myself at my new position
     */
    public Coordinate NextMove() {
        Random random = new Random();
        switch (random.nextInt(4)) {
            case 0:
                if (x < size)
                    x += 1f;
                break;
            case 1:
                if (x > 0)
                    x -= 1f;
                break;
            case 2:
                if (y < size)
                    y += 1f;
                break;
            default:
                if (y > 0)
                    y -= 1f;
                break;
        }
        return this;
    }

    public boolean Equals(Coordinate coordinate) {
        return this.x == coordinate.x && this.y == coordinate.y;
    }

    /**
     * Get the indexes of the image views in order to know if the puzzle is solved
     * here allows to know if this coordinate is bigger than the other
     * @param coordinate coordinate to test
     * @return true if bigger
     */
    public boolean isBiggerThan(Coordinate coordinate){
        if (this.y * size + this.x > coordinate.y * size + coordinate.x){
            return true;
        }
        return false;
    }
}
