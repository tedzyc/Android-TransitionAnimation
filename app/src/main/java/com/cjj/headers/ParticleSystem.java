package com.cjj.headers;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

public class ParticleSystem extends Renderable {
    public static final int DURATION = 10000;
    ValueAnimator color;
    private int emitInterWall;
    int endColor;
    private float gravityY;
    long lastEmit = System.currentTimeMillis();
    private float minYCoord;
    List<Particle> paricles = new ArrayList();
    Paint particlePaint = new Paint();
    int particleSize = 20;
    private int randomMovementChangeInterval = 2000;
    float randomMovementX = 10.0F;
    private float randomMovementY;
    private float randomXPlacement;
    int startColor;

    public ParticleSystem(float paramFloat1, float paramFloat2, int paramInt, float paramFloat3, float paramFloat4) {
        super(null, paramFloat1, paramFloat2);
        this.particlePaint.setColor(Color.WHITE);//todo cjj
        this.emitInterWall = paramInt;
        this.gravityY = paramFloat3;
        this.randomXPlacement = paramFloat4;
    }

    private void addParticle() {
        this.paricles.add(new Particle(this.x + MathHelper.randomRange(-this.randomXPlacement, this.randomXPlacement), this.y, MathHelper.randomRange(-this.randomMovementX, this.randomMovementX), MathHelper.randomRange(-this.randomMovementY, this.randomMovementY)));
        this.lastEmit = System.currentTimeMillis();
    }

    public void draw(Canvas paramCanvas) {
        for (int i = 0; i < this.paricles.size(); ++i) {
            Particle localParticle = (Particle) this.paricles.get(i);
            setParticlePaintColor(localParticle);
            paramCanvas.drawRect(localParticle.x, localParticle.y, localParticle.x + this.particleSize, localParticle.y + this.particleSize, this.particlePaint);
        }
    }

    public void setColors(int paramInt1, int paramInt2) {
        this.startColor = paramInt1;
        this.endColor = paramInt2;
        ArgbEvaluator localArgbEvaluator = new ArgbEvaluator();
        Object[] arrayOfObject = new Object[3];
        arrayOfObject[0] = Integer.valueOf(paramInt1);
        arrayOfObject[1] = Integer.valueOf(paramInt2);
        arrayOfObject[2] = Integer.valueOf(0);
        this.color = ValueAnimator.ofObject(localArgbEvaluator, arrayOfObject).setDuration(10000L);
    }

    public void setMinYCoord(float paramFloat) {
        this.minYCoord = paramFloat;
    }

    public void setParticlePaintColor(Particle paramParticle) {
        float f = (this.y - paramParticle.y) / (this.y - this.minYCoord);
        this.color.setCurrentPlayTime((long) (10000.0F * f));
        this.particlePaint.setColor(((Integer) this.color.getAnimatedValue()).intValue());
    }

    public void setParticleSize(int paramInt) {
        this.particleSize = paramInt;
    }

    public void setRandomMovementChangeInterval(int paramInt) {
        this.randomMovementChangeInterval = paramInt;
    }

    public void setRandomMovementX(float paramFloat) {
        this.randomMovementX = paramFloat;
    }

    public void setRandomMovementY(float paramFloat) {
        this.randomMovementY = paramFloat;
    }

    public void update(float paramFloat1, float paramFloat2) {
        long l = System.currentTimeMillis();
        if (this.lastEmit + this.emitInterWall < l)
            addParticle();
        for (int i = 0; i < this.paricles.size(); ++i) {
            Particle localParticle = (Particle) this.paricles.get(i);
            localParticle.y += paramFloat1 * this.gravityY;
            localParticle.y += paramFloat1 * localParticle.randomSpeedY;
            localParticle.x += paramFloat1 * localParticle.randomSpeedX;
            localParticle.x += paramFloat2 * paramFloat1;
            if (localParticle.lastRandomizeChange + this.randomMovementChangeInterval < l) {
                localParticle.lastRandomizeChange = System.currentTimeMillis();
                localParticle.setRandomSpeed(MathHelper.randomRange(-this.randomMovementX, this.randomMovementX), MathHelper.randomRange(-this.randomMovementY, this.randomMovementY));
            }
            if (localParticle.y >= this.minYCoord)
                continue;
            this.paricles.remove(i);
            --i;
        }
    }
}