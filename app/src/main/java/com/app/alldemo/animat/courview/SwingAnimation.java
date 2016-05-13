package com.app.alldemo.animat.courview;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;

/**
 * Created by limin on 2016-02-18.
 */
public class SwingAnimation extends Animation implements Interpolator {
	private final static double HALF_PI = Math.PI * 0.5;
	private final static double CYCLE = Math.PI * 2;

	private double mEnd;

	private float mPivotX, mPivotY;

	private float mPivotXValue, mPivotYValue;

	private float mMaxDegree;

	/**
	 *
	 * @param count 摆多少次到停
	 * @param position
	 * @param maxDegree 最大角度值正数
	 * @param pivotXValue 摆动中心x
	 * @param pivotYValue
	 */
	public SwingAnimation(int count, Position position, float maxDegree, float pivotXValue, float pivotYValue) {
		count=count<=0 ? 1: count;
		double offset;
		switch(position) {
		case MAX:
			offset = -HALF_PI;
			break;

		case MIN:
			offset = HALF_PI;
			break;

		default:
			offset = 0;
			break;
		}

		mEnd = CYCLE * count + offset;

		mPivotXValue = pivotXValue;
		mPivotYValue = pivotYValue;
		mMaxDegree = maxDegree;
	}

	@Override
	public float getInterpolation(float input) {
		return (float) (Math.sin(input * mEnd) * (1 - input));
	}

	@Override
	public void setInterpolator(Interpolator i) {
	}

	@Override
	public void setInterpolator(Context context, int resID) {
	}

	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		//减速
//		float degrees = (float) ((Math.sin(interpolatedTime * mEnd) * (1.0f - interpolatedTime) * (1.0f - interpolatedTime)) * mMaxDegree);
		//匀速
		float degrees = (float) ((Math.sin(interpolatedTime * mEnd) * (1.0f - interpolatedTime)) * mMaxDegree);
		System.out.println("Degree: " + degrees);
		float scale = getScaleFactor();
		if (mPivotX == 0.0f && mPivotY == 0.0f) {
			t.getMatrix().setRotate(degrees);
		} else {
			t.getMatrix().setRotate(degrees, mPivotX * scale, mPivotY * scale);
		}
		super.applyTransformation(interpolatedTime, t);
	}

	@Override
	public void initialize(int width, int height, int parentWidth, int parentHeight) {
		super.initialize(width, height, parentWidth, parentHeight);
		mPivotX = resolveSize(RELATIVE_TO_SELF, mPivotXValue, width, parentWidth);
		mPivotY = resolveSize(RELATIVE_TO_SELF, mPivotYValue, height, parentHeight);
	}

	public enum Position {
		MAX,
		MIN,
		ZERO
	}
}
