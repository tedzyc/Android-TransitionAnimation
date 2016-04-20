package com.cjj.anim.interpolators;

import android.animation.TimeInterpolator;

public class ExpoOut implements TimeInterpolator {
    public float getInterpolation(float paramFloat) {
        if (paramFloat == 1.0F) {
            return 1.0F;
        }
        return 1.0F + -(float) Math.pow(2.0D, -10.0F * paramFloat);
    }
}
