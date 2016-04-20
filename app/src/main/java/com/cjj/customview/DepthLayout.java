package com.cjj.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.RelativeLayout;

import com.cjj.anim.helper.LogHelper;
import com.cjj.beautifulanim.R;


public class DepthLayout extends RelativeLayout {
    public static final int DEFAULT_EDGE_COLOR = -1;
    PointF bottomLeft = new PointF(0.0F, 0.0F);
    PointF bottomLeftBack = new PointF(0.0F, 0.0F);
    PointF bottomRight = new PointF(0.0F, 0.0F);
    PointF bottomRightBack = new PointF(0.0F, 0.0F);
    private CustomShadow customShadow = new CustomShadow();
    float customShadowElevation;
    private float depth;
    Paint edgePaint = new Paint();
    private boolean isCircle = false;
    float[] prevSrc = new float[8];
    PointF topLeft = new PointF(0.0F, 0.0F);
    PointF topLeftBack = new PointF(0.0F, 0.0F);
    PointF topRight = new PointF(0.0F, 0.0F);
    PointF topRightBack = new PointF(0.0F, 0.0F);

    public DepthLayout(Context paramContext) {
        super(paramContext);
        initView(null);
    }

    public DepthLayout(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        initView(paramAttributeSet);
    }

    public DepthLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        initView(paramAttributeSet);
    }

    private void initView(AttributeSet paramAttributeSet) {
        this.edgePaint.setColor(DEFAULT_EDGE_COLOR);
        this.edgePaint.setAntiAlias(true);
        if (paramAttributeSet != null) {
            TypedArray localTypedArray = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.DepthView);
            this.edgePaint.setColor(localTypedArray.getInt(R.styleable.DepthView_edge_color, -1));
            setIsCircle(localTypedArray.getBoolean(R.styleable.DepthView_is_circle, false));
            this.depth = localTypedArray.getDimension(R.styleable.DepthView_depth, 2.0F * getResources().getDisplayMetrics().density);
            this.customShadowElevation = localTypedArray.getDimension(R.styleable.DepthView_custom_elevation, 0.0F);
            localTypedArray.recycle();
        }
