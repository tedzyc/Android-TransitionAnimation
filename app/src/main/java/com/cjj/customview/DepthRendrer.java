package com.cjj.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.RelativeLayout;

import com.cjj.anim.helper.LogHelper;
import com.cjj.beautifulanim.R;


public class DepthRendrer extends RelativeLayout {
    private Path edgePath = new Path();
    Matrix matrix = new Matrix();
    private Drawable roundSoftShadow;
    private float shadowAlpha = 0.3F;
    private Paint shadowPaint = new Paint();
    private NinePatchDrawable softShadow;

    public DepthRendrer(Context paramContext) {
        super(paramContext);
        setup();
    }

    public DepthRendrer(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        setup();
    }

    public DepthRendrer(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        setup();
    }

    public DepthRendrer(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2) {
        super(paramContext, paramAttributeSet, paramInt1, paramInt2);
        setup();
    }

    private void drawBottomEdge(DepthLayout paramDepthLayout, Canvas paramCanvas, float[] paramArrayOfFloat) {
        float[] arrayOfFloat = new float[8];
        arrayOfFloat[0] = paramDepthLayout.getBottomLeft().x;
        arrayOfFloat[1] = paramDepthLayout.getBottomLeft().y;
        arrayOfFloat[2] = paramDepthLayout.getBottomRight().x;
        arrayOfFloat[3] = paramDepthLayout.getBottomRight().y;
        arrayOfFloat[4] = paramDepthLayout.getBottomRightBack().x;
        arrayOfFloat[5] = paramDepthLayout.getBottomRightBack().y;
        arrayOfFloat[6] = paramDepthLayout.getBottomLeftBack().x;
        arrayOfFloat[7] = paramDepthLayout.getBottomLeftBack().y;
        int i = paramCanvas.save();
        this.matrix.setPolyToPoly(paramArrayOfFloat, 0, arrayOfFloat, 0, arrayOfFloat.length >> 1);
        paramCanvas.concat(this.matrix);
        drawRectancle(paramDepthLayout, paramCanvas);
        drawShadow(paramDepthLayout.getBottomLeft(), paramDepthLayout.getBottomRight(), 0.0F, paramCanvas, paramDepthLayout);
        paramCanvas.restoreToCount(i);
    }

    private void drawCornerBaseShape(DepthLayout paramDepthLayout, Canvas paramCanvas, float[] paramArrayOfFloat) {
        float[] arrayOfFloat = new float[8];
        arrayOfFloat[0] = paramDepthLayout.getTopLeftBack().x;
        arrayOfFloat[1] = paramDepthLayout.getTopLeftBack().y;
        arrayOfFloat[2] = paramDepthLayout.getTopRightBack().x;
        arrayOfFloat[3] = paramDepthLayout.getTopRightBack().y;
        arrayOfFloat[4] = paramDepthLayout.getBottomRightBack().x;
        arrayOfFloat[5] = paramDepthLayout.getBottomRightBack().y;
        arrayOfFloat[6] = paramDepthLayout.getBottomLeftBack().x;
        arrayOfFloat[7] = paramDepthLayout.getBottomLeftBack().y;
        int i = paramCanvas.save();
        this.matrix.setPolyToPoly(paramArrayOfFloat, 0, arrayOfFloat, 0, paramArrayOfFloat.length >> 1);
        paramCanvas.concat(this.matrix);
        this.edgePath.reset();
        this.edgePath.addRoundRect(0.0F, 0.0F, paramDepthLayout.getWidth(), paramDepthLayout.getHeight(), paramDepthLayout.getWidth() / 2.0F, paramDepthLayout.getHeight() / 2.0F, Direction.CCW);
        paramCanvas.drawPath(this.edgePath, paramDepthLayout.getEdgePaint());
        this.shadowPaint.setAlpha((int) (255.0F * (0.5F * this.shadowAlpha)));
        paramCanvas.drawPath(this.edgePath, this.shadowPaint);
        paramCanvas.restoreToCount(i);
    }

