package com.cjj.anim.interpolators;

import android.animation.TimeInterpolator;

public class QuadInOut implements TimeInterpolator {
    public float getInterpolation(float paramFloat) {
        float f1 = paramFloat * 2.0F;
        if (f1 < 1.0F) {
            return f1 * (0.5F * f1);
        }
        float f2 = f1 - 1.0F;
        return -0.5F * (f2 * (f2 - 2.0F) - 1.0F);
    }
}
