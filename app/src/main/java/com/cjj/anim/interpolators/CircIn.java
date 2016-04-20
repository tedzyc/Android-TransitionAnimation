package com.cjj.anim.interpolators;

import android.animation.TimeInterpolator;

public class CircIn implements TimeInterpolator {
    public float getInterpolation(float paramFloat) {
        return (float) Math.sqrt(1.0F - paramFloat * paramFloat);
    }
}

