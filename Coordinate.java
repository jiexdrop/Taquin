package com.jnvarzea.taquin;

import java.util.Map;

/**
 * Created by Jorge Nogueira on 28/02/17.
 */

public class Coordinate {
    float x, y, xBackup, yBackup;

    public Coordinate(float x, float y) {
        this.x = x;
        this.y = y;
        this.xBackup = x;
        this.yBackup = y;
    }

    public Coordinate ToRound(float width){
        xBackup =x;
        yBackup =y;
        x = Math.round(x/width);
        y = Math.round(y/width);

        return this;
    }

    public Coordinate ToBackUp(){
        this.x = xBackup;
        this.y = yBackup;
        return this;
    }
}
