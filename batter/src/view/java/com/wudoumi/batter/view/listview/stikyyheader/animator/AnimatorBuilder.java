package com.wudoumi.batter.view.listview.stikyyheader.animator;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.animation.Interpolator;

import java.util.HashSet;
import java.util.Set;

public class AnimatorBuilder {

    public static final float DEFAULT_VELOCITY_ANIMATOR = 0.5f;

    private Set<AnimatorBundle> mSetAnimatorBundles;

    public AnimatorBuilder() {
        mSetAnimatorBundles = new HashSet<>(2);
    }

    private float mLastTranslationApplied = Float.NaN;

    public static AnimatorBuilder create() {
        return new AnimatorBuilder();
    }

    public AnimatorBuilder applyScale(View viewToScale, Rect finalRect) {
        return applyScale(viewToScale, finalRect, null);
    }


    public AnimatorBuilder applyScale(View viewToScale, Rect finalRect, Interpolator interpolator) {

        if (viewToScale == null) {
            throw new IllegalArgumentException("You passed a null view");
        }

        Rect from = buildViewRect(viewToScale);
        float scaleX = calculateScaleX(from, finalRect);
        float scaleY = calculateScaleY(from, finalRect);

        return applyScale(viewToScale, scaleX, scaleY, interpolator);
    }