    private void drawHorizontalFist(DepthLayout paramDepthLayout, Canvas paramCanvas, float[] paramArrayOfFloat) {
        if (getLeftEdgeLength(paramDepthLayout) <= getRightEdgeLength(paramDepthLayout)) {
            drawLeftEdge(paramDepthLayout, paramCanvas, paramArrayOfFloat);
        }
//        for (; ; ) {
//            drawTopEdge(paramDepthLayout, paramCanvas, paramArrayOfFloat);
//            drawBottomEdge(paramDepthLayout, paramCanvas, paramArrayOfFloat);
//            if (getLeftEdgeLength(paramDepthLayout) < getRightEdgeLength(paramDepthLayout)) {
//                break;
//            }
//            drawLeftEdge(paramDepthLayout, paramCanvas, paramArrayOfFloat);
//            drawRightEdge(paramDepthLayout, paramCanvas, paramArrayOfFloat);
//        }
        drawRightEdge(paramDepthLayout, paramCanvas, paramArrayOfFloat);
    }

    private void drawLeftEdge(DepthLayout paramDepthLayout, Canvas paramCanvas, float[] paramArrayOfFloat) {
        float[] arrayOfFloat = new float[8];
        arrayOfFloat[0] = paramDepthLayout.getTopLeft().x;
        arrayOfFloat[1] = paramDepthLayout.getTopLeft().y;
        arrayOfFloat[2] = paramDepthLayout.getTopLeftBack().x;
        arrayOfFloat[3] = paramDepthLayout.getTopLeftBack().y;
        arrayOfFloat[4] = paramDepthLayout.getBottomLeftBack().x;
        arrayOfFloat[5] = paramDepthLayout.getBottomLeftBack().y;
        arrayOfFloat[6] = paramDepthLayout.getBottomLeft().x;
        arrayOfFloat[7] = paramDepthLayout.getBottomLeft().y;
        int i = paramCanvas.save();
        this.matrix.setPolyToPoly(paramArrayOfFloat, 0, arrayOfFloat, 0, paramArrayOfFloat.length >> 1);
        paramCanvas.concat(this.matrix);
        drawRectancle(paramDepthLayout, paramCanvas);
        drawShadow(paramDepthLayout.getTopLeft(), paramDepthLayout.getBottomLeft(), 0.0F, paramCanvas, paramDepthLayout);
        paramCanvas.restoreToCount(i);
    }

    private void drawRectancle(DepthLayout paramDepthLayout, Canvas paramCanvas) {
        paramCanvas.drawRect(0.0F, 0.0F, paramDepthLayout.getWidth(), paramDepthLayout.getHeight(), paramDepthLayout.getEdgePaint());
    }

    private void drawRightEdge(DepthLayout paramDepthLayout, Canvas paramCanvas, float[] paramArrayOfFloat) {
        float[] arrayOfFloat = new float[8];
        arrayOfFloat[0] = paramDepthLayout.getTopRight().x;
        arrayOfFloat[1] = paramDepthLayout.getTopRight().y;
        arrayOfFloat[2] = paramDepthLayout.getTopRightBack().x;
        arrayOfFloat[3] = paramDepthLayout.getTopRightBack().y;
        arrayOfFloat[4] = paramDepthLayout.getBottomRightBack().x;
        arrayOfFloat[5] = paramDepthLayout.getBottomRightBack().y;
        arrayOfFloat[6] = paramDepthLayout.getBottomRight().x;
        arrayOfFloat[7] = paramDepthLayout.getBottomRight().y;
        int i = paramCanvas.save();
        this.matrix.setPolyToPoly(paramArrayOfFloat, 0, arrayOfFloat, 0, paramArrayOfFloat.length >> 1);
        paramCanvas.concat(this.matrix);
        drawRectancle(paramDepthLayout, paramCanvas);
        drawShadow(paramDepthLayout.getTopRight(), paramDepthLayout.getBottomRight(), -180.0F, paramCanvas, paramDepthLayout);
        paramCanvas.restoreToCount(i);
    }

