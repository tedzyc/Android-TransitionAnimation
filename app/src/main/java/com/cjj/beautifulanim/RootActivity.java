package com.cjj.beautifulanim;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.cjj.anim.MenuAnimation;
import com.cjj.anim.interpolators.ExpoIn;
import com.cjj.anim.interpolators.QuintOut;
import com.cjj.customview.CircularSplashView;


public class RootActivity extends Activity {
    Fragment currentFragment;
    int curretMenuIndex = 0;
    boolean isMenuVisible = false;
    ViewGroup menu;

    private void addMenuItem(ViewGroup paramViewGroup, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        ViewGroup localViewGroup = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.menu_item, paramViewGroup, false);
        ((TextView) localViewGroup.findViewById(R.id.item_text)).setText(paramString);
        CircularSplashView localCircularSplashView = (CircularSplashView) localViewGroup.findViewById(R.id.circle);
        localCircularSplashView.setSplash(BitmapFactory.decodeResource(getResources(), paramInt1));
        localCircularSplashView.setSplashColor(paramInt2);
        localViewGroup.setOnClickListener(getMenuItemCLick(paramInt4, paramInt2));
        if (paramInt4 == 0) {
            int j = (int) getResources().getDimension(R.dimen.menu_item_height_padding);
            paramViewGroup.addView(localViewGroup, new LayoutParams(-1, j + (int) getResources().getDimension(R.dimen.menu_item_height)));
            localViewGroup.setPadding(0, j, 0, 0);
        } else {
            localViewGroup.setBackground(getResources().getDrawable(paramInt3, null));
            if (paramInt4 == 3) {
                int i = (int) getResources().getDimension(R.dimen.menu_item_height_padding);
                paramViewGroup.addView(localViewGroup, new LayoutParams(-1, i + (int) getResources().getDimension(R.dimen.menu_item_height)));
                localViewGroup.setPadding(0, 0, 0, i);
            } else {
                paramViewGroup.addView(localViewGroup, new LayoutParams(-1, (int) getResources().getDimension(R.dimen.menu_item_height)));
            }
        }
    }

    private void fadeColoTo(int paramInt, TextView paramTextView) {
        ArgbEvaluator localArgbEvaluator = new ArgbEvaluator();
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(paramTextView.getCurrentTextColor());
        arrayOfObject[1] = Integer.valueOf(paramInt);
        ObjectAnimator localObjectAnimator = ObjectAnimator.ofObject(paramTextView, "TextColor", localArgbEvaluator, arrayOfObject);
        localObjectAnimator.setDuration(200L);
        localObjectAnimator.start();
    }

    private OnClickListener getMenuItemCLick(final int paramInt1, final int paramInt2) {
        return new OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (paramInt1 == RootActivity.this.curretMenuIndex) {
                    RootActivity.this.onBackPressed();
                }
                do {
                    if ((paramInt1 == 0) && (!(RootActivity.this.currentFragment instanceof WaterFragment))) {
                        ((MenuAnimation) RootActivity.this.currentFragment).exitFromMenu();
                        WaterFragment localWaterFragment = new WaterFragment();
                        localWaterFragment.setIntroAnimate(true);
                        RootActivity.this.goToFragment(localWaterFragment);
                        RootActivity.this.hideMenu();
                        RootActivity.this.selectMenuItem(paramInt1, paramInt2);
                        return;
                    }
                    if ((paramInt1 == 1) && (!(RootActivity.this.currentFragment instanceof WindFragment))) {
                        ((MenuAnimation) RootActivity.this.currentFragment).exitFromMenu();
                        WindFragment localWindFragment = new WindFragment();
                        localWindFragment.setIntroAnimate(true);
                        RootActivity.this.goToFragment(localWindFragment);
                        RootActivity.this.hideMenu();
                        RootActivity.this.selectMenuItem(paramInt1, paramInt2);
                        return;
                    }
                } while (paramInt1 != 2);
                showAboutDialog();
                RootActivity.this.onBackPressed();
            }
        };
    }

    private void showAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("cjjjjjjjjjjjjjjjjjjjjjjjjj");
        builder.setTitle("About");
        builder.show();
    }

    private void makeAppFullscreen() {
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(1280);
    }

    private void select(View paramView, int paramInt) {
        CircularSplashView localCircularSplashView = (CircularSplashView) paramView.findViewById(R.id.circle);
        localCircularSplashView.setScaleX(1.0F);
        localCircularSplashView.setScaleY(1.0F);
        localCircularSplashView.setVisibility(View.VISIBLE);
        localCircularSplashView.introAnimate();
        fadeColoTo(paramInt, (TextView) paramView.findViewById(R.id.item_text));
    }

    private void selectMenuItem(int paramInt1, int paramInt2) {
        for (int i = 0; i < this.menu.getChildCount(); i++) {
            View localView = this.menu.getChildAt(i);
            if (i == paramInt1) {
                select(localView, paramInt2);
            } else {
                unSelect(localView);
            }
        }
        this.curretMenuIndex = paramInt1;
    }

    private void setupMenu() {
        this.menu = ((ViewGroup) findViewById(R.id.menu_container));
        int i = getResources().getColor(R.color.splash1);
        addMenuItem(this.menu, "Naruto", R.drawable.splash1, i, R.drawable.menu_btn, 0);
        addMenuItem(this.menu, "OnePiece", R.drawable.splash2, getResources().getColor(R.color.splash2), R.drawable.menu_btn2, 1);
        addMenuItem(this.menu, "About", R.drawable.splash3, getResources().getColor(R.color.splash3), R.drawable.menu_btn3, 2);
        selectMenuItem(0, i);
        this.menu.setTranslationY(20000.0F);
    }

    private void unSelect(View paramView) {
        final View localView = paramView.findViewById(R.id.circle);
        localView.animate().scaleX(0.0F).scaleY(0.0F).setDuration(150L).withEndAction(new Runnable() {
            public void run() {
                localView.setVisibility(View.INVISIBLE);
            }
        }).start();
        fadeColoTo(-16777216, (TextView) paramView.findViewById(R.id.item_text));
    }

    public void goToFragment(Fragment paramFragment) {
        getFragmentManager().beginTransaction().add(R.id.fragment_container, paramFragment).commit();
        final Fragment localFragment = this.currentFragment;
        this.currentFragment = paramFragment;
        getWindow().getDecorView().postDelayed(new Runnable() {
            public void run() {
                RootActivity.this.getFragmentManager().beginTransaction().remove(localFragment).commit();
            }
        }, 2000L);
    }

    public void hideMenu() {
        this.isMenuVisible = false;
        ViewGroup localViewGroup = this.menu;
        Property localProperty = View.TRANSLATION_Y;
        float[] arrayOfFloat = new float[1];
        arrayOfFloat[0] = this.menu.getHeight();
        ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(localViewGroup, localProperty, arrayOfFloat);
        localObjectAnimator.setDuration(750L);
        localObjectAnimator.setInterpolator(new ExpoIn());
        localObjectAnimator.start();
    }

    public void onBackPressed() {
        if (this.isMenuVisible) {
            hideMenu();
            ((MenuAnimation) this.currentFragment).revertFromMenu();
            return;
        }
        super.onBackPressed();
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_root);
        makeAppFullscreen();
        if (paramBundle == null) {
            this.currentFragment = new WaterFragment();
            getFragmentManager().beginTransaction().add(R.id.fragment_container, this.currentFragment).commit();
        }
        setupMenu();
    }

    public void setCurretMenuIndex(int paramInt) {
        this.curretMenuIndex = paramInt;
    }

    public void showMenu() {
        this.isMenuVisible = true;
        ViewGroup localViewGroup = this.menu;
        Property localProperty = View.TRANSLATION_Y;
        float[] arrayOfFloat = new float[2];
        arrayOfFloat[0] = this.menu.getHeight();
        arrayOfFloat[1] = 0.0F;
        ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(localViewGroup, localProperty, arrayOfFloat);
        localObjectAnimator.setDuration(1000L);
        localObjectAnimator.setInterpolator(new QuintOut());
        localObjectAnimator.setStartDelay(150L);
        localObjectAnimator.start();
        selectMenuItem(this.curretMenuIndex, ((TextView) this.menu.getChildAt(this.curretMenuIndex).findViewById(R.id.item_text)).getCurrentTextColor());
        ((MenuAnimation) this.currentFragment).animateTOMenu();
    }
}

