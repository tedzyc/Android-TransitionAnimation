package com.cjj.headers;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Xfermode;


public class NoiseEffect extends Renderable {
    private static final Xfermode[] sModes;
    private int grainFPS;
    long lastGrainOffset;
    Matrix matrix;
    private Paint paint = new Paint();
    float scale;
    BitmapShader shader;

    static {
        Xfermode[] arrayOfXfermode = new Xfermode[16];
        arrayOfXfermode[0] = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        arrayOfXfermode[1] = new PorterDuffXfermode(PorterDuff.Mode.SRC);
        arrayOfXfermode[2] = new PorterDuffXfermode(PorterDuff.Mode.DST);
        arrayOfXfermode[3] = new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER);
        arrayOfXfermode[4] = new PorterDuffXfermode(PorterDuff.Mode.DST_OVER);
        arrayOfXfermode[5] = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        arrayOfXfermode[6] = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        arrayOfXfermode[7] = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
        arrayOfXfermode[8] = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        arrayOfXfermode[9] = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);
        arrayOfXfermode[10] = new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP);
        arrayOfXfermode[11] = new PorterDuffXfermode(PorterDuff.Mode.XOR);
        arrayOfXfermode[12] = new PorterDuffXfermode(PorterDuff.Mode.DARKEN);
        arrayOfXfermode[13] = new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN);
        arrayOfXfermode[14] = new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY);
        arrayOfXfermode[15] = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);
        sModes = arrayOfXfermode;
    }

    public NoiseEffect(Bitmap paramBitmap, int paramInt, float paramFloat) {
        super(paramBitmap, 0.0F, 0.0F);
        this.shader = new BitmapShader(paramBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        this.matrix = new Matrix();
        this.shader.setLocalMatrix(this.matrix);
        this.paint.setShader(this.shader);
        this.paint.setAlpha(144);
        this.paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
        this.lastGrainOffset = System.currentTimeMillis();
        this.grainFPS = paramInt;
        this.scale = paramFloat;
    }

    public void draw(Canvas paramCanvas) {
        paramCanvas.drawPaint(this.paint);
    }

    public void setNoiseIntensity(float paramFloat) {
        this.paint.setAlpha((int) (255.0F * paramFloat));
    }

    public void update(float paramFloat1, float paramFloat2) {
        if (this.lastGrainOffset + this.grainFPS >= System.currentTimeMillis())
            return;
        this.matrix.reset();
        this.matrix.setScale(this.scale, this.scale);
        this.matrix.postTranslate(MathHelper.randomRange(10.0F * -this.bitmap.getWidth(), 10.0F * this.bitmap.getWidth()), MathHelper.randomRange(10.0F * -this.bitmap.getHeight(), 10.0F * this.bitmap.getHeight()));
        this.shader.setLocalMatrix(this.matrix);
        this.lastGrainOffset = System.currentTimeMillis();
    }
}