    private void drawTopEdge(DepthLayout paramDepthLayout, Canvas paramCanvas, float[] paramArrayOfFloat) {
        float[] arrayOfFloat = new float[8];
        arrayOfFloat[0] = paramDepthLayout.getTopLeft().x;
        arrayOfFloat[1] = paramDepthLayout.getTopLeft().y;
        arrayOfFloat[2] = paramDepthLayout.getTopRight().x;
        arrayOfFloat[3] = paramDepthLayout.getTopRight().y;
        arrayOfFloat[4] = paramDepthLayout.getTopRightBack().x;
        arrayOfFloat[5] = paramDepthLayout.getTopRightBack().y;
        arrayOfFloat[6] = paramDepthLayout.getTopLeftBack().x;
        arrayOfFloat[7] = paramDepthLayout.getTopLeftBack().y;
        int i = paramCanvas.save();
        this.matrix.setPolyToPoly(paramArrayOfFloat, 0, arrayOfFloat, 0, paramArrayOfFloat.length >> 1);
        paramCanvas.concat(this.matrix);
        drawRectancle(paramDepthLayout, paramCanvas);
        drawShadow(paramDepthLayout.getTopLeft(), paramDepthLayout.getTopRight(), -180.0F, paramCanvas, paramDepthLayout);
        paramCanvas.restoreToCount(i);
    }

    private void drawVerticalFirst(DepthLayout paramDepthLayout, Canvas paramCanvas, float[] paramArrayOfFloat) {
        if (getTopEdgeLength(paramDepthLayout) <= getBottomEdgeLength(paramDepthLayout)) {
            drawTopEdge(paramDepthLayout, paramCanvas, paramArrayOfFloat);
        }
//        for (; ; ) {
//            drawLeftEdge(paramDepthLayout, paramCanvas, paramArrayOfFloat);
//            drawRightEdge(paramDepthLayout, paramCanvas, paramArrayOfFloat);
//            if (getTopEdgeLength(paramDepthLayout) < getBottomEdgeLength(paramDepthLayout)) {
//                break;
//            }
//            drawTopEdge(paramDepthLayout, paramCanvas, paramArrayOfFloat);
//            drawBottomEdge(paramDepthLayout, paramCanvas, paramArrayOfFloat);
//        }
        drawBottomEdge(paramDepthLayout, paramCanvas, paramArrayOfFloat);
    }

    private float getBottomEdgeLength(DepthLayout paramDepthLayout) {
        return getDistance(paramDepthLayout.getBottomLeftBack(), paramDepthLayout.getBottomRightBack());
    }

    private float getLeftEdgeLength(DepthLayout paramDepthLayout) {
        return getDistance(paramDepthLayout.getTopLeftBack(), paramDepthLayout.getBottomLeftBack());
    }

    private float getRightEdgeLength(DepthLayout paramDepthLayout) {
        return getDistance(paramDepthLayout.getTopRightBack(), paramDepthLayout.getBottomRightBack());
    }

