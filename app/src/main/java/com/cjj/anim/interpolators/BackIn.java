package com.cjj.anim.interpolators;

import android.animation.TimeInterpolator;

public class BackIn implements TimeInterpolator {
    protected float param_s = 1.70158F;

    public BackIn amount(float paramFloat) {
        this.param_s = paramFloat;
        return this;
    }

    public float getInterpolation(float paramFloat) {
        float f = this.param_s;
        return paramFloat * paramFloat * (paramFloat * (1.0F + f) - f);
    }
}
