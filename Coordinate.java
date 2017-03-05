package com.jnvarzea.taquin;

import java.util.Map;
import java.util.Random;

/**
 * Created by Jorge Nogueira on 28/02/17.
 */

public class Coordinate {
    float x, y, realX, realY, size;

    public Coordinate(float realX, float realY, float width) {
        this.realX = realX;
        this.realY = realY;

        if(width!=0) {
            x = Math.round(realX / width);
            y = Math.round(realY / width);
        }
    }

    public Coordinate(float realX, float realY, float width, int size) {
        this.realX = realX;
        this.realY = realY;

        x = Math.round(realX / width);
        y = Math.round(realY / width);

        this.size = size;
    }

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

    public boolean isBiggerThan(Coordinate coordinate){
        if ((this.x > coordinate.x && this.y > coordinate.y)
                || (this.x == coordinate.x && this.y == coordinate.y)
                || (this.x > coordinate.x && this.y <= coordinate.y)
                || (this.x <= coordinate.x && this.y > coordinate.y)){
            return true;
        }
        return false;
    }

}
