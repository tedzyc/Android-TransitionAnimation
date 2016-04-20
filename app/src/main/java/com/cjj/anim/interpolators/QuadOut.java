package com.cjj.anim.interpolators;

import android.animation.TimeInterpolator;

public class QuadOut implements TimeInterpolator {
    public float getInterpolation(float paramFloat) {
        return -paramFloat * (paramFloat - 2.0F);
    }
}

