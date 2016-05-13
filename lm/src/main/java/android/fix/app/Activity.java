package android.fix.app;

import android.content.res.TypedArray;
import android.os.Bundle;

/**
 * Created by limin on 15/12/11.
 */
public abstract class Activity extends android.app.Activity {

	private int activityCloseEnterResId, activityCloseExitResId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		final int[] attrs = {
				android.R.attr.activityOpenEnterAnimation,
				android.R.attr.activityOpenExitAnimation,
				android.R.attr.activityCloseEnterAnimation,
				android.R.attr.activityCloseExitAnimation};

		TypedArray typedArray = obtainStyledAttributes(attrs);

		int activityOpenEnterResId = typedArray.getResourceId(0, 0);
		int activityOpenExitResId = typedArray.getResourceId(1, 0);

		activityCloseEnterResId = typedArray.getResourceId(2, 0);
		activityCloseExitResId = typedArray.getResourceId(3, 0);

		typedArray.recycle();

		if(activityOpenEnterResId != 0 || activityOpenExitResId != 0) {
			overridePendingTransition(activityOpenEnterResId, activityOpenExitResId);
		}
		super.onCreate(savedInstanceState);
	}

	@Override
	public void finish() {
		super.finish();
		if(activityCloseEnterResId != 0 || activityCloseExitResId != 0) {
			overridePendingTransition(activityCloseEnterResId, activityCloseExitResId);
		}
	}
}