    public AnimatorBuilder applyScale(View viewToScale, float scaleX, float scaleY) {
        return applyScale(viewToScale, scaleX, scaleY, null);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public AnimatorBuilder applyScale(View viewToScale, float scaleX, float scaleY, Interpolator interpolator) {

        if (viewToScale == null) {
            throw new IllegalArgumentException("You passed a null view");
        }

        boolean hasScaleAnimation = hasAnimation(viewToScale, AnimatorBundle.TypeAnimation.SCALEX, AnimatorBundle.TypeAnimation.SCALEXY);

        if (hasScaleAnimation) {
            throw new IllegalArgumentException("Scale animation already added");
        }

        float startScaleX = viewToScale.getScaleX();
        float startScaleY = viewToScale.getScaleY();

        if (scaleX == scaleY && startScaleX == startScaleY) {

            AnimatorBundle animatorScaleXY = AnimatorBundle.create(AnimatorBundle.TypeAnimation.SCALEXY, viewToScale, interpolator, startScaleX, scaleX);

            addAnimator(animatorScaleXY);

        } else {
            AnimatorBundle animatorScaleX = AnimatorBundle.create(AnimatorBundle.TypeAnimation.SCALEX, viewToScale, interpolator, startScaleX, scaleX);
            AnimatorBundle animatorScaleY = AnimatorBundle.create(AnimatorBundle.TypeAnimation.SCALEY, viewToScale, interpolator, startScaleY, scaleY);

            addAnimator(animatorScaleX, animatorScaleY);
        }

        adjustTranslation(viewToScale);

        return this;
    }

    public AnimatorBuilder applyTranslation(View viewToTranslate, Point finalPoint) {
        return applyTranslation(viewToTranslate, finalPoint, null);
    }

    /**
     * Translate the top-left point of the view to finalPoint
     */
    public AnimatorBuilder applyTranslation(View viewToTranslate, Point finalPoint, Interpolator interpolator) {

        if (viewToTranslate == null) {
            throw new IllegalArgumentException("You passed a null view");
        }

        final Point from = buildPointView(viewToTranslate);
        float translationX = calculateTranslationX(from, finalPoint);
        float translationY = calculateTranslationY(from, finalPoint);

        return applyTranslation(viewToTranslate, translationX, translationY, interpolator);
    }

    public AnimatorBuilder applyTranslation(View viewToTranslate, float translateX, float translateY) {
        return applyTranslation(viewToTranslate, translateX, translateY, null);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public AnimatorBuilder applyTranslation(View viewToTranslate, float translateX, float translateY, Interpolator interpolator) {

        if (viewToTranslate == null) {
            throw new IllegalArgumentException("You passed a null view");
        }

        float startTranslationX = viewToTranslate.getTranslationX();
        float startTranslationY = viewToTranslate.getTranslationY();

        AnimatorBundle animatorTranslationX = AnimatorBundle.create(AnimatorBundle.TypeAnimation.TRANSLATIONX, viewToTranslate, interpolator, startTranslationX, translateX);
        AnimatorBundle animatorTranslationY = AnimatorBundle.create(AnimatorBundle.TypeAnimation.TRANSLATIONY, viewToTranslate, interpolator, startTranslationY, translateY);

        addAnimator(animatorTranslationX, animatorTranslationY);

        adjustTranslation(viewToTranslate);

        return this;
    }

    public AnimatorBuilder applyFade(View viewToFade, float fade) {
        return applyFade(viewToFade, fade, null);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public AnimatorBuilder applyFade(View viewToFade, float fade, Interpolator interpolator) {

        if (viewToFade == null) {
            throw new IllegalArgumentException("You passed a null view");
        }

        float startAlpha = viewToFade.getAlpha();

        addAnimator(AnimatorBundle.create(AnimatorBundle.TypeAnimation.FADE, viewToFade, interpolator, startAlpha, fade));

        return this;
    }

    public AnimatorBuilder applyVerticalParallax(View viewToParallax) {
        return applyVerticalParallax(viewToParallax, DEFAULT_VELOCITY_ANIMATOR);
    }

    /**
     * @param viewToParallax
     * @param velocityParallax the velocity to apply to the view in order to show the parallax effect. choose a velocity between 0 and 1 for better results
     * @return
     */
    public AnimatorBuilder applyVerticalParallax(View viewToParallax, float velocityParallax) {

        if (viewToParallax == null) {
            throw new IllegalArgumentException("You passed a null view");
        }

        addAnimator(AnimatorBundle.create(AnimatorBundle.TypeAnimation.PARALLAX, viewToParallax, null, 0f, -velocityParallax));

        return this;
    }

    private void addAnimator(AnimatorBundle... animators) {

        boolean added = true;

        for (AnimatorBundle animator : animators) {
            added &= mSetAnimatorBundles.add(animator);
        }

        if (!added) {
            throw new IllegalArgumentException("Animation already added to this view");
        }

    }

    /**
     * called after a new scale or translation animation has been added
     */
    private void adjustTranslation(View viewAnimated) {

        AnimatorBundle animatorScaleX = null;
        AnimatorBundle animatorScaleY = null;
        AnimatorBundle animatorTranslationX = null;
        AnimatorBundle animatorTranslationY = null;

        for (AnimatorBundle animator : mSetAnimatorBundles) {

            if (viewAnimated != animator.mView) {
                continue;
            }

            switch (animator.mTypeAnimation) {
                case SCALEX:
                    animatorScaleX = animator;
                    break;
                case SCALEY:
                    animatorScaleY = animator;
                    break;
                case SCALEXY:
                    animatorScaleX = animator;
                    animatorScaleY = animator;
                    break;
                case TRANSLATIONX:
                    animatorTranslationX = animator;
                    break;
                case TRANSLATIONY:
                    animatorTranslationY = animator;
                    break;

            }
        }

        if (animatorTranslationX != null && animatorScaleX != null) {
            animatorTranslationX.mDelta = animatorTranslationX.mDelta + (animatorTranslationX.mView.getWidth() * (animatorScaleX.mDelta / 2f));
        }

        if (animatorTranslationY != null && animatorScaleY != null) {
            animatorTranslationY.mDelta = animatorTranslationY.mDelta + (animatorTranslationY.mView.getWidth() * (animatorScaleY.mDelta / 2f));
        }

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected void animateOnScroll(float boundedRatioTranslationY, float translationY) {

        if (mLastTranslationApplied == boundedRatioTranslationY) {
            return;
        }

        mLastTranslationApplied = boundedRatioTranslationY;

        for (AnimatorBuilder.AnimatorBundle animatorBundle : mSetAnimatorBundles) {

            float interpolatedTranslation = animatorBundle.mInterpolator == null ? boundedRatioTranslationY : animatorBundle.mInterpolator.getInterpolation(boundedRatioTranslationY);
            float valueAnimation = animatorBundle.mFromValue + (animatorBundle.mDelta * interpolatedTranslation);

            switch (animatorBundle.mTypeAnimation) {

                case SCALEX:
                    animatorBundle.mView.setScaleX(valueAnimation);
                    break;
                case SCALEY:
                    animatorBundle.mView.setScaleY(valueAnimation);
                    break;
                case SCALEXY:
                    animatorBundle.mView.setScaleX(valueAnimation);
                    animatorBundle.mView.setScaleY(valueAnimation);
                    break;
                case FADE:
                    animatorBundle.mView.setAlpha(valueAnimation); //TODO performance issues?
                    break;
                case TRANSLATIONX:
                    animatorBundle.mView.setTranslationX(valueAnimation);
                    break;
                case TRANSLATIONY:
                    animatorBundle.mView.setTranslationY(valueAnimation - translationY);
                    break;
                case PARALLAX:
                    animatorBundle.mView.setTranslationY(animatorBundle.mDelta * translationY);
                    break;

            }

        }

    }

    public boolean hasAnimatorBundles() {
        return mSetAnimatorBundles.size() > 0;
    }

    public static Rect buildViewRect(View view) {
        //TODO get coordinates related to the header
        return new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }

    public static Point buildPointView(View view) {
        return new Point(view.getLeft(), view.getTop());
    }

    public static float calculateScaleX(Rect from, Rect to) {
        return (float) to.width() / (float) from.width();
    }

    public static float calculateScaleY(Rect from, Rect to) {
        return (float) to.height() / (float) from.height();
    }

    public static float calculateTranslationX(Point from, Point to) {
        return to.x - from.x;
    }

    public static float calculateTranslationY(Point from, Point to) {
        return to.y - from.y;
    }

    private boolean hasAnimation(final View view, AnimatorBundle.TypeAnimation... typeAnimations) {

        for (AnimatorBundle animator : mSetAnimatorBundles) {
            if (animator.mView == view) {
                for (AnimatorBundle.TypeAnimation typeAnimation : typeAnimations) {
                    if (animator.mTypeAnimation == typeAnimation) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static class AnimatorBundle {

        public enum TypeAnimation {
            SCALEX, SCALEY, SCALEXY, FADE, TRANSLATIONX, TRANSLATIONY, PARALLAX
        }

        private float mFromValue;
        private float mDelta;
        private TypeAnimation mTypeAnimation;
        private View mView;
        private Interpolator mInterpolator;

        AnimatorBundle(TypeAnimation typeAnimation) {
            mTypeAnimation = typeAnimation;
        }

        public static AnimatorBundle create(AnimatorBundle.TypeAnimation typeAnimation, View view, Interpolator interpolator, float fromValue, float toValue) {
            AnimatorBundle animatorBundle = new AnimatorBundle(typeAnimation);

            animatorBundle.mView = view;
            animatorBundle.mFromValue = fromValue;
            animatorBundle.mDelta = toValue - fromValue;
            animatorBundle.mInterpolator = interpolator;

            return animatorBundle;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AnimatorBundle that = (AnimatorBundle) o;
            return mView == that.mView && mTypeAnimation == that.mTypeAnimation;
        }

        @Override
        public int hashCode() {
            int result = mTypeAnimation.hashCode();
            result = 31 * result + mView.hashCode();
            return result;
        }
    }


}
