package com.cjj.headers;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.animation.LinearInterpolator;

public class PathBitmapMesh {
    private static final int HORIZONTAL_SLICES = 6;
    private static final int VERTICAL_SLICES = 1;
    Bitmap bitmap;
    float[] coords = new float[2];
    float[] coords2 = new float[2];
    float[] drawingVerts = new float[2 * this.totaolSlicesCount];
    int horizontalSlices;
    Paint paint = new Paint();
    ValueAnimator pathOffset;
    float pathOffsetPercent = 1.0F;
    final float[] staticVerts = new float[2 * this.totaolSlicesCount];
    private int totaolSlicesCount = 14;
    int verticalSlices;

    public PathBitmapMesh(int paramInt1, int paramInt2, Bitmap paramBitmap, int paramInt3) {
        this.horizontalSlices = paramInt1;
        this.verticalSlices = paramInt2;
        this.bitmap = paramBitmap;
        createVerts();
        startWaveAnim(paramBitmap, paramInt1, paramInt3);
    }

    private void createVerts() {
        float f1 = this.bitmap.getWidth();
        float f2 = this.bitmap.getHeight();
        int i = 0;
        for (int j = 0; j <= 1; ++j) {
            float f3 = f2 * j / 1.0F;
            for (int k = 0; k <= 6; ++k) {
                float f4 = f1 * k / 6.0F;
                setXY(this.drawingVerts, i, f4, f3);
                setXY(this.staticVerts, i, f4, f3);
                ++i;
            }
        }
    }

    private void startWaveAnim(Bitmap paramBitmap, float paramFloat, int paramInt) {
        float[] arrayOfFloat = new float[2];
        arrayOfFloat[0] = 0.0F;
        arrayOfFloat[1] = (2.0F * (paramBitmap.getWidth() / paramFloat) / paramBitmap.getWidth());
        this.pathOffset = ValueAnimator.ofFloat(arrayOfFloat).setDuration(paramInt);
        this.pathOffset.setRepeatCount(-1);
        this.pathOffset.setRepeatMode(1);
        this.pathOffset.addUpdateListener(new jjUpdateListtener());
        this.pathOffset.setInterpolator(new LinearInterpolator());
        this.pathOffset.start();
    }

    public void destroy() {
        this.pathOffset.cancel();
        if ((this.bitmap == null) || (this.bitmap.isRecycled()))
            return;
        this.bitmap.recycle();
        this.bitmap = null;
    }

    public void draw(Canvas paramCanvas) {
        paramCanvas.drawBitmapMesh(this.bitmap, 6, 1, this.drawingVerts, 0, null, 0, this.paint);
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public void matchVertsToPath(Path paramPath, float paramFloat1, float paramFloat2) {
        PathMeasure localPathMeasure = new PathMeasure(paramPath, false);
        int i = 0;
        if (i >= this.staticVerts.length / 2)
            label14:return;
        float f1 = this.staticVerts[(1 + i * 2)];
        float f2 = this.staticVerts[(i * 2)];
        float f3 = (1.0E-006F + f2) / this.bitmap.getWidth();
        float f4 = (1.0E-006F + f2) / (paramFloat2 + this.bitmap.getWidth()) + this.pathOffsetPercent;
        localPathMeasure.getPosTan(localPathMeasure.getLength() * (1.0F - f3), this.coords, null);
        localPathMeasure.getPosTan(localPathMeasure.getLength() * (1.0F - f4), this.coords2, null);
        if (f1 == 0.0F)
            setXY(this.drawingVerts, i, this.coords[0], this.coords2[1]);
        while (true) {
            ++i;
//            break label14: //// TODO: 2016/4/19  
            setXY(this.drawingVerts, i, this.coords[0], paramFloat1);
        }
    }

    public void pause() {
        this.pathOffset.pause();
    }

    public void resume() {
        this.pathOffset.resume();
    }

    public void setAlpha(int paramInt) {
        this.paint.setAlpha(paramInt);
    }

    public void setXY(float[] paramArrayOfFloat, int paramInt, float paramFloat1, float paramFloat2) {
        paramArrayOfFloat[(0 + paramInt * 2)] = paramFloat1;
        paramArrayOfFloat[(1 + paramInt * 2)] = paramFloat2;
    }

    class jjUpdateListtener implements ValueAnimator.AnimatorUpdateListener {
        public void onAnimationUpdate(ValueAnimator paramValueAnimator) {
            pathOffsetPercent = ((Float) paramValueAnimator.getAnimatedValue()).floatValue();
        }
    }
}