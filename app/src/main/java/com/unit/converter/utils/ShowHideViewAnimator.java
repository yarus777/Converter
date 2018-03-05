package com.unit.converter.utils;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.support.annotation.FloatRange;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Admin on 25.07.2017.
 * Утилита для анимирования view-компанент
 */
public class ShowHideViewAnimator {
    private long mAnimationDuration; //Длительность анимации
    private int mWidth; //Размер анимируемого View - ширина
    private int mHeight; //Размер анимируемого View - высота
    private int mToWidth; //До каких размеров сжимть по ширине
    private int mToHeight; //До каких размеров сжимть по высоте
    private float mFromAlpha; //С какого alpha
    private float mToAlpha; //До какого alpha
    private boolean mIsShown; //true - если view отображется полностью
    private LinearLayout.LayoutParams mStartParams;
    @Orientation
    private int mOrientation; //Ориентация анимации
    @AnimationType
    private int mAnimationType; //Тип анимации
    @AnimationDirection
    private int mAnimationDirection; //Направление движения
    private ValueAnimator mValueAnimator; //Аниматор
    private View mAnimatedView; //Ссылка на анимируемый View
    private OnAnimationChangeState mOnAnimationChangeState; //Callback

    /**
     * Конструктор без параметров
     */
    public ShowHideViewAnimator() {
        init();
    }

    /**
     * Конструктор с параметрами
     *
     * @param onAnimationChangeState Слушатель смены состояния
     */
    public ShowHideViewAnimator(OnAnimationChangeState onAnimationChangeState) {
        init();
        mOnAnimationChangeState = onAnimationChangeState;
    }

    /**
     * Начальная инициализация
     */
    private void init() {
        mAnimationDuration = 250L;
        mFromAlpha = 1.f;
        mIsShown = true;
        mOrientation = Orientation.VERTICAL;
        mAnimationType = AnimationType.SCALE_AND_ALPHA;
        mAnimationDirection = AnimationDirection.NONE;
    }

    /**
     * Метод для получения направления движения
     *
     * @return константа направления движения
     */
    public int getAnimationDirection() {
        return mAnimationDirection;
    }

    /**
     * Метод для установки направления движения
     *
     * @param mAnimationDirection константа направления движения
     */
    public ShowHideViewAnimator setAnimationDirection(@AnimationDirection int mAnimationDirection) {
        this.mAnimationDirection = mAnimationDirection;
        return this;
    }

    /**
     * Метод для получения типа анимации
     */
    public int getAnimation() {
        return mAnimationType;
    }

    /**
     * Метод для установки типа анимации
     *
     * @param animationType тип анимации
     */
    public void setAnimation(@AnimationType int animationType) {
        mAnimationType = animationType;
    }

    /**
     * Метод для получения ориентации анимации
     */
    public int getOrientation() {
        return mOrientation;
    }

    /**
     * Метод для установки ориентации анимации
     *
     * @param orientation ориентация
     */
    public void setOrientation(@Orientation int orientation) {
        mOrientation = orientation;
    }

    /**
     * Метод для установки начального значения alpha
     *
     * @param fromAlpha alpha
     */
    public void setFromAlpha(@FloatRange(from = 0.f, to = 1.f) float fromAlpha) {
        mFromAlpha = fromAlpha;
        if (mAnimatedView != null) {
            mAnimatedView.setAlpha(fromAlpha);
        }
    }

    /**
     * Метод для установки конечного значения alpha
     *
     * @param toAlpha alpha
     */
    public void setToAlpha(@FloatRange(from = 0.f, to = 1.f) float toAlpha) {
        mToAlpha = toAlpha;
        refreshAlpha();
    }

    /**
     * Метод для установки начального и конечного значений alpha
     *
     * @param fromAlpha Начальное alpha
     * @param toAlpha   Конечное alpha
     */
    public void setFromToAlpha(@FloatRange(from = 0.f, to = 1.f) float fromAlpha, @FloatRange(from = 0.f, to = 1.f) float toAlpha) {
        mFromAlpha = fromAlpha;
        mToAlpha = toAlpha;
    }

