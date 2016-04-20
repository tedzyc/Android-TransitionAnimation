package com.cjj.anim.interpolators;

import android.animation.TimeInterpolator;

public class QuintOut implements TimeInterpolator {
    public float getInterpolation(float paramFloat) {
        float f = paramFloat - 1.0F;
        return 1.0F + f * (f * (f * (f * f)));
    }
}

