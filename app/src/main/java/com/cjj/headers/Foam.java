package com.cjj.headers;

import android.graphics.Bitmap;
import android.graphics.Path;
import android.graphics.PathMeasure;

public class Foam extends PathBitmapMesh {
    float[] easedFoamCoords;
    float[] foamCoords;
    float maxHeight;
    float minHeight;
    private float verticalOffset;

    public Foam(int paramInt1, int paramInt2, Bitmap paramBitmap, float paramFloat1, float paramFloat2, int paramInt3) {
        super(paramInt1, paramInt2, paramBitmap, paramInt3);
        setupFoam(paramInt1);
        this.minHeight = paramFloat1;
        this.maxHeight = paramFloat2;
    }

    private void setupFoam(int paramInt) {
        this.foamCoords = new float[paramInt];
        this.easedFoamCoords = new float[paramInt];
        for (int i = 0; i < paramInt; ++i) {
            this.foamCoords[i] = 0.0F;
            this.easedFoamCoords[i] = 0.0F;
        }
    }

    void calcWave() {
        for (int i = 0; i < this.foamCoords.length; ++i)
            this.foamCoords[i] = MathHelper.randomRange(this.minHeight, this.maxHeight);
    }

    public void matchVertsToPath(Path paramPath, float paramFloat) {
        PathMeasure localPathMeasure = new PathMeasure(paramPath, false);
        int i = 0;
        int j = 0;
        if (j >= this.staticVerts.length / 2)
            label16:return;
        float f1 = this.staticVerts[(1 + j * 2)];
        float f2 = this.staticVerts[(j * 2)];
        float f3 = (1.0E-006F + f2) / this.bitmap.getWidth();
        float f4 = (1.0E-006F + f2) / (paramFloat + this.bitmap.getWidth()) + this.pathOffsetPercent;
        localPathMeasure.getPosTan(localPathMeasure.getLength() * (1.0F - f3), this.coords, null);
        localPathMeasure.getPosTan(localPathMeasure.getLength() * (1.0F - f4), this.coords2, null);
        if (f1 == 0.0F)
            setXY(this.drawingVerts, j, this.coords[0], this.coords2[1] + this.verticalOffset);
        while (true) {
            ++j;
//            break
//                    label16: //todo cjj
            float f5 = Math.max(this.coords2[1], this.coords2[1] + this.easedFoamCoords[Math.min(-1 + this.easedFoamCoords.length, i)]);
            setXY(this.drawingVerts, j, this.coords[0], f5 + this.verticalOffset);
            ++i;
        }
    }

    public void setVerticalOffset(float paramFloat) {
        this.verticalOffset = paramFloat;
    }

    void update(float paramFloat) {
        for (int i = 0; i < this.foamCoords.length; ++i) {
            float[] arrayOfFloat = this.easedFoamCoords;
            arrayOfFloat[i] += paramFloat * (this.foamCoords[i] - this.easedFoamCoords[i]);
        }
    }
}