package com.cjj.anim.helper;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.util.Property;
import android.view.View;

import com.cjj.anim.interpolators.BackOut;
import com.cjj.anim.interpolators.CircInOut;
import com.cjj.anim.interpolators.ExpoIn;
import com.cjj.anim.interpolators.ExpoOut;
import com.cjj.anim.interpolators.QuadInOut;
import com.cjj.anim.interpolators.QuintInOut;
import com.cjj.anim.interpolators.QuintOut;
import com.cjj.beautifulanim.R;
import com.cjj.customview.DepthLayout;


public class TransitionHelper {
    public static final int DURATION = 1100;
    public static final int FISRTDELAY = 300;
    public static final int MOVE_Y_STEP = 15;
    public static final float TARGET_ROTATION = -50.0F;
    public static final float TARGET_ROTATION_X = 60.0F;
    public static final float TARGET_SCALE = 0.5F;
    public static final QuintOut VALUEinterpolator = new QuintOut();

    public static void animateMenuOut(View paramView) {
        continueOutToRight((DepthLayout) paramView.findViewById(R.id.root_dl), 0.0F, 20);
        continueOutToRight((DepthLayout) paramView.findViewById(R.id.appbar), 15.0F, 0);
        continueOutToRight((DepthLayout) paramView.findViewById(R.id.fab_container), 30.0F, 40);
        continueOutToRight((DepthLayout) paramView.findViewById(R.id.dl2), 15.0F, 60);
        continueOutToRight((DepthLayout) paramView.findViewById(R.id.dl3), 30.0F, 80);
    }

