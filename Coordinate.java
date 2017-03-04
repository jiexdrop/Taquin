package com.jnvarzea.taquin;

import java.util.Map;
import java.util.Random;

/**
 * Created by Jorge Nogueira on 28/02/17.
 */

public class Coordinate {
    float x, y, realX, realY;

    public Coordinate(float realX, float realY, float width) {
        this.realX = realX;
        this.realY = realY;

        x = Math.round(realX/width);
        y = Math.round(realY/width);
    }

    public Coordinate NextMove(){
        Random random = new Random();
        switch (random.nextInt(4)){
            case 0:
                x += 1f;
                break;
            case 1:
                x -= 1f;
                break;
            case 2:
                y += 1f;
                break;
            default:
                y -= 1f;
                break;
        }
        return this;
    }

}
