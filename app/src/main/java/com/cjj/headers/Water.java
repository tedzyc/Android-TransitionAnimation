package com.cjj.headers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;

import com.cjj.headers.Renderable;

public class Water extends Renderable
{
    public static final int VERTS = 6;
    Paint debugPaint = new Paint();
    private int emitInterWall = 1000;
    Foam[] foams = new Foam[4];
    private float height;
    long lastEmit;
    int numberOfWaves;
    PathBitmapMesh water;
    private Path waterPath = new Path();
    private float waveHeight;
    private float width;

    public Water(Bitmap paramBitmap1, Bitmap paramBitmap2, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt)
    {
        super(paramBitmap1, 0.0F, paramFloat1);
        this.height = paramFloat2;
        this.width = paramFloat3;
        this.numberOfWaves = paramInt;
        this.debugPaint.setColor(Color.RED);//todo cjj
        this.debugPaint.setStyle(Paint.Style.STROKE);
        this.lastEmit = System.currentTimeMillis();
        this.water = new PathBitmapMesh(6, 1, paramBitmap1, 1500);
        this.foams[0] = new Foam(6, 1, paramBitmap2, 0.0F, paramFloat2 / 12.0F, 1500);
        this.foams[1] = new Foam(6, 1, paramBitmap2, -paramFloat2 / 5.0F, paramFloat2 / 5.0F, 1500);
        this.foams[1].setAlpha(100);
        this.foams[2] = new Foam(6, 1, paramBitmap2, -paramFloat2 / 12.0F, paramFloat2 / 12.0F, 1450);
        this.foams[2].setVerticalOffset(paramFloat2 / 7.0F);
        this.foams[3] = new Foam(6, 1, paramBitmap2, -paramFloat2 / 12.0F, paramFloat2 / 12.0F, 1400);
        this.foams[3].setVerticalOffset(paramFloat2 / 4.0F);
        this.waveHeight = (paramFloat2 / 10.0F);
        createPath();
    }

    private void createPath()
    {
        this.waterPath.reset();
        this.waterPath.moveTo(0.0F, this.y);
        int i = (int)(this.width / this.numberOfWaves);
        int j = 1;
        int k = 0;
        if (k >= this.numberOfWaves)
            label35: return;
        if (j != 0)
        {
            this.waterPath.cubicTo(this.x + i * k, this.y, this.x + i * k + i / 2.0F, this.y + this.waveHeight, this.x + i * k + i, this.y);
//            label106: if (j != 0)
//                break label180;
        }
        for (j = 1; ; j = 0)
        {
            ++k;
//            break label35:
            this.waterPath.cubicTo(this.x + i * k, this.y, this.x + i * k + i / 2.0F, this.y - this.waveHeight, this.x + i * k + i, this.y);
//            label180: break label106:
        }
    }

    public void destroy()
    {
        super.destroy();
        Foam[] arrayOfFoam = this.foams;
        int i = arrayOfFoam.length;
        for (int j = 0; j < i; ++j)
            arrayOfFoam[j].destroy();
    }

    public void draw(Canvas paramCanvas)
    {
        this.water.draw(paramCanvas);
        Foam[] arrayOfFoam = this.foams;
        int i = arrayOfFoam.length;
        for (int j = 0; j < i; ++j)
            arrayOfFoam[j].draw(paramCanvas);
    }

    public void pause()
    {
        super.pause();
        this.water.pause();
        Foam[] arrayOfFoam = this.foams;
        int i = arrayOfFoam.length;
        for (int j = 0; j < i; ++j)
            arrayOfFoam[j].pause();
    }

    public void resume()
    {
        super.resume();
        this.water.resume();
        Foam[] arrayOfFoam = this.foams;
        int i = arrayOfFoam.length;
        for (int j = 0; j < i; ++j)
            arrayOfFoam[j].resume();
    }

    public void setWaveHeight(float paramFloat)
    {
        this.waveHeight = paramFloat;
        createPath();
    }

    public void update(float paramFloat1, float paramFloat2)
    {
        int i = 0;
        super.update(paramFloat1, paramFloat2);
        Foam[] arrayOfFoam1 = this.foams;
        int j = arrayOfFoam1.length;
        for (int k = 0; k < j; ++k)
            arrayOfFoam1[k].update(paramFloat1);
        this.water.matchVertsToPath(this.waterPath, this.height, 4.0F * (this.bitmap.getWidth() / this.numberOfWaves));
        for (Foam localFoam : this.foams)
            localFoam.matchVertsToPath(this.waterPath, 4.0F * (localFoam.getBitmap().getWidth() / this.numberOfWaves));
        if (this.lastEmit + this.emitInterWall >= System.currentTimeMillis())
            return;
        Foam[] arrayOfFoam3 = this.foams;
        int i2 = arrayOfFoam3.length;
        while (i < i2)
        {
            arrayOfFoam3[i].calcWave();
            ++i;
        }
        this.lastEmit = System.currentTimeMillis();
    }
}