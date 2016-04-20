package com.cjj.anim.interpolators;

import android.animation.TimeInterpolator;

public class SineOut implements TimeInterpolator {
    public float getInterpolation(float paramFloat) {
        return (float) Math.sin(1.5707963267948966D * paramFloat);
    }
}

