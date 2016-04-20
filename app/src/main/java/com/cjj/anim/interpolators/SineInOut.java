package com.cjj.anim.interpolators;

import android.animation.TimeInterpolator;

public class SineInOut implements TimeInterpolator {
    public float getInterpolation(float paramFloat) {
        return -0.5F * ((float) Math.cos(3.141592653589793D * paramFloat) - 1.0F);
    }
}
