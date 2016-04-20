package com.cjj.anim.interpolators;

import android.animation.TimeInterpolator;

public class ExpoIn implements TimeInterpolator {
    public float getInterpolation(float paramFloat) {
        if (paramFloat == 0.0F) {
            return 0.0F;
        }
        return (float) Math.pow(2.0D, 10.0F * (paramFloat - 1.0F));
    }
}

