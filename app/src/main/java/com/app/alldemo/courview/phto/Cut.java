package com.app.alldemo.courview.phto;

import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.Interpolator;

import com.app.alldemo.utils.MyLog;

/**
 * Created by Administrator on 2016/2/14.
 */
public class Cut {
    private final Interpolator mInterpolator;
    private int mDuration = 150;
    private boolean mFinished;
    private float mStartScaleX;
    private float mStartScaleY;
    private float mCurrScaleX;
    private float mCurrScaleY;
    private float mDeltaScaleX;
    private float mDeltaScaleY;
    private float mTotalDeltaScaleX;
    private float mTotalDeltaScaleY;
    private float mFinalScaleX;
    private float mFinalScaleY;
    private long mStartTime;
    private float mDurationReciprocal;
    private boolean scaleXEnlarge;
    private boolean scaleYEnlarge;

    public Cut(Context context) {
        this(context, null);
    }

    public Cut(Context context, Interpolator interpolator) {
        mFinished = true;
        if (interpolator == null) {
            mInterpolator = new LinearInterpolator();
        } else {
            mInterpolator = interpolator;
        }
    }

    public boolean computeScrollOffset() {
        if (mFinished) {
            return false;
        }
        int timePassed = (int) (AnimationUtils.currentAnimationTimeMillis() - mStartTime);

        if (timePassed < mDuration) {
            final float x = mInterpolator.getInterpolation(timePassed * mDurationReciprocal);
            final float tempScalex;
            if (scaleXEnlarge) {// x放大
                tempScalex = mStartScaleX * (1 + (x * mTotalDeltaScaleX));
            } else {
                tempScalex = mStartScaleX * (1 - (x * mTotalDeltaScaleX));
            }
            mDeltaScaleX = tempScalex / mCurrScaleX;
            MyLog.e("timePassed", mStartScaleX + "*" + x + "*" + mTotalDeltaScaleX + "   " + tempScalex + "/" + mCurrScaleX + "=" + mDeltaScaleX);
            mCurrScaleX = tempScalex;
            final float tempScaleY;
            if (scaleYEnlarge) {
                tempScaleY = mStartScaleY * (1 + (x * mTotalDeltaScaleY));
            } else {
                tempScaleY = mStartScaleY * (1 - (x * mTotalDeltaScaleY));
            }
            mDeltaScaleY = tempScaleY / mCurrScaleY;
            mCurrScaleY = tempScaleY;
        } else {
            mDeltaScaleX = mFinalScaleX / mCurrScaleX;
            mDeltaScaleY = mFinalScaleY / mCurrScaleY;
            mCurrScaleX = mFinalScaleX;
            mCurrScaleY = mFinalScaleY;
            mFinished = true;
        }
        return true;
    }


    /**
     * 等比缩放
     *
     * @param startScale 开始大小
     * @param goalScale  目标大小
     */
    public void startScale(float startScale, float goalScale) {
        startScale(startScale, goalScale, mDuration);
    }

    /**
     * 等比缩放
     *
     * @param startScale
     * @param goalScale
     * @param duration
     */
    public void startScale(float startScale, float goalScale, int duration) {
        startScale(startScale, startScale, goalScale, goalScale, duration);
    }

    /**
     * 非等比缩放
     *
     * @param startXScale
     * @param startYScale
     * @param goalXScale
     * @param goalYScale
     * @param duration
     */
    public void startScale(float startXScale, float startYScale, float goalXScale, float goalYScale, int duration) {
        mDuration = duration;
        mFinished = false;
        mStartScaleX = startXScale;
        mStartScaleY = startYScale;
        mFinalScaleX = goalXScale;
        mFinalScaleY = goalYScale;
        mCurrScaleX = startXScale;
        mCurrScaleY = startYScale;
        if (scaleXEnlarge = goalXScale > startXScale) {//放大
            mTotalDeltaScaleX = mFinalScaleX / mStartScaleX - 1;
        } else {
            mTotalDeltaScaleX = 1 - mFinalScaleX / mStartScaleX;
        }

        if (scaleYEnlarge = goalYScale > startYScale) {//放大
            mTotalDeltaScaleY = mFinalScaleY / mStartScaleY - 1;
        } else {
            mTotalDeltaScaleY = 1 - mFinalScaleY / mStartScaleY;
        }
        mDurationReciprocal = 1.0f / (float) mDuration;
        mStartTime = AnimationUtils.currentAnimationTimeMillis();
    }

    public final boolean isFinished() {
        return mFinished;
    }

    public final void forceFinished(boolean finished) {
        mFinished = finished;
    }

    public float getmCurrScaleX() {
        return mCurrScaleX;
    }

    public float getmCurrScaleY() {
        return mCurrScaleY;
    }

    /**
     * 离上次XScale的改变量
     *
     * @return
     */
    public float getmDeltaScaleX() {
        return mDeltaScaleX;
    }

    /**
     * 离上次YScale的改变量
     *
     * @return
     */
    public float getmDeltaScaleY() {
        return mDeltaScaleY;
    }
}
