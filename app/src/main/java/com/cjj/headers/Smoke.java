package com.cjj.headers;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

public class Smoke extends Renderable {
    private static final int HORIZONTAL_SLICES = 1;
    private static final int TOTAL_SLICES_COUNT = 162;
    private static final int VERTICAL_SLICES = 80;
    public static final float WIND_SENSITIVITY = 7.0F;
    float[] coords = new float[2];
    float[] coords2 = new float[2];
    float density;
    private final float[] drawingVerts = new float[324];
    float height;
    int numberOfTurns;
    Paint paint = new Paint();
    float pathPointOffset = 1.0F;
    ValueAnimator pathPointOffsetAnim;
    DecelerateInterpolator smokeExponentionWindStuff = new DecelerateInterpolator(1.0F);
    Path smokePath = new Path();
    private final float[] staticVerts = new float[324];
    float width;

    public Smoke(Bitmap paramBitmap, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt, float paramFloat5) {
        super(paramBitmap, paramFloat1, paramFloat2);
        this.height = paramFloat3;
        this.width = paramFloat4;
        this.numberOfTurns = paramInt;
        this.paint.setStyle(Paint.Style.STROKE);
        this.density = paramFloat5;
        createVerts();
        createPath();
        float[] arrayOfFloat = new float[2];
        arrayOfFloat[0] = 0.0F;
        arrayOfFloat[1] = (2.0F * (paramBitmap.getHeight() / paramInt) / paramBitmap.getHeight());
        this.pathPointOffsetAnim = ValueAnimator.ofFloat(arrayOfFloat).setDuration(1500L);
        this.pathPointOffsetAnim.setRepeatCount(-1);
        this.pathPointOffsetAnim.setRepeatMode(1);
//        this.pathPointOffsetAnim.addUpdateListener(new Smoke .1 (this)); //// TODO: 2016/4/19
        this.pathPointOffsetAnim.setInterpolator(new LinearInterpolator());
        this.pathPointOffsetAnim.start();
        createPath();
    }

    private void createPath() {
        this.smokePath.reset();
        this.smokePath.moveTo(this.x, this.y);
        int i = (int) (this.height / this.numberOfTurns);
        int j = 1;
        int k = 0;
        if (k >= this.numberOfTurns)
            label38:return;
        if (j != 0) {
            this.smokePath.cubicTo(this.x, this.y - i * k, this.x + this.width, this.y - i * k - i / 2, this.x, this.y - i * k - i);
//            label109:
//            if (j != 0)
//                break label183;
        }
        for (j = 1; ; j = 0) {
            ++k;
//            break label38:
            this.smokePath.cubicTo(this.x, this.y - i * k, this.x - this.width, this.y - i * k - i / 2, this.x, this.y - i * k - i);
//            label183:
//            break label109:
        }
    }

    private void createVerts() {
        float f1 = this.bitmap.getWidth();
        float f2 = this.bitmap.getHeight();
        int i = 0;
        for (int j = 0; j <= 80; ++j) {
            float f3 = f2 * j / 80.0F;
            for (int k = 0; k <= 1; ++k) {
                float f4 = f1 * k / 1.0F;
                setXY(this.drawingVerts, i, f4, f3);
                setXY(this.staticVerts, i, f4, f3);
                ++i;
            }
        }
    }

    private void matchVertsToPath(float paramFloat) {
        PathMeasure localPathMeasure = new PathMeasure(this.smokePath, false);
        int i = 0;
        if (i >= this.staticVerts.length / 2)
            label15:return;
        float f1 = this.staticVerts[(1 + i * 2)];
        float f2 = this.staticVerts[(i * 2)];
        float f3 = (1.0E-006F + f1) / this.bitmap.getHeight();
        float f4 = (1.0E-006F + f1) / (this.bitmap.getHeight() + 4.0F * (this.bitmap.getHeight() / this.numberOfTurns)) + this.pathPointOffset;
        localPathMeasure.getPosTan(localPathMeasure.getLength() * (1.0F - f3), this.coords, null);
        localPathMeasure.getPosTan(localPathMeasure.getLength() * (1.0F - f4), this.coords2, null);
        if (f2 == 0.0F) {
            float f7 = this.coords2[0] - this.bitmap.getWidth() / 2;
            float f8 = f7 - f3 * (f7 - this.x) + (paramFloat / 3.0F * this.density + 7.0F * paramFloat * (1.0F - this.smokeExponentionWindStuff.getInterpolation(f3)));
            setXY(this.drawingVerts, i, f8, this.coords[1]);
        }
        while (true) {
            ++i;
//            break label15:
            float f5 = this.coords2[0] + this.bitmap.getWidth() / 2;
            float f6 = f5 - f3 * (f5 - this.x) + (paramFloat / 3.0F * this.density + 7.0F * paramFloat * (1.0F - this.smokeExponentionWindStuff.getInterpolation(f3)));
            setXY(this.drawingVerts, i, f6, this.coords[1]);
        }
    }

    public void destroy() {
        super.destroy();
        this.pathPointOffsetAnim.cancel();
    }

    public void draw(Canvas paramCanvas) {
        paramCanvas.drawBitmapMesh(this.bitmap, 1, 80, this.drawingVerts, 0, null, 0, this.paint);
    }

    public void pause() {
        super.pause();
        this.pathPointOffsetAnim.pause();
    }

    public void resume() {
        super.resume();
        this.pathPointOffsetAnim.resume();
    }

    public void setXY(float[] paramArrayOfFloat, int paramInt, float paramFloat1, float paramFloat2) {
        paramArrayOfFloat[(0 + paramInt * 2)] = paramFloat1;
        paramArrayOfFloat[(1 + paramInt * 2)] = paramFloat2;
    }

    public void setY(float paramFloat) {
        this.y = paramFloat;
        createPath();
    }

    public void update(float paramFloat1, float paramFloat2) {
        matchVertsToPath(paramFloat2);
    }
}