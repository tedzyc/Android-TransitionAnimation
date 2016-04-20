package com.cjj.anim.interpolators;

import android.animation.TimeInterpolator;

public class BackInOut implements TimeInterpolator {
    protected float param_s = 1.70158F;

    public BackInOut amount(float paramFloat) {
        this.param_s = paramFloat;
        return this;
    }

    public float getInterpolation(float paramFloat) {
        float f1 = this.param_s;
        float f2 = paramFloat * 2.0F;
        if (f2 < 1.0F) {
            float f6 = f2 * f2;
            float f7 = f1 * 1.525F;
            return 0.5F * (f6 * (f2 * (1.0F + f7) - f7));
        }
        float f3 = f2 - 2.0F;
        float f4 = f3 * f3;
        float f5 = f1 * 1.525F;
        return 0.5F * (2.0F + f4 * (f5 + f3 * (1.0F + f5)));
    }
}