    public static void animateToMenuState(View paramView, AnimatorListenerAdapter paramAnimatorListenerAdapter) {
        hideStatusBar(paramView);
        exitAnimate((DepthLayout) paramView.findViewById(R.id.root_dl), 0.0F, 30.0F, 15L, 190, false);
        exitAnimate((DepthLayout) paramView.findViewById(R.id.appbar), 15.0F, 20.0F, 30L, 170, false);
        exitAnimate((DepthLayout) paramView.findViewById(R.id.fab_container), 30.0F, 20.0F, 45L, 210, false);
        exitAnimate((DepthLayout) paramView.findViewById(R.id.dl2), 15.0F, 20.0F, 60L, 230, false);
        exitAnimate((DepthLayout) paramView.findViewById(R.id.dl3), 30.0F, 20.0F, 75L, 250, false).addListener(paramAnimatorListenerAdapter);
        Property localProperty = View.TRANSLATION_Y;
        float[] arrayOfFloat = new float[1];
        arrayOfFloat[0] = (-90.0F * paramView.getResources().getDisplayMetrics().density);
        ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramView, localProperty, arrayOfFloat).setDuration(1100L);
        localObjectAnimator.setInterpolator(VALUEinterpolator);
        localObjectAnimator.start();
    }

    private static void continueOutToRight(DepthLayout paramDepthLayout, float paramFloat, int paramInt) {
        Property localProperty1 = View.TRANSLATION_Y;
        float[] arrayOfFloat1 = new float[2];
        arrayOfFloat1[0] = (-paramFloat * paramDepthLayout.getResources().getDisplayMetrics().density);
        arrayOfFloat1[1] = (-paramDepthLayout.getResources().getDisplayMetrics().heightPixels);
        ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(paramDepthLayout, localProperty1, arrayOfFloat1).setDuration(900L);
        localObjectAnimator1.setInterpolator(new ExpoIn());
        localObjectAnimator1.setStartDelay(paramInt + 0);
        localObjectAnimator1.start();
        Property localProperty2 = View.TRANSLATION_X;
        float[] arrayOfFloat2 = new float[2];
        arrayOfFloat2[0] = paramDepthLayout.getTranslationX();
        arrayOfFloat2[1] = paramDepthLayout.getResources().getDisplayMetrics().widthPixels;
        ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(paramDepthLayout, localProperty2, arrayOfFloat2).setDuration(900L);
        localObjectAnimator2.setInterpolator(new ExpoIn());
        localObjectAnimator2.setStartDelay(paramInt + 0);
        localObjectAnimator2.start();
    }

    static ValueAnimator exitAnimate(DepthLayout paramDepthLayout, float paramFloat1, float paramFloat2, long paramLong, int paramInt, boolean paramBoolean) {
        paramDepthLayout.setPivotY(getDistanceToCenter(paramDepthLayout));
        paramDepthLayout.setPivotX(getDistanceToCenterX(paramDepthLayout));
        paramDepthLayout.setCameraDistance(10000.0F * paramDepthLayout.getResources().getDisplayMetrics().density);
        ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(paramDepthLayout, View.ROTATION_X, new float[]{60.0F}).setDuration(1100L);
        localObjectAnimator1.setInterpolator(VALUEinterpolator);
        localObjectAnimator1.setStartDelay(paramLong);
        localObjectAnimator1.start();
        float[] arrayOfFloat1 = new float[2];
        arrayOfFloat1[0] = paramDepthLayout.getCustomShadowElevation();
        arrayOfFloat1[1] = (paramFloat2 * paramDepthLayout.getResources().getDisplayMetrics().density);
        ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(paramDepthLayout, "CustomShadowElevation", arrayOfFloat1).setDuration(1100L);
        localObjectAnimator2.setInterpolator(VALUEinterpolator);
        localObjectAnimator2.setStartDelay(paramLong);
        localObjectAnimator2.start();
        ObjectAnimator localObjectAnimator3 = ObjectAnimator.ofFloat(paramDepthLayout, View.SCALE_X, new float[]{0.5F}).setDuration(1100L);
        localObjectAnimator3.setInterpolator(new QuintOut());
        localObjectAnimator3.setStartDelay(paramLong);
        localObjectAnimator3.start();
        ObjectAnimator localObjectAnimator4 = ObjectAnimator.ofFloat(paramDepthLayout, View.SCALE_Y, new float[]{0.5F}).setDuration(1100L);
        localObjectAnimator4.setInterpolator(new QuintOut());
        localObjectAnimator4.setStartDelay(paramLong);
        localObjectAnimator4.start();
        ObjectAnimator localObjectAnimator5 = ObjectAnimator.ofFloat(paramDepthLayout, View.ROTATION, new float[]{-50.0F}).setDuration(1600L);
        localObjectAnimator5.setInterpolator(VALUEinterpolator);
        localObjectAnimator5.setStartDelay(paramLong);
        localObjectAnimator5.start();
        Property localProperty = View.TRANSLATION_Y;
        float[] arrayOfFloat2 = new float[1];
        arrayOfFloat2[0] = (-paramFloat1 * paramDepthLayout.getResources().getDisplayMetrics().density);
        ObjectAnimator localObjectAnimator6 = ObjectAnimator.ofFloat(paramDepthLayout, localProperty, arrayOfFloat2).setDuration(paramInt);
        localObjectAnimator6.setInterpolator(VALUEinterpolator);
        localObjectAnimator6.setStartDelay(paramLong);
        localObjectAnimator6.start();
        if (paramBoolean) {
            continueOutToRight(paramDepthLayout, paramFloat1, paramInt);
        }
        return localObjectAnimator4;
    }

    public static float getDistanceToCenter(View paramView) {
        float f = paramView.getTop() + paramView.getHeight() / 2.0F;
        return ((View) paramView.getParent()).getHeight() / 2 + paramView.getHeight() / 2.0F - f;
    }

    public static float getDistanceToCenterX(View paramView) {
        float f = paramView.getLeft() + paramView.getWidth() / 2.0F;
        return ((View) paramView.getParent()).getWidth() / 2 + paramView.getWidth() / 2.0F - f;
    }

    @NonNull
    private static AnimatorListenerAdapter getShowStatusBarListener(final DepthLayout paramDepthLayout) {
        return new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator paramAnonymousAnimator) {
                super.onAnimationEnd(paramAnonymousAnimator);
                TransitionHelper.makeAppFullscreen(paramDepthLayout);
            }
        };
    }

    private static void hideStatusBar(View paramView) {
    }

    static ObjectAnimator introAnimate(DepthLayout paramDepthLayout, float paramFloat1, float paramFloat2, long paramLong, int paramInt) {
        paramDepthLayout.setPivotY(getDistanceToCenter(paramDepthLayout));
        paramDepthLayout.setPivotX(getDistanceToCenterX(paramDepthLayout));
        paramDepthLayout.setCameraDistance(10000.0F * paramDepthLayout.getResources().getDisplayMetrics().density);
        Property localProperty1 = View.TRANSLATION_Y;
        float[] arrayOfFloat1 = new float[2];
        arrayOfFloat1[0] = paramDepthLayout.getResources().getDisplayMetrics().heightPixels;
        arrayOfFloat1[1] = (-paramFloat1 * paramDepthLayout.getResources().getDisplayMetrics().density);
        ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(paramDepthLayout, localProperty1, arrayOfFloat1).setDuration(800L);
        localObjectAnimator1.setInterpolator(new ExpoOut());
        localObjectAnimator1.setStartDelay(paramInt + 700);
        localObjectAnimator1.start();
        paramDepthLayout.setTranslationY(paramDepthLayout.getResources().getDisplayMetrics().heightPixels);
        Property localProperty2 = View.TRANSLATION_X;
        float[] arrayOfFloat2 = new float[2];
        arrayOfFloat2[0] = (-paramDepthLayout.getResources().getDisplayMetrics().widthPixels);
        arrayOfFloat2[1] = 0.0F;
        ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(paramDepthLayout, localProperty2, arrayOfFloat2).setDuration(800L);
        localObjectAnimator2.setInterpolator(new ExpoOut());
        localObjectAnimator2.setStartDelay(paramInt + 700);
        localObjectAnimator2.start();
        paramDepthLayout.setTranslationX(-paramDepthLayout.getResources().getDisplayMetrics().widthPixels);
        ObjectAnimator localObjectAnimator3 = ObjectAnimator.ofFloat(paramDepthLayout, View.TRANSLATION_Y, new float[]{0.0F}).setDuration(700L);
        localObjectAnimator3.setInterpolator(new BackOut());
        localObjectAnimator3.setStartDelay(1500L);
        localObjectAnimator3.start();
        ObjectAnimator localObjectAnimator4 = ObjectAnimator.ofFloat(paramDepthLayout, View.ROTATION_X, new float[]{60.0F, 0.0F}).setDuration(1000L);
        localObjectAnimator4.setInterpolator(new QuintInOut());
        localObjectAnimator4.setStartDelay(paramInt + 1000);
        localObjectAnimator4.start();
        paramDepthLayout.setRotationX(60.0F);
        float[] arrayOfFloat3 = new float[2];
        arrayOfFloat3[0] = (paramFloat2 * paramDepthLayout.getResources().getDisplayMetrics().density);
        arrayOfFloat3[1] = paramDepthLayout.getCustomShadowElevation();
        ObjectAnimator localObjectAnimator5 = ObjectAnimator.ofFloat(paramDepthLayout, "CustomShadowElevation", arrayOfFloat3).setDuration(1000L);
        localObjectAnimator5.setInterpolator(new QuintInOut());
        localObjectAnimator5.setStartDelay(1000 + paramInt * 2);
        localObjectAnimator5.start();
        paramDepthLayout.setCustomShadowElevation(paramFloat2 * paramDepthLayout.getResources().getDisplayMetrics().density);
        Property localProperty3 = View.SCALE_X;
        float[] arrayOfFloat4 = new float[2];
        arrayOfFloat4[0] = 0.5F;
        arrayOfFloat4[1] = paramDepthLayout.getScaleX();
        ObjectAnimator localObjectAnimator6 = ObjectAnimator.ofFloat(paramDepthLayout, localProperty3, arrayOfFloat4).setDuration(1000L);
        localObjectAnimator6.setInterpolator(new CircInOut());
        localObjectAnimator6.setStartDelay(paramInt + 1000);
        localObjectAnimator6.start();
        paramDepthLayout.setScaleX(0.5F);
        Property localProperty4 = View.SCALE_Y;
        float[] arrayOfFloat5 = new float[2];
        arrayOfFloat5[0] = 0.5F;
        arrayOfFloat5[1] = paramDepthLayout.getScaleY();
        ObjectAnimator localObjectAnimator7 = ObjectAnimator.ofFloat(paramDepthLayout, localProperty4, arrayOfFloat5).setDuration(1000L);
        localObjectAnimator7.setInterpolator(new CircInOut());
        localObjectAnimator7.setStartDelay(paramInt + 1000);
        localObjectAnimator7.start();
        paramDepthLayout.setScaleY(0.5F);
        ObjectAnimator localObjectAnimator8 = ObjectAnimator.ofFloat(paramDepthLayout, View.ROTATION, new float[]{-50.0F, 0.0F}).setDuration(1400L);
        localObjectAnimator8.setInterpolator(new QuadInOut());
        localObjectAnimator8.setStartDelay(paramInt + 300);
        localObjectAnimator8.start();
        paramDepthLayout.setRotation(-50.0F);
        localObjectAnimator8.addListener(getShowStatusBarListener(paramDepthLayout));
        return localObjectAnimator7;
    }

    private static void makeAppFullscreen(View paramView) {
    }

    static ObjectAnimator revertFromMenu(DepthLayout paramDepthLayout, float paramFloat1, int paramInt, float paramFloat2) {
        paramDepthLayout.setPivotY(getDistanceToCenter(paramDepthLayout));
        paramDepthLayout.setPivotX(getDistanceToCenterX(paramDepthLayout));
        paramDepthLayout.setCameraDistance(10000.0F * paramDepthLayout.getResources().getDisplayMetrics().density);
        ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(paramDepthLayout, View.TRANSLATION_Y, new float[]{0.0F}).setDuration(700L);
        localObjectAnimator1.setInterpolator(new BackOut());
        localObjectAnimator1.setStartDelay(paramInt + 550);
        localObjectAnimator1.start();
        Property localProperty1 = View.ROTATION_X;
        float[] arrayOfFloat1 = new float[2];
        arrayOfFloat1[0] = paramDepthLayout.getRotationX();
        arrayOfFloat1[1] = 0.0F;
        ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(paramDepthLayout, localProperty1, arrayOfFloat1).setDuration(1000L);
        localObjectAnimator2.setInterpolator(new QuintInOut());
        localObjectAnimator2.setStartDelay(paramInt + 300);
        localObjectAnimator2.start();
        paramDepthLayout.setRotationX(60.0F);
        float[] arrayOfFloat2 = new float[2];
        arrayOfFloat2[0] = paramDepthLayout.getCustomShadowElevation();
        arrayOfFloat2[1] = (paramFloat2 * paramDepthLayout.getResources().getDisplayMetrics().density);
        ObjectAnimator localObjectAnimator3 = ObjectAnimator.ofFloat(paramDepthLayout, "CustomShadowElevation", arrayOfFloat2).setDuration(1000L);
        localObjectAnimator3.setInterpolator(new QuintInOut());
        localObjectAnimator3.setStartDelay(300 + paramInt * 2);
        localObjectAnimator3.start();
        paramDepthLayout.setCustomShadowElevation(paramFloat1 * paramDepthLayout.getResources().getDisplayMetrics().density);
        Property localProperty2 = View.SCALE_X;
        float[] arrayOfFloat3 = new float[2];
        arrayOfFloat3[0] = paramDepthLayout.getScaleX();
        arrayOfFloat3[1] = 1.0F;
        ObjectAnimator localObjectAnimator4 = ObjectAnimator.ofFloat(paramDepthLayout, localProperty2, arrayOfFloat3).setDuration(1000L);
        localObjectAnimator4.setInterpolator(new CircInOut());
        localObjectAnimator4.setStartDelay(paramInt + 300);
        localObjectAnimator4.start();
        paramDepthLayout.setScaleX(0.5F);
        Property localProperty3 = View.SCALE_Y;
        float[] arrayOfFloat4 = new float[2];
        arrayOfFloat4[0] = paramDepthLayout.getScaleY();
        arrayOfFloat4[1] = 1.0F;
        ObjectAnimator localObjectAnimator5 = ObjectAnimator.ofFloat(paramDepthLayout, localProperty3, arrayOfFloat4).setDuration(1000L);
        localObjectAnimator5.setInterpolator(new CircInOut());
        localObjectAnimator5.setStartDelay(paramInt + 300);
        localObjectAnimator5.start();
        paramDepthLayout.setScaleY(0.5F);
        Property localProperty4 = View.ROTATION;
        float[] arrayOfFloat5 = new float[2];
        arrayOfFloat5[0] = paramDepthLayout.getRotation();
        arrayOfFloat5[1] = 0.0F;
        ObjectAnimator localObjectAnimator6 = ObjectAnimator.ofFloat(paramDepthLayout, localProperty4, arrayOfFloat5).setDuration(1100L);
        localObjectAnimator6.setInterpolator(new QuintInOut());
        localObjectAnimator6.setStartDelay(paramInt);
        localObjectAnimator6.start();
        localObjectAnimator6.addListener(getShowStatusBarListener(paramDepthLayout));
        return localObjectAnimator5;
    }

    public static void startExitAnim(View paramView) {
        exitAnimate((DepthLayout) paramView.findViewById(R.id.root_dl), 0.0F, 30.0F, 15L, 190, true);
        exitAnimate((DepthLayout) paramView.findViewById(R.id.appbar), 15.0F, 20.0F, 30L, 170, true);
        exitAnimate((DepthLayout) paramView.findViewById(R.id.fab_container), 30.0F, 20.0F, 45L, 210, true);
        exitAnimate((DepthLayout) paramView.findViewById(R.id.dl2), 15.0F, 20.0F, 60L, 230, true);
        exitAnimate((DepthLayout) paramView.findViewById(R.id.dl3), 30.0F, 20.0F, 75L, 250, true);
        hideStatusBar(paramView);
    }

    public static void startIntroAnim(View paramView, AnimatorListenerAdapter paramAnimatorListenerAdapter) {
        introAnimate((DepthLayout) paramView.findViewById(R.id.root_dl), 0.0F, 30.0F, 15L, 180);
        introAnimate((DepthLayout) paramView.findViewById(R.id.appbar), 15.0F, 20.0F, 30L, 170);
        introAnimate((DepthLayout) paramView.findViewById(R.id.fab_container), 30.0F, 20.0F, 45L, 190);
        introAnimate((DepthLayout) paramView.findViewById(R.id.dl2), 15.0F, 20.0F, 60L, 200);
        introAnimate((DepthLayout) paramView.findViewById(R.id.dl3), 30.0F, 20.0F, 75L, 210).addListener(paramAnimatorListenerAdapter);
    }

    public static void startRevertFromMenu(View paramView, AnimatorListenerAdapter paramAnimatorListenerAdapter) {
        revertFromMenu((DepthLayout) paramView.findViewById(R.id.root_dl), 30.0F, 10, 0.0F);
        revertFromMenu((DepthLayout) paramView.findViewById(R.id.appbar), 20.0F, 0, 0.0F);
        revertFromMenu((DepthLayout) paramView.findViewById(R.id.fab_container), 20.0F, 20, 6.0F);
        revertFromMenu((DepthLayout) paramView.findViewById(R.id.dl2), 20.0F, 30, 1.0F);
        revertFromMenu((DepthLayout) paramView.findViewById(R.id.dl3), 20.0F, 40, 2.0F).addListener(paramAnimatorListenerAdapter);
        ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramView, View.TRANSLATION_Y, new float[]{0.0F}).setDuration(1100L);
        localObjectAnimator.setInterpolator(new QuintInOut());
        localObjectAnimator.start();
    }
}

