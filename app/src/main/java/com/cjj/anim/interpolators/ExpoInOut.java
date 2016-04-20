package com.cjj.anim.interpolators;

import android.animation.TimeInterpolator;

public class ExpoInOut implements TimeInterpolator {
    public float getInterpolation(float paramFloat) {
        if (paramFloat == 0.0F) {
            return 0.0F;
        }
        if (paramFloat == 1.0F) {
            return 1.0F;
        }
        float f = paramFloat * 2.0F;
        if (f < 1.0F) {
            return 0.5F * (float) Math.pow(2.0D, 10.0F * (f - 1.0F));
        }
        return 0.5F * (2.0F + -(float) Math.pow(2.0D, -10.0F * (f - 1.0F)));
    }
}


