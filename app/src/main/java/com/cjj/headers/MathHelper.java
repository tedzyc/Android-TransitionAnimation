package com.cjj.headers;

import java.util.Random;

public class MathHelper {
    public static Random rand = new Random();

    public static float randomRange(float paramFloat1, float paramFloat2) {
        return rand.nextInt(1 + ((int) paramFloat2 - (int) paramFloat1)) + (int) paramFloat1;
    }
}