    protected boolean drawChild(Canvas paramCanvas, View paramView, long paramLong) {
        LogHelper.trace("drawchild");
        DepthLayout localDepthLayout;
        float[] arrayOfFloat;
        if (((paramView instanceof DepthLayout)) && (!isInEditMode())) {
            localDepthLayout = (DepthLayout) paramView;
            arrayOfFloat = new float[8];
            arrayOfFloat[0] = 0.0F;
            arrayOfFloat[1] = 0.0F;
            arrayOfFloat[2] = localDepthLayout.getWidth();
            arrayOfFloat[3] = 0.0F;
            arrayOfFloat[4] = localDepthLayout.getWidth();
            arrayOfFloat[5] = localDepthLayout.getHeight();
            arrayOfFloat[6] = 0.0F;
            arrayOfFloat[7] = localDepthLayout.getHeight();
            if (!localDepthLayout.isCircle()) {
                localDepthLayout.getCustomShadow().drawShadow(paramCanvas, localDepthLayout, this.softShadow);
                if ((localDepthLayout.getRotationX() != 0.0F) || (localDepthLayout.getRotationY() != 0.0F)) {
                    if (getLongestHorizontalEdge(localDepthLayout) > getLongestVerticalEdge(localDepthLayout)) {
                        drawVerticalFirst(localDepthLayout, paramCanvas, arrayOfFloat);
                    } else {
                        drawHorizontalFist(localDepthLayout, paramCanvas, arrayOfFloat);
                    }
                }
            }else {
                localDepthLayout.getCustomShadow().drawShadow(paramCanvas, localDepthLayout, this.roundSoftShadow);
            }
//
            if ((Math.abs(localDepthLayout.getRotationX()) > 1.0F) || (Math.abs(localDepthLayout.getRotationY()) > 1.0F)) {
                drawCornerBaseShape(localDepthLayout, paramCanvas, arrayOfFloat);
            }
        }
        return super.drawChild(paramCanvas, paramView, paramLong);
    }

    void drawShadow(PointF paramPointF1, PointF paramPointF2, float paramFloat, Canvas paramCanvas, DepthLayout paramDepthLayout) {
        float f = Math.abs(paramFloat + Math.abs(getAngle(paramPointF1, paramPointF2))) / 180.0F;
        this.shadowPaint.setAlpha((int) (255.0F * f * this.shadowAlpha));
        paramCanvas.drawRect(0.0F, 0.0F, paramDepthLayout.getWidth(), paramDepthLayout.getHeight(), this.shadowPaint);
    }

    public float getAngle(PointF paramPointF1, PointF paramPointF2) {
        return (float) Math.toDegrees(Math.atan2(paramPointF1.y - paramPointF2.y, paramPointF1.x - paramPointF2.x));
    }

    float getDistance(PointF paramPointF1, PointF paramPointF2) {
        return (float) Math.sqrt((paramPointF1.x - paramPointF2.x) * (paramPointF1.x - paramPointF2.x) + (paramPointF1.y - paramPointF2.y) * (paramPointF1.y - paramPointF2.y));
    }

    float getLongestHorizontalEdge(DepthLayout paramDepthLayout) {
        float f1 = getTopEdgeLength(paramDepthLayout);
        float f2 = getBottomEdgeLength(paramDepthLayout);
        if (f1 > f2) {
            return f1;
        }
        return f2;
    }

    float getLongestVerticalEdge(DepthLayout paramDepthLayout) {
        float f1 = getLeftEdgeLength(paramDepthLayout);
        float f2 = getRightEdgeLength(paramDepthLayout);
        if (f1 > f2) {
            return f1;
        }
        return f2;
    }

    public float getShadowAlpha() {
        return this.shadowAlpha;
    }

    public float getTopEdgeLength(DepthLayout paramDepthLayout) {
        return getDistance(paramDepthLayout.getTopLeftBack(), paramDepthLayout.getTopRightBack());
    }

    public void setShadowAlpha(float paramFloat) {
        this.shadowAlpha = Math.min(1.0F, Math.max(0.0F, paramFloat));
    }

    void setup() {
        LogHelper.trace("setup");
        getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
            public boolean onPreDraw() {
                for (int i = 0; i < DepthRendrer.this.getChildCount(); i++) {
                    View localView = DepthRendrer.this.getChildAt(i);
                    if (((localView instanceof DepthLayout)) && (((DepthLayout) localView).calculateBounds())) {
                        DepthRendrer.this.invalidate();
                        LogHelper.trace("setup1");
                    }
                }
                return true;
            }
        });
        this.shadowPaint.setColor(Color.GRAY);
        this.shadowPaint.setAntiAlias(true);
        this.softShadow = ((NinePatchDrawable) getResources().getDrawable(R.drawable.shadow, null));
        this.roundSoftShadow = getResources().getDrawable(R.drawable.round_soft_shadow, null);
        LogHelper.trace("setup3");
    }
}


