package com.cjj.anim.evaluator;

import android.animation.TypeEvaluator;
import android.graphics.RectF;

public class TRectFEvaluator implements TypeEvaluator<RectF> {
    public RectF evaluate(float paramFloat, RectF paramRectF1, RectF paramRectF2) {
        return new RectF(paramRectF1.left + (int) (paramFloat * (paramRectF2.left - paramRectF1.left)), paramRectF1.top + (int) (paramFloat * (paramRectF2.top - paramRectF1.top)), paramRectF1.right + (int) (paramFloat * (paramRectF2.right - paramRectF1.right)), paramRectF1.bottom + (int) (paramFloat * (paramRectF2.bottom - paramRectF1.bottom)));
    }
}

