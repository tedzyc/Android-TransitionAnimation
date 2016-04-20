package com.cjj.headers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;


public class BearSceneView extends View {
    public static final int WIND_RANDOMIZE_INTERVAL = 300;
    float HIGHEST_FLAMES_COORD = 0.4F;
    float HIGHEST_SMOKE_COORD = 0.6F;
    float LOWEST_FLAMES_COORD = 0.8F;
    ParticleSystem flames;
    int index = 0;
    long lastWindRandomChange;
    private boolean pause = false;
    private Renderable[] renderables;
    Smoke smoke;
    ParticleSystem sparks;
    private float wind = 10.0F;
    float windRanomizerEased;
    float windRanomizerTarget;

    public BearSceneView(Context paramContext) {
        super(paramContext);
    }

    public BearSceneView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public BearSceneView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    private void addBear(float paramFloat1, float paramFloat2, Bitmap paramBitmap1, Bitmap paramBitmap2) {
        Renderable[] arrayOfRenderable = this.renderables;
        int i = this.index;
        Bitmap[] arrayOfBitmap = new Bitmap[2];
        arrayOfBitmap[0] = paramBitmap1;
        arrayOfBitmap[1] = paramBitmap2;
        arrayOfRenderable[i] = new RenderableBear(arrayOfBitmap, paramFloat1, paramFloat2);
        this.index = (1 + this.index);
    }

    private void addFire(Bitmap paramBitmap1, Bitmap paramBitmap2, float paramFloat1, float paramFloat2) {
        this.renderables[this.index] = new AuraDrawable(getResources().getDrawable(2130837564),
                new Rect((int) (0.44F * getMeasuredWidth()), (int) (0.4F * getMeasuredHeight()), (int) (0.8F * getMeasuredWidth()), (int) (1.1F * getMeasuredHeight())));
        this.index = (1 + this.index);
        this.renderables[this.index] = new AuraDrawable(getResources().getDrawable(2130837565), new Rect((int) (0.5F * getMeasuredWidth()), (int) (0.6F * getMeasuredHeight()), (int) (0.72F * getMeasuredWidth()), (int) (1.0F * getMeasuredHeight())));
        this.index = (1 + this.index);
        float f1 = getResources().getDisplayMetrics().density;
        float f2 = 5.0F * f1;
        this.flames = new ParticleSystem(paramFloat1, paramFloat2, 30, -30.0F * f1, f2);
        this.sparks = new ParticleSystem(paramFloat1, paramFloat2, 600, -30.0F * f1, f2);
        this.renderables[this.index] = this.flames;
        this.flames.setParticleSize((int) (8.0F * f1));
        this.flames.setRandomMovementX(20.0F * f1);
        this.flames.setRandomMovementY(1.5F * f1);
        this.flames.setColors(getResources().getColor(2131427367), getResources().getColor(2131427366));
        this.index = (1 + this.index);
        this.renderables[this.index] = this.sparks;
        this.sparks.setParticleSize((int) (1.0F * f1));
        this.sparks.setRandomMovementX(25.0F * f1);
        this.sparks.setRandomMovementY(2.5F * f1);
        this.sparks.setRandomMovementChangeInterval(900);
        this.sparks.setColors(getResources().getColor(2131427367), getResources().getColor(2131427367));
        this.sparks.setMinYCoord(0.0F);
        this.index = (1 + this.index);
        this.renderables[this.index] = new Renderable(paramBitmap2, paramFloat1 - 2.0F * f2, paramFloat2);
        this.index = (1 + this.index);
        this.smoke = new Smoke(paramBitmap1, paramFloat1, 0.68F * getMeasuredHeight(), 110.0F * f1, 60.0F * f1, 8, f1);
        this.renderables[this.index] = this.smoke;
        this.index = (1 + this.index);
    }

    private void addGrunge(Bitmap paramBitmap) {
        NoiseEffect localNoiseEffect = new NoiseEffect(paramBitmap, 100, 2.5F);
        this.renderables[this.index] = localNoiseEffect;
        localNoiseEffect.setNoiseIntensity(0.22F);
        this.index = (1 + this.index);
    }

    private void addWhiteBear(float paramFloat1, float paramFloat2, Bitmap paramBitmap) {
        this.renderables[this.index] = new Renderable(paramBitmap, paramFloat1, paramFloat2);
        this.index = (1 + this.index);
    }

    private void destroyResources() {
        Renderable[] arrayOfRenderable = this.renderables;
        int i = arrayOfRenderable.length;
        for (int j = 0; j < i; ++j)
            arrayOfRenderable[j].destroy();
    }

    private float getYCoordByPercent(float paramFloat) {
        return paramFloat * getHeight();
    }

