package com.cjj.anim.interpolators;

import android.animation.TimeInterpolator;

public class SineIn implements TimeInterpolator {
    public float getInterpolation(float paramFloat) {
        return 1.0F + (float) -Math.cos(1.5707963267948966D * paramFloat);
    }
}

