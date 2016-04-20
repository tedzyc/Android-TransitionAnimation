package com.cjj.headers;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.cjj.headers.Renderable;


public class AuraDrawable extends Renderable {
    private Drawable drawable;
    long lastflicker;

    public AuraDrawable(Drawable paramDrawable, Rect paramRect) {
        super(null, 0.0F, 0.0F);
        paramDrawable.setBounds(paramRect);
        this.drawable = paramDrawable;
        this.lastflicker = System.currentTimeMillis();
    }

    public void draw(Canvas paramCanvas) {
        this.drawable.draw(paramCanvas);
    }

    public void update(float paramFloat1, float paramFloat2) {
        if (50L + this.lastflicker >= System.currentTimeMillis())
            return;
        this.drawable.setAlpha((int) (255.0F * (30.0F + MathHelper.rand.nextInt(25)) / 100.0F));
        this.lastflicker = System.currentTimeMillis();
    }
}