    /**
     * Метод для установки начальной ширины анимируемого view
     *
     * @param fullWidth начальная ширина анимируемого view
     */
    public void setWidth(@IntRange(from = 0) int fullWidth) {
        mWidth = fullWidth;
    }

    /**
     * Метод для установки начальной высоты анимируемого view
     *
     * @param fullHeight начальная высоты анимируемого view
     */
    public void setHeight(@IntRange(from = 0) int fullHeight) {
        mHeight = fullHeight;
    }

    /**
     * Метод для установки минимального значения ширины анимируемого view
     *
     * @param toWidth минимальное значения ширина анимируемого view
     */
    public void setToWidth(int toWidth) {
        mToWidth = toWidth;
    }

    /**
     * Метод для установки минимального значения высоты анимируемого view
     *
     * @param toHeight минимальное значения высоты анимируемого view
     */
    public void setToHeight(int toHeight) {
        mToHeight = toHeight;
    }

    /**
     * Метод для установки состояния анимированнного view
     *
     * @param isShown true - если видно, иначе false
     */
    public void setIsShown(boolean isShown) {
        mIsShown = isShown;
        if (mAnimatedView != null) {
            mAnimatedView.setLayoutParams(new ViewGroup.LayoutParams(mWidth, mHeight));
            mAnimatedView.setVisibility(isShown ? View.VISIBLE : View.GONE);
            refreshAlpha();
        }
    }

    /**
     * Метод для установки анимируемого View
     *
     * @param view анимируемый View
     */
    public ShowHideViewAnimator setAnimatedView(View view) {
//        mChildRefreshed = false;
        mAnimatedView = view;
        mStartParams = (LinearLayout.LayoutParams) mAnimatedView.getLayoutParams();
        mWidth = view.getMeasuredWidth();
        mHeight = view.getMeasuredHeight();
        return this;
    }

    /**
     * Метод для инициализации
     */
    public ShowHideViewAnimator initAllData() {
        if (mOrientation != Orientation.NONE) {
            setWidth(mAnimatedView.getMeasuredWidth());
            setHeight(mAnimatedView.getMeasuredHeight());
            setFromAlpha(1.f);
            if (mAnimationDuration == 0L) {
                mAnimationDuration = 250L;
            }
            initAnimation();
        }
        return this;
    }

    /**
     * Метод для инициализации
     *
     * @param orientation Ориентация
     */
    public ShowHideViewAnimator initAllData(@Orientation int orientation) {
        mOrientation = orientation;
        mAnimationDuration = 250L;
        return initAllData();
    }

    /**
     * Метод для инициализации
     *
     * @param orientation       Ориентация
     * @param animationDuration длительность анимации
     */
    public ShowHideViewAnimator initAllData(@Orientation int orientation, long animationDuration) {
        mOrientation = orientation;
        mAnimationDuration = animationDuration;
        return initAllData();
    }

    /**
     * Метод для инициализации
     *
     * @param orientation       Ориентация
     * @param animationDuration длительность анимации
     */
    public ShowHideViewAnimator initAllData(@Orientation int orientation, @AnimationType int animationType, long animationDuration) {
        mOrientation = orientation;
        mAnimationDuration = animationDuration;
        mAnimationType = animationType;
        return initAllData();
    }

