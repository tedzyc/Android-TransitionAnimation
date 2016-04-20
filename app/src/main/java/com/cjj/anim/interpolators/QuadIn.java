package com.cjj.anim.interpolators;

import android.animation.TimeInterpolator;

public class QuadIn implements TimeInterpolator {
    public float getInterpolation(float paramFloat) {
        return paramFloat * paramFloat;
    }
}