//        for (; ; ) {
//            setOutlineProvider(new ViewOutlineProvider() {
//                public void getOutline(View paramAnonymousView, Outline paramAnonymousOutline) {
//                }
//            });
//            this.edgePaint.setColor(-1);
//            this.depth = (2.0F * getResources().getDisplayMetrics().density);
//            return;
//        }
    }

    public boolean calculateBounds() {
        float[] arrayOfFloat1 = new float[8];
        float[] arrayOfFloat2 = new float[8];
        arrayOfFloat2[0] = 0.0F;
        arrayOfFloat2[1] = 0.0F;
        arrayOfFloat2[2] = getWidth();
        arrayOfFloat2[3] = 0.0F;
        arrayOfFloat2[4] = 0.0F;
        arrayOfFloat2[5] = getHeight();
        arrayOfFloat2[6] = getWidth();
        arrayOfFloat2[7] = getHeight();
        Matrix localMatrix = getMatrix();
        localMatrix.mapPoints(arrayOfFloat1, arrayOfFloat2);
        this.topLeft.x = (arrayOfFloat1[0] + getLeft());
        this.topLeft.y = (arrayOfFloat1[1] + getTop());
        this.topRight.x = (arrayOfFloat1[2] + getLeft());
        this.topRight.y = (arrayOfFloat1[3] + getTop());
        this.bottomLeft.x = (arrayOfFloat1[4] + getLeft());
        this.bottomLeft.y = (arrayOfFloat1[5] + getTop());
        this.bottomRight.x = (arrayOfFloat1[6] + getLeft());
        this.bottomRight.y = (arrayOfFloat1[7] + getTop());
        boolean bool = hasMatrixChanged(arrayOfFloat1);
        this.prevSrc = arrayOfFloat1;
        float f = getRotationX() / 90.0F;
        localMatrix.postTranslate(-getRotationY() / 90.0F * getDepth(), f * getDepth());
        float[] arrayOfFloat3 = new float[8];
        float[] arrayOfFloat4 = new float[8];
        arrayOfFloat4[0] = 0.0F;
        arrayOfFloat4[1] = 0.0F;
        arrayOfFloat4[2] = getWidth();
        arrayOfFloat4[3] = 0.0F;
        arrayOfFloat4[4] = 0.0F;
        arrayOfFloat4[5] = getHeight();
        arrayOfFloat4[6] = getWidth();
        arrayOfFloat4[7] = getHeight();
        localMatrix.mapPoints(arrayOfFloat3, arrayOfFloat4);
        this.topLeftBack.x = (arrayOfFloat3[0] + getLeft());
        this.topLeftBack.y = (arrayOfFloat3[1] + getTop());
        this.topRightBack.x = (arrayOfFloat3[2] + getLeft());
        this.topRightBack.y = (arrayOfFloat3[3] + getTop());
        this.bottomLeftBack.x = (arrayOfFloat3[4] + getLeft());
        this.bottomLeftBack.y = (arrayOfFloat3[5] + getTop());
        this.bottomRightBack.x = (arrayOfFloat3[6] + getLeft());
        this.bottomRightBack.y = (arrayOfFloat3[7] + getTop());
        this.customShadow.calculateBounds(this);
        return bool;
    }

    public PointF getBottomLeft() {
        return this.bottomLeft;
    }

    public PointF getBottomLeftBack() {
        return this.bottomLeftBack;
    }

    public PointF getBottomRight() {
        return this.bottomRight;
    }

    public PointF getBottomRightBack() {
        return this.bottomRightBack;
    }

    public CustomShadow getCustomShadow() {
        return this.customShadow;
    }

    public float getCustomShadowElevation() {
        return this.customShadowElevation;
    }

    public float getDepth() {
        return this.depth;
    }

    public Paint getEdgePaint() {
        return this.edgePaint;
    }

    public PointF getTopLeft() {
        return this.topLeft;
    }

    public PointF getTopLeftBack() {
        return this.topLeftBack;
    }

    public PointF getTopRight() {
        return this.topRight;
    }

    public PointF getTopRightBack() {
        return this.topRightBack;
    }

    boolean hasMatrixChanged(float[] paramArrayOfFloat) {
        for (int i = 0; i < 8; i++) {
            if (paramArrayOfFloat[i] != this.prevSrc[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean hasOverlappingRendering() {
        return false;
    }

    public boolean isCircle() {
        return this.isCircle;
    }

    public void setCustomShadowElevation(float paramFloat) {
        this.customShadowElevation = paramFloat;
        ((View) getParent()).invalidate();
    }

    public void setDepth(float paramFloat) {
        this.depth = paramFloat;
        ((View) getParent()).invalidate();
    }

    public void setEdgePaint(Paint paramPaint) {
        this.edgePaint = paramPaint;
    }

    public void setIsCircle(boolean paramBoolean) {
        this.isCircle = paramBoolean;
    }

    class CustomShadow {
        public static final float DEFAULT_SHADOW_PADDING = 10.0F;
        PointF bottomLeftBack = new PointF(0.0F, 0.0F);
        PointF bottomRightBack = new PointF(0.0F, 0.0F);
        Matrix matrix = new Matrix();
        int padding;
        PointF topLeftBack = new PointF(0.0F, 0.0F);
        PointF topRightBack = new PointF(0.0F, 0.0F);

        CustomShadow() {
        }

        public boolean calculateBounds(DepthLayout paramDepthLayout) {
            float[] arrayOfFloat1 = new float[8];
            float f1 = DepthLayout.this.getResources().getDisplayMetrics().density;
            float f2 = DepthLayout.this.customShadowElevation;
            float f3 = DepthLayout.this.customShadowElevation / 5.0F;
            this.padding = ((int) (DepthLayout.this.customShadowElevation / 4.0F + 10.0F * f1));
            float[] arrayOfFloat2 = new float[8];
            arrayOfFloat2[0] = (-this.padding);
            arrayOfFloat2[1] = (-this.padding);
            arrayOfFloat2[2] = (paramDepthLayout.getWidth() + this.padding);
            arrayOfFloat2[3] = (-this.padding);
            arrayOfFloat2[4] = (-this.padding);
            arrayOfFloat2[5] = (paramDepthLayout.getHeight() + this.padding);
            arrayOfFloat2[6] = (paramDepthLayout.getWidth() + this.padding);
            arrayOfFloat2[7] = (paramDepthLayout.getHeight() + this.padding);
            DepthLayout.this.getMatrix().mapPoints(arrayOfFloat1, arrayOfFloat2);
            this.topLeftBack.x = (f3 + (arrayOfFloat1[0] + paramDepthLayout.getLeft()));
            this.topLeftBack.y = (f2 + (arrayOfFloat1[1] + paramDepthLayout.getTop()));
            this.topRightBack.x = (f3 + (arrayOfFloat1[2] + paramDepthLayout.getLeft()));
            this.topRightBack.y = (f2 + (arrayOfFloat1[3] + paramDepthLayout.getTop()));
            this.bottomLeftBack.x = (f3 + (arrayOfFloat1[4] + paramDepthLayout.getLeft()));
            this.bottomLeftBack.y = (f2 + (arrayOfFloat1[5] + paramDepthLayout.getTop()));
            this.bottomRightBack.x = (f3 + (arrayOfFloat1[6] + paramDepthLayout.getLeft()));
            this.bottomRightBack.y = (f2 + (arrayOfFloat1[7] + paramDepthLayout.getTop()));
            return false;
        }

        public void drawShadow(Canvas paramCanvas, DepthLayout paramDepthLayout, Drawable paramDrawable) {
            paramDrawable.setBounds(-this.padding, -this.padding, paramDepthLayout.getWidth() + this.padding, paramDepthLayout.getHeight() + this.padding);
            float[] arrayOfFloat1 = new float[8];
            arrayOfFloat1[0] = 0.0F;
            arrayOfFloat1[1] = 0.0F;
            arrayOfFloat1[2] = paramDepthLayout.getWidth();
            arrayOfFloat1[3] = 0.0F;
            arrayOfFloat1[4] = paramDepthLayout.getWidth();
            arrayOfFloat1[5] = paramDepthLayout.getHeight();
            arrayOfFloat1[6] = 0.0F;
            arrayOfFloat1[7] = paramDepthLayout.getHeight();
            float[] arrayOfFloat2 = new float[8];
            arrayOfFloat2[0] = this.topLeftBack.x;
            arrayOfFloat2[1] = this.topLeftBack.y;
            arrayOfFloat2[2] = this.topRightBack.x;
            arrayOfFloat2[3] = this.topRightBack.y;
            arrayOfFloat2[4] = this.bottomRightBack.x;
            arrayOfFloat2[5] = this.bottomRightBack.y;
            arrayOfFloat2[6] = this.bottomLeftBack.x;
            arrayOfFloat2[7] = this.bottomLeftBack.y;
            int i = paramCanvas.save();
            this.matrix.setPolyToPoly(arrayOfFloat1, 0, arrayOfFloat2, 0, arrayOfFloat1.length >> 1);
            paramCanvas.concat(this.matrix);
            paramDrawable.draw(paramCanvas);
            paramCanvas.restoreToCount(i);
        }
    }
}
