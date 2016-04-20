package com.cjj.anim.interpolators;

import android.animation.TimeInterpolator;

public class CircInOut implements TimeInterpolator {
    public float getInterpolation(float paramFloat) {
        float f1 = paramFloat * 2.0F;
        if (f1 < 1.0F) {
            return -0.5F * ((float) Math.sqrt(1.0F - f1 * f1) - 1.0F);
        }
        float f2 = f1 - 2.0F;
        return 0.5F * (1.0F + (float) Math.sqrt(1.0F - f2 * f2));
    }
}


