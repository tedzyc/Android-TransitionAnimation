package com.cjj.headers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class WaterSceneView extends View {
    private NoiseEffect noise;
    private NoiseEffect noiseScratchEffect;
    private boolean pasuse = false;
    private Renderable[] renderables;
    private Water water;

    public WaterSceneView(Context paramContext) {
        super(paramContext);
    }

    public WaterSceneView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public WaterSceneView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    private void destroyResources() {
        Renderable[] arrayOfRenderable = this.renderables;
        int i = arrayOfRenderable.length;
        for (int j = 0; j < i; ++j)
            arrayOfRenderable[j].destroy();
    }

    private float getXCoordByPercent(float paramFloat) {
        return paramFloat * getWidth();
    }

    private float getYCoordByPercent(float paramFloat) {
        return paramFloat * getHeight();
    }

    private void init() {
        this.renderables = new Renderable[4];
        Bitmap localBitmap1 = BitmapFactory.decodeResource(getResources(), 2130837591);
        Bitmap localBitmap2 = BitmapFactory.decodeResource(getResources(), 2130837569);
        setLayerType(2, null);
        this.water = new Water(localBitmap1, localBitmap2, getYCoordByPercent(0.65F), getYCoordByPercent(1.0F), getXCoordByPercent(1.0F), 6);
        this.renderables[0] = this.water;
        Bitmap localBitmap3 = BitmapFactory.decodeResource(getResources(), 2130837590);
        this.renderables[1] = new Renderable(localBitmap3, getXCoordByPercent(0.5F), getYCoordByPercent(0.35F));
        Bitmap localBitmap4 = BitmapFactory.decodeResource(getResources(), 2130837579);
        Bitmap localBitmap5 = BitmapFactory.decodeResource(getResources(), 2130837578);
        this.noiseScratchEffect = new NoiseEffect(localBitmap4, 100, 2.0F);
        this.renderables[2] = this.noiseScratchEffect;
        this.noise = new NoiseEffect(localBitmap5, 30, 1.0F);
        this.renderables[3] = this.noise;
        setNoiseIntensity(0.5F);
        setWaveHeight(50.0F);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if ((this.renderables != null) || (getWidth() == 0))
            return;
        init();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        destroyResources();
    }

    protected void onDraw(Canvas paramCanvas) {
        super.onDraw(paramCanvas);
        float f = FrameRateCounter.timeStep();
        for (Renderable localRenderable : this.renderables) {
            localRenderable.draw(paramCanvas);
            localRenderable.update(f, 0.0F);
        }
        if (this.pasuse)
            return;
        invalidate();
    }

    protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
        if (this.renderables != null)
            return;
        init();
    }

    public void setNoiseIntensity(float paramFloat) {
        this.noiseScratchEffect.setNoiseIntensity(paramFloat);
        this.noise.setNoiseIntensity(paramFloat);
    }

    public void setPause(boolean paramBoolean) {
        int i = 0;
        this.pasuse = paramBoolean;
        if (!paramBoolean) {
            FrameRateCounter.timeStep();
            invalidate();
            Renderable[] arrayOfRenderable2 = this.renderables;
            int k = arrayOfRenderable2.length;
            while (true) {
                if (i >= k)
                    return;
                arrayOfRenderable2[i].resume();
                ++i;
            }
        }
        Renderable[] arrayOfRenderable1 = this.renderables;
        int j = arrayOfRenderable1.length;
        while (i < j) {
            arrayOfRenderable1[i].pause();
            ++i;
        }
    }

    public void setWaveHeight(float paramFloat) {
        this.water.setWaveHeight(paramFloat);
    }
}