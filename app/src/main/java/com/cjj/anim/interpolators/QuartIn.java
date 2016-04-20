package com.cjj.anim.interpolators;

import android.animation.TimeInterpolator;

public class QuartIn implements TimeInterpolator {
    public float getInterpolation(float paramFloat) {
        return paramFloat * (paramFloat * (paramFloat * paramFloat));
    }
}

