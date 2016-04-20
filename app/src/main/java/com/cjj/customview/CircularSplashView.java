package com.cjj.customview;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.cjj.anim.evaluator.RectEvaluator;
import com.cjj.anim.evaluator.RectFEvaluator;
import com.cjj.anim.interpolators.QuintOut;
import com.cjj.beautifulanim.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CircularSplashView extends View {
    private List<CircledDrawable> circles = new ArrayList();
    private Bitmap splash;
    private int splashColor;

    public CircularSplashView(Context paramContext) {
        super(paramContext);
    }

    public CircularSplashView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public CircularSplashView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    public void introAnimate() {
        this.circles.clear();
        RectF localRectF1 = new RectF(0.0F, 0.0F, getWidth(), getHeight());
        Rect localRect = new Rect(0, 0, getWidth(), getHeight());
        RectF localRectF2 = new RectF(-1.0F, -1.0F, 1 + getWidth(), 1 + getHeight());
        this.circles.add(new CircleColorExpand(localRectF1, 0L, 600L, this.splashColor));
        this.circles.add(new CircleColorExpand(localRectF2, 70L, 600L, getResources().getColor(R.color.white)));
        this.circles.add(new CircleBitmapExpand(localRect, 130L, 800L, this.splash));
        Iterator localIterator = this.circles.iterator();
        while (localIterator.hasNext()) {
            ((CircledDrawable) localIterator.next()).startAnim();
        }
    }

    protected void onDraw(Canvas paramCanvas) {
        super.onDraw(paramCanvas);
        Iterator localIterator = this.circles.iterator();
        while (localIterator.hasNext()) {
            ((CircledDrawable) localIterator.next()).draw(paramCanvas);
        }
    }

    protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
        if (this.circles.size() == 0) {
            introAnimate();
        }
    }

    public void setSplash(Bitmap paramBitmap) {
        this.splash = paramBitmap;
    }

    public void setSplashColor(int paramInt) {
        this.splashColor = paramInt;
    }

    public class CircleBitmapExpand
            implements CircularSplashView.CircledDrawable {
        long animDuration;
        Bitmap bitmap;
        Rect drawingRect;
        long startDelay;
        Rect targetSize;

        public CircleBitmapExpand(Rect paramRect, long paramLong1, long paramLong2, Bitmap paramBitmap) {
            this.targetSize = paramRect;
            this.startDelay = paramLong1;
            this.animDuration = paramLong2;
            this.bitmap = paramBitmap;
        }

        public Bitmap GetBitmapClippedCircle(Bitmap paramBitmap) {
            int i = paramBitmap.getWidth();
            int j = paramBitmap.getHeight();
            Bitmap localBitmap = Bitmap.createBitmap(i, j, Config.ARGB_8888);
            Path localPath = new Path();
            localPath.addCircle(i / 2, j / 2, Math.min(i, j / 2), Direction.CCW);
            Canvas localCanvas = new Canvas(localBitmap);
            localCanvas.clipPath(localPath);
            localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, null);
            paramBitmap.recycle();
            return localBitmap;
        }

        public void draw(Canvas paramCanvas) {
            if (this.drawingRect != null && this.bitmap != null) {
                paramCanvas.drawBitmap(this.bitmap, null, this.drawingRect, null);
            }
        }

        public void startAnim() {
            Rect localRect = new Rect(this.targetSize.centerX(), this.targetSize.centerY(), this.targetSize.centerX(), this.targetSize.centerY());
            this.drawingRect = localRect;
            RectEvaluator localRectEvaluator = new RectEvaluator();
            Object[] arrayOfObject = new Object[2];
            arrayOfObject[0] = localRect;
            arrayOfObject[1] = this.targetSize;
            ValueAnimator localValueAnimator = ValueAnimator.ofObject(localRectEvaluator, arrayOfObject);
            localValueAnimator.setDuration(this.animDuration);
            localValueAnimator.setInterpolator(new QuintOut());
            localValueAnimator.setStartDelay(this.startDelay);
            localValueAnimator.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator) {
                    CircularSplashView.CircleBitmapExpand.this.drawingRect = ((Rect) paramAnonymousValueAnimator.getAnimatedValue());
                    CircularSplashView.this.invalidate();
                }
            });
            localValueAnimator.start();
        }
    }

    public class CircleColorExpand
            implements CircularSplashView.CircledDrawable {
        long animDuration;
        RectF drawingRect;
        private Paint paint = new Paint(-16777216);
        long startDelay;
        RectF targetSize;

        public CircleColorExpand(RectF paramRectF, long paramLong1, long paramLong2, int paramInt) {
            this.targetSize = paramRectF;
            this.startDelay = paramLong1;
            this.animDuration = paramLong2;
            this.paint.setColor(paramInt);
            this.paint.setAntiAlias(true);
            this.paint.setDither(true);
        }

        public void draw(Canvas paramCanvas) {
            if (this.drawingRect != null) {
                paramCanvas.drawOval(this.drawingRect, this.paint);
            }
        }

        public void startAnim() {
            RectF localRectF = new RectF(this.targetSize.centerX(), this.targetSize.centerY(), this.targetSize.centerX(), this.targetSize.centerY());
            RectFEvaluator localRectFEvaluator = new RectFEvaluator();
            Object[] arrayOfObject = new Object[2];
            arrayOfObject[0] = localRectF;
            arrayOfObject[1] = this.targetSize;
            ValueAnimator localValueAnimator = ValueAnimator.ofObject(localRectFEvaluator, arrayOfObject);
            localValueAnimator.setDuration(this.animDuration);
            localValueAnimator.setInterpolator(new QuintOut());
            localValueAnimator.setStartDelay(this.startDelay);
            localValueAnimator.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator) {
                    CircularSplashView.CircleColorExpand.this.drawingRect = ((RectF) paramAnonymousValueAnimator.getAnimatedValue());
                    CircularSplashView.this.invalidate();
                }
            });
            localValueAnimator.start();
        }
    }

    static abstract interface CircledDrawable {
        public abstract void draw(Canvas paramCanvas);

        public abstract void startAnim();
    }
}
