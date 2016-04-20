package com.cjj.anim.interpolators;

import android.animation.TimeInterpolator;

public class ElasticInOut implements TimeInterpolator {
    protected float param_a;
    protected float param_p;
    protected boolean setA = false;
    protected boolean setP = false;

    public ElasticInOut a(float paramFloat) {
        this.param_a = paramFloat;
        this.setA = true;
        return this;
    }

    public float getInterpolation(float paramFloat) {
        float f1 = this.param_a;
        float f2 = this.param_p;
        if (paramFloat == 0.0F) {
            return 0.0F;
        }
        float f3 = paramFloat * 2.0F;
        if (f3 == 2.0F) {
            return 1.0F;
        }
        if (!this.setP) {
            f2 = 0.45000002F;
        }
        if ((!this.setA) || (f1 < 1.0F)) {
            f1 = 1.0F;
        }
        float f4 = f2 / 4.0F;
        for (; f3 < 1.0F; f4 = f2 / 6.2831855F * (float) Math.asin(1.0F / f1)) {
            float f6 = f3 - 1.0F;
            return -0.5F * (f1 * (float) Math.pow(2.0D, 10.0F * f6) * (float) Math.sin(6.2831855F * (f6 - f4) / f2));
        }
        float f5 = f3 - 1.0F;
        return 1.0F + 0.5F * (f1 * (float) Math.pow(2.0D, -10.0F * f5) * (float) Math.sin(6.2831855F * (f5 - f4) / f2));
    }

    public ElasticInOut p(float paramFloat) {
        this.param_p = paramFloat;
        this.setP = true;
        return this;
    }
}


