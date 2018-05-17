package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * Created by luisernesto on 24/04/18.
 */

public final class AnimationUtils {

    public static void slideDown(final View view) {
        view.animate()
                .translationY(view.getHeight())
                .alpha(0.f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        // superfluous restoration
                        view.setVisibility(View.GONE);
                        view.setAlpha(1.f);
                        view.setTranslationY(0.f);
                    }
                });
    }

    public static void slideUp(final View view) {
        view.setVisibility(View.VISIBLE);
        view.setAlpha(0.f);

        if (view.getHeight() > 0) {
            slideUpNow(view);
        } else {
            // wait till height is measured
            view.post(new Runnable() {
                @Override
                public void run() {
                    slideUpNow(view);
                }
            });
        }
    }

    public static void slideLeftClose(final View view) {
        view.animate()
                .translationX(view.getWidth())
                .alpha(0.f)
                .setDuration(1000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        // superfluous restoration
                        view.setVisibility(View.GONE);
                        view.setAlpha(1.f);
                        view.setTranslationX(0.f);
                    }
                });
    }

    public static void slideLeftOpen(final View view) {
        view.setVisibility(View.VISIBLE);
        view.setAlpha(0.f);

        if (view.getWidth() > 0) {
            slideUpNowRigth(view);
        } else {
            // wait till height is measured
            view.post(new Runnable() {
                @Override
                public void run() {
                    slideUpNowRigth(view);
                }
            });
        }
    }

    private static void slideUpNow(final View view) {
        view.setTranslationY(view.getHeight());
        view.animate()
                .translationY(0)
                .alpha(1.f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.VISIBLE);
                        view.setAlpha(1.f);
                    }
                });
    }


    private static void slideUpNowRigth(final View view) {
        view.setTranslationX(view.getWidth());
        view.animate()
                .translationX(0)
                .alpha(1.f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.VISIBLE);
                        view.setAlpha(1.f);
                    }
                });
    }

    public static void circularReveal(View parent, View view, int duration){
        parent.setVisibility(View.GONE);
        int x = view.getLeft() +
                (view.getWidth() / 2);
        int y = view.getTop() +
                (view.getHeight() / 2);

        int endRadius = (int) Math.hypot(parent.getWidth(), parent.getHeight());


        Animator anim = ViewAnimationUtils.
                createCircularReveal(parent, x, y, 0, endRadius);
        anim.setDuration(duration);
        parent.setVisibility(View.VISIBLE);
        anim.start();

    }

}