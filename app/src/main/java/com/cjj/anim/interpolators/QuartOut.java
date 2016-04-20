package com.cjj.anim.interpolators;

import android.animation.TimeInterpolator;

public class QuartOut implements TimeInterpolator {
    public float getInterpolation(float paramFloat) {
        float f = paramFloat - 1.0F;
        return -(f * (f * (f * f)) - 1.0F);
    }
}

