package com.cjj.headers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Renderable {
    public Bitmap bitmap;
    float scaleX = 1.0F;
    float scaleY = 1.0F;
    public float translationX;
    public float translationY;
    public float x;
    public float y;

    public Renderable(Bitmap paramBitmap, float paramFloat1, float paramFloat2) {
        this.x = paramFloat1;
        this.y = paramFloat2;
        this.bitmap = paramBitmap;
    }

    public void destroy() {
        if ((this.bitmap == null) || (this.bitmap.isRecycled()))
            return;
        this.bitmap.recycle();
        this.bitmap = null;
    }

    public void draw(Canvas paramCanvas) {
        paramCanvas.save();
        paramCanvas.drawBitmap(this.bitmap, this.x + this.translationX / 2.0F, this.y + this.translationY, null);
        paramCanvas.restore();
    }

    public void drawStretched(Canvas paramCanvas, float paramFloat) {
        paramCanvas.save();
        paramCanvas.drawBitmap(this.bitmap, null, new RectF(this.x + this.translationX / 2.0F, this.y + this.translationY, paramFloat + (this.x + this.translationX / 2.0F), this.y + this.translationY + this.bitmap.getHeight()), null);
        paramCanvas.restore();
    }

    public float getTranslationX() {
        return this.translationX;
    }

    public float getTranslationY() {
        return this.translationY;
    }

    public void pause() {
    }

    public void resume() {
    }

    public void setScale(float paramFloat1, float paramFloat2) {
    }

    public void setTranslationX(float paramFloat) {
        this.translationX = paramFloat;
    }

    public void setTranslationY(float paramFloat) {
        this.translationY = paramFloat;
    }

    public void setTranslationY(Float paramFloat) {
        this.translationY = paramFloat.floatValue();
    }

    public void setY(float paramFloat) {
        this.y = paramFloat;
    }

    public void update(float paramFloat1, float paramFloat2) {
    }
}