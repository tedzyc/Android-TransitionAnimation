package com.cjj.headers;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class RenderableBear extends Renderable {
    public static final int FRAME_DELAY = 2500;
    int bitmapIndex = 0;
    Bitmap[] bitmaps;
    long lastFrameChange = System.currentTimeMillis();

    public RenderableBear(Bitmap[] paramArrayOfBitmap, float paramFloat1, float paramFloat2) {
        super(null, paramFloat1, paramFloat2);
        this.bitmaps = paramArrayOfBitmap;
    }

    public void destroy() {
        for (Bitmap localBitmap : this.bitmaps) {
            if ((localBitmap == null) || (localBitmap.isRecycled()))
                continue;
            localBitmap.recycle();
        }
    }

    public void draw(Canvas paramCanvas) {
        paramCanvas.save();
        paramCanvas.drawBitmap(this.bitmaps[this.bitmapIndex], this.x + this.translationX / 2.0F, this.y + this.translationY, null);
        paramCanvas.restore();
    }

    public void update(float paramFloat1, float paramFloat2) {
        super.update(paramFloat1, paramFloat2);
        if (2500L + this.lastFrameChange >= System.currentTimeMillis())
            return;
        this.lastFrameChange = System.currentTimeMillis();
        this.bitmapIndex = (1 + this.bitmapIndex);
        if (this.bitmapIndex != this.bitmaps.length)
            return;
        this.bitmapIndex = 0;
    }
}