    private void init() {
        this.renderables = new Renderable[17];
        Bitmap localBitmap1 = BitmapFactory.decodeResource(getResources(), 2130903051);
        addThree(localBitmap1, 0.18F * getMeasuredWidth(), -0.65F * getMeasuredHeight(), 0.28F, 0.46F);
        addThree(localBitmap1, 0.6F * getMeasuredWidth(), -0.65F * getMeasuredHeight(), 0.33F, 0.46F);
        addThree(localBitmap1, 0.45F * getMeasuredWidth(), -0.45F * getMeasuredHeight(), 0.5F, 0.8F);
        addThree(localBitmap1, 0.13F * getMeasuredWidth(), -0.65F * getMeasuredHeight(), 0.3F, 0.46F);
        addThree(localBitmap1, 0.83F * getMeasuredWidth(), -0.2F * getMeasuredHeight(), 0.5F, 1.0F);
        addThree(localBitmap1, 0.02F * getMeasuredWidth(), -0.1F * getMeasuredHeight(), 0.8F, 1.0F);
        addThree(localBitmap1, 0.18F * getMeasuredWidth(), 0.15F * getMeasuredHeight(), 0.8F, 1.0F);
        addThree(localBitmap1, 0.7F * getMeasuredWidth(), -0.1F * getMeasuredHeight(), 0.8F, 1.0F);
        Bitmap localBitmap2 = BitmapFactory.decodeResource(getResources(), 2130903040);
        Bitmap localBitmap3 = BitmapFactory.decodeResource(getResources(), 2130903041);
        Bitmap localBitmap4 = BitmapFactory.decodeResource(getResources(), 2130903042);
        Bitmap localBitmap5 = BitmapFactory.decodeResource(getResources(), 2130837588);
        Bitmap localBitmap6 = BitmapFactory.decodeResource(getResources(), 2130837582);
        Bitmap localBitmap7 = BitmapFactory.decodeResource(getResources(), 2130837570);
        addFire(localBitmap6, localBitmap5, 0.61F * getMeasuredWidth(), 0.8F * getMeasuredHeight());
        addBear(0.636F * getMeasuredWidth(), 0.59F * getMeasuredHeight(), localBitmap2, localBitmap3);
        addWhiteBear(0.44F * getMeasuredWidth(), 0.66F * getMeasuredHeight(), localBitmap4);
        setLayerType(2, null);
        addGrunge(localBitmap7);
    }

    void addThree(Bitmap paramBitmap, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
        this.renderables[this.index] = new RenderableThree(paramBitmap, paramFloat1, paramFloat2, paramFloat4);
        this.renderables[this.index].setScale(paramFloat3, paramFloat3);
        this.index = (1 + this.index);
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
        float f1 = FrameRateCounter.timeStep();
        this.windRanomizerEased += f1 * (4.0F * (this.windRanomizerTarget - this.windRanomizerEased));
        Renderable[] arrayOfRenderable = this.renderables;
        int i = arrayOfRenderable.length;
        int j = 0;
//        if (j < i) {
//            label45:
//            Renderable localRenderable = arrayOfRenderable[j];
//            localRenderable.draw(paramCanvas);
//            if ((localRenderable instanceof Smoke) || (localRenderable instanceof ParticleSystem))
//                localRenderable.update(f1, this.wind);
//            while (true) {
//                ++j;
//                break label45:
//                localRenderable.update(f1, this.wind + this.windRanomizerEased);
//            }
//        }
        if (300L + this.lastWindRandomChange < System.currentTimeMillis()) {
            this.lastWindRandomChange = System.currentTimeMillis();
            float f2 = Math.max(this.wind / 2.0F, 1.0F);
            this.windRanomizerTarget = (MathHelper.rand.nextInt((int) f2) - f2 / 2.0F);
        }
        if (this.pause)
            return;
        invalidate();
    }

    protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
        if (this.renderables != null)
            return;
        init();
    }

    public void setFlamesHeight(int paramInt) {
        float f1 = getYCoordByPercent(this.LOWEST_FLAMES_COORD - (this.LOWEST_FLAMES_COORD - this.HIGHEST_FLAMES_COORD) * (paramInt / 100.0F));
        this.flames.setMinYCoord(f1);
        float f2 = getYCoordByPercent(this.LOWEST_FLAMES_COORD - (this.LOWEST_FLAMES_COORD - this.HIGHEST_SMOKE_COORD) * (paramInt / 100.0F));
        this.smoke.setY(f2);
    }

    public void setPause(boolean paramBoolean) {
        int i = 0;
        this.pause = paramBoolean;
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

    public void setWind(int paramInt) {
        this.wind = paramInt;
    }
}