    @Deprecated
    private void calculateViewSize() {
        if (mAnimatedView != null && mAnimatedView instanceof ViewGroup) {
            int height = 0;
            int width = 0;
            for (int i = 0; i < ((ViewGroup) mAnimatedView).getChildCount(); i++) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ((ViewGroup) mAnimatedView).getChildAt(i).getLayoutParams();
                width += ((ViewGroup) mAnimatedView).getChildAt(i).getWidth();
                height += ((ViewGroup) mAnimatedView).getChildAt(i).getMeasuredHeight();
            }
        }
    }

    /**
     * Метод для проверки валидных параметров
     */
    @SuppressLint("SwitchIntDef")
    private void checkValidParamsAnimation() {
        switch (mOrientation) {
            case Orientation.HORIZONTAL:
                switch (mAnimationDirection) {
                    case AnimationDirection.BOTTOM:
                    case AnimationDirection.TOP:
                        mAnimationDirection = AnimationDirection.NONE;
                }
                break;

            case Orientation.VERTICAL:
                switch (mAnimationDirection) {
                    case AnimationDirection.LEFT:
                    case AnimationDirection.RIGHT:
                        mAnimationDirection = AnimationDirection.NONE;
                }
                break;
        }
    }

    /**
     * Инициализация аниматора
     */
    private void initAnimation() {
        if (mValueAnimator == null) {
            mValueAnimator = new ValueAnimator();
            if (!mValueAnimator.isRunning()) {
                mValueAnimator.removeAllUpdateListeners();
                mValueAnimator.setFloatValues(0.f, 1.f);
                mValueAnimator.setDuration(mAnimationDuration);
                /*BounceInterpolator interpolator = new BounceInterpolator();

                mValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());*/
            }

            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @SuppressLint("SwitchIntDef")
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    if (mAnimatedView == null) {
                        mValueAnimator.cancel();
                        return;
                    }
                    if (mAnimatedView.getVisibility() == View.GONE || mAnimatedView.getVisibility() == View.INVISIBLE) {
                        mAnimatedView.setVisibility(View.VISIBLE);
                    }
                    mAnimatedView.refreshDrawableState();
                    int width = mWidth;
                    if (width == 0) {
                        width = mAnimatedView.getMeasuredWidth();
                    }
                    int height = mHeight;
                    if (width == 0) {
                        height = mAnimatedView.getMeasuredHeight();
                    }
                    float alpha;
                    float percent = (float) valueAnimator.getCurrentPlayTime() / (float) valueAnimator.getDuration();
//                    float percent = valueAnimator.getInterpolator().getInterpolation((float)valueAnimator.getAnimatedValue());
                    if (!mIsShown) {
                        percent = 1.f - percent;
                    }
                    float alphaPercent;

                    boolean isAlpha = AnimationType.ALPHA == mAnimationType;
                    if (mAnimationDirection != AnimationDirection.NONE) {
                        final LinearLayout.LayoutParams paraams = (LinearLayout.LayoutParams) mAnimatedView.getLayoutParams();
                        switch (mAnimationDirection) {
                            case AnimationDirection.LEFT:
                                if (!isAlpha) {
                                    mAnimatedView.setPivotX(0.f);
                                    mAnimatedView.setPivotY(0.f);
                                } else {
                                    int left = Math.round(percent * Math.abs(mWidth - mToWidth));
                                    paraams.setMargins(-left, 0, 0, 0);
                                }
                                break;
                            case AnimationDirection.TOP:
                                if (!isAlpha) {
                                    mAnimatedView.setPivotX(0.f);
                                    mAnimatedView.setPivotY(0.f);
                                } else {
                                    int top = Math.round(percent * Math.abs(mHeight - mToHeight));
                                    paraams.setMargins(0, -top, 0, 0);
                                }
                                break;
                            case AnimationDirection.RIGHT:
                                if (!isAlpha) {
                                    mAnimatedView.setPivotX(mWidth);
                                    mAnimatedView.setPivotY(0.f);
                                } else {
                                    int right = Math.round(percent * Math.abs(mWidth - mToWidth));
                                    paraams.setMargins(right, 0, 0, 0);
                                }
                                break;
                            case AnimationDirection.BOTTOM:
                                if (!isAlpha) {
                                    mAnimatedView.setPivotX(0.f);
                                    mAnimatedView.setPivotY(mHeight);
                                } else {
                                    int bottom = Math.round(percent * Math.abs(mHeight - mToHeight));
                                    paraams.setMargins(0, bottom, 0, 0);
                                }
                                break;
                        }
                        mAnimatedView.setLayoutParams(paraams);
                    }

                    switch (mAnimationType) {
                        case AnimationType.ALPHA:
                            alphaPercent = percent * Math.abs(mFromAlpha - mToAlpha);
                            alpha = 1.f - alphaPercent * mFromAlpha;
                            if (alpha > mFromAlpha) {
                                alpha = mFromAlpha;
                            } else if (alpha < 0.f) {
                                alpha = 0.f;
                            }
                            mAnimatedView.setAlpha(alpha);
                            break;
                        case AnimationType.SCALE_AND_ALPHA:
                            alphaPercent = percent * Math.abs(mFromAlpha - mToAlpha);
                            alpha = 1.f - alphaPercent * mFromAlpha;
                            if (alpha > mFromAlpha) {
                                alpha = mFromAlpha;
                            } else if (alpha < 0.f) {
                                alpha = 0.f;
                            }
                            mAnimatedView.setAlpha(alpha);
                        case AnimationType.SCALE:
                            switch (mOrientation) {
                                case Orientation.HORIZONTAL:
                                    width = getAnimatedViewWidth(percent);
                                    mAnimatedView.setScaleX(1.f - percent);
                                    mAnimatedView.setLayoutParams(new LinearLayout.LayoutParams(width, height));
                                    break;
                                case Orientation.HORIZ_AND_VERT:
                                    width = getAnimatedViewWidth(percent);
                                    mAnimatedView.setScaleX(1.f - percent);
                                case Orientation.VERTICAL:
                                    height = getAnimatedViewHeight(percent);
                                    mAnimatedView.setScaleY(1.f - percent);
                                    mAnimatedView.setLayoutParams(new LinearLayout.LayoutParams(width, height));
                                    break;
                            }
                            break;
                        case AnimationType.SIZE_AND_ALPHA:
                            alphaPercent = percent * Math.abs(mFromAlpha - mToAlpha);
                            alpha = 1.f - alphaPercent * mFromAlpha;
                            if (alpha > mFromAlpha) {
                                alpha = mFromAlpha;
                            } else if (alpha < 0.f) {
                                alpha = 0.f;
                            }
                            mAnimatedView.setAlpha(alpha);
                        case AnimationType.SIZE:
                            switch (mOrientation) {
                                case Orientation.VERTICAL:
                                    height = getAnimatedViewHeight(percent);
                                    mAnimatedView.setLayoutParams(new LinearLayout.LayoutParams(width, height));
                                    break;
                                case Orientation.HORIZ_AND_VERT:
                                    height = getAnimatedViewHeight(percent);
                                case Orientation.HORIZONTAL:
                                    width = getAnimatedViewWidth(1.f - percent);
                                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
                                    switch (mAnimationDirection) {
                                        case AnimationDirection.RIGHT:
                                            int left = mWidth - width;
                                            params.setMargins(left, 0, 0, 0);
                                            mAnimatedView.setLayoutParams(params);
                                            break;
                                    }
                                    mAnimatedView.setLayoutParams(params);
                                    break;
                            }
                            break;
                    }
                    if (valueAnimator.getDuration() <= valueAnimator.getCurrentPlayTime()) {
                        mIsShown = !mIsShown;

                        if (!mIsShown) {
                            mAnimatedView.setVisibility(View.GONE);
                        } else {
                            mAnimatedView.setVisibility(View.VISIBLE);
                        }

                        mAnimatedView.setLayoutParams(mStartParams);

                        if (mOnAnimationChangeState != null) {
                            mOnAnimationChangeState.onAnimationEnd(mIsShown);
                        }
                        mValueAnimator.cancel();
                    }
                }
            });
        }
    }

    /**
     * Метод для получения ширины view исходя из процента
     *
     * @param percent процент (0-1)
     * @return ширина view исходя из процента
     */
    private int getAnimatedViewWidth(float percent) {
        float widthPercent = percent * Math.abs(mWidth - mToWidth);
        int width = Math.round(widthPercent);
        if (width > mWidth) {
            width = mWidth;
        } else if (width < 0) {
            width = 0;
        }
        return width;
    }

    /**
     * Метод для получения высоты view исходя из процента
     *
     * @param percent процент (0-1)
     * @return высота view исходя из процента
     */
    private int getAnimatedViewHeight(float percent) {
        float heightPercent = percent * Math.abs(mHeight - mToHeight);
        int height = Math.round(heightPercent);
        if (height > mHeight) {
            height = mHeight;
        } else if (height < 0) {
            height = 0;
        }
        return (mHeight - height);
    }

    /**
     * Метод для запуска анимации
     *
     * @param isShow новое состояние View, true - если показывать, иначе false
     */
    public void startAnimation(boolean isShow) {
        if (mAnimatedView != null) {
            mAnimatedView.setLayoutParams(mStartParams);
            mIsShown = isShow;
            startAnimation();
        }
    }

    /**
     * Метод для проверки alpha
     */
    @SuppressLint("SwitchIntDef")
    private void refreshAlpha() {
        if (!mIsShown) {
            switch (mAnimationType) {
                case AnimationType.ALPHA:
                case AnimationType.SCALE_AND_ALPHA:
                    mAnimatedView.setAlpha(mToAlpha);
                    break;
            }
        }
    }

    /**
     * Метод для запуска анимации
     */
    public void startAnimation() {
        if (mValueAnimator == null) {
            initAnimation();
        }
        if (mAnimatedView != null && !mValueAnimator.isRunning()) {
            if (mWidth == 0 || mHeight == 0 || mAnimatedView.getVisibility() == View.GONE) {
                refreshAlpha();
                mAnimatedView.setVisibility(View.INVISIBLE);
                mAnimatedView.post(new Runnable() {
                    @SuppressLint("SwitchIntDef")
                    @Override
                    public void run() {
                        mHeight = mAnimatedView.getMeasuredHeight();
                        mWidth = mAnimatedView.getMeasuredWidth();
                        if (!mIsShown) {
                            switch (mOrientation) {
                                case Orientation.HORIZONTAL:
                                    mAnimatedView.setLayoutParams(new LinearLayout.LayoutParams(mWidth, mToHeight));
                                    break;
                                case Orientation.VERTICAL:
                                    mAnimatedView.setLayoutParams(new LinearLayout.LayoutParams(mToWidth, mHeight));
                                    break;
                                case Orientation.HORIZ_AND_VERT:
                                    mAnimatedView.setLayoutParams(new LinearLayout.LayoutParams(mToWidth, mToHeight));
                                    break;
                            }
                        }
                        if (mValueAnimator != null) {
                            checkValidParamsAnimation();
                            mValueAnimator.start();
                            if (mOnAnimationChangeState != null) {
                                mOnAnimationChangeState.onAnimationStart(!mIsShown);
                            }
                        }
                    }
                });
            } else {
                if (mValueAnimator != null) {
                    checkValidParamsAnimation();
                    mValueAnimator.start();
                    if (mOnAnimationChangeState != null) {
                        mOnAnimationChangeState.onAnimationStart(!mIsShown);
                    }
                }
            }
        }
    }

    /**
     * Константы с направлением движения анимации
     */
    @Retention(RetentionPolicy.SOURCE)
    @IntDef(flag = true, value = {Orientation.NONE, Orientation.HORIZONTAL, Orientation.VERTICAL, Orientation.HORIZ_AND_VERT})
    public @interface Orientation {
        int NONE = 0;
        int HORIZONTAL = 1;
        int VERTICAL = 2;
        int HORIZ_AND_VERT = 3;
    }

    /**
     * Константы для установки типа анимации
     */
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({AnimationType.SCALE, AnimationType.ALPHA, AnimationType.SIZE, AnimationType.SIZE_AND_ALPHA, AnimationType.SCALE_AND_ALPHA})
    public @interface AnimationType {
        int SCALE = 0;
        int ALPHA = 1;
        int SIZE = 2;
        int SIZE_AND_ALPHA = 3;
        int SCALE_AND_ALPHA = 4;
    }

    /**
     * Константы с направлением движения вложенных элементов
     */
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({AnimationDirection.NONE, AnimationDirection.LEFT, AnimationDirection.TOP, AnimationDirection.RIGHT, AnimationDirection.BOTTOM})
    public @interface AnimationDirection {
        int NONE = 0;
        int LEFT = 1;
        int TOP = 2;
        int RIGHT = 3;
        int BOTTOM = 4;
    }

    /**
     * Callback
     */
    public interface OnAnimationChangeState {
        /**
         * Метод для оповещения слушателя об окончании анимации
         *
         * @param isHide true - если анимируемая view скрыта, иначе false
         */
        void onAnimationEnd(boolean isHide);

        /**
         * Метод для оповещения слушателя о начале анимации
         *
         * @param isHiding true - если анимируемая view скрыта, иначе false
         */
        void onAnimationStart(boolean isHiding);
    }
}
