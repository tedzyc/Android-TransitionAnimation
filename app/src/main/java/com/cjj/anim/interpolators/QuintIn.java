package com.cjj.anim.interpolators;

import android.animation.TimeInterpolator;

public class QuintIn implements TimeInterpolator {
    public float getInterpolation(float paramFloat) {
        return paramFloat * (paramFloat * (paramFloat * (paramFloat * paramFloat)));
    }
}
