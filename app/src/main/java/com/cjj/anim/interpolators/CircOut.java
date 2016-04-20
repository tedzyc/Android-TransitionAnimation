package com.cjj.anim.interpolators;

import android.animation.TimeInterpolator;

public class CircOut implements TimeInterpolator {
    public float getInterpolation(float paramFloat) {
        return 1.0F + (float) Math.sqrt(1.0F - paramFloat * (paramFloat - 1.0F));
    }
}

