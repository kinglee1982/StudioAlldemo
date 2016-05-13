package lm.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by limin on 16/01/05.
 */
public abstract class ViewToast {
	private final Context mContext;

	private final Handler mHandler;

	private final WindowManager mWM;

	private final WindowManager.LayoutParams mParams;

	private View mRootView;

	private boolean isShowing;

	public ViewToast(Context context) {
		mContext = context;
		mHandler = new Handler(Looper.myLooper());
		mWM = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		mRootView = onCreateView(LayoutInflater.from(mContext));
		mParams = new WindowManager.LayoutParams();

		onViewCreated(mRootView);
	}

	protected abstract View onCreateView(LayoutInflater inflater);

	protected void onViewCreated(View view) {
	}

	protected void onConfigAttributes(WindowManager.LayoutParams attrs) {
	}

	public int getDuration() {
		return 2000;
	}

	protected void onShow() {
	}

	protected void onCancel() {
	}

	private Runnable mShowRunnable = new Runnable() {
		@Override
		public void run() {
			mWM.addView(mRootView, mParams);
			onShow();
		}
	};

	private Runnable mCancelRunnable = new Runnable() {
		@Override
		public void run() {
			mWM.removeView(mRootView);
			onCancel();
		}
	};

	private Runnable mCancelTask = new Runnable() {
		@Override
		public void run() {
			cancel();
		}
	};

	final public void show() {
		synchronized(this) {
			if(!isShowing) {
				onConfigAttributes(mParams);
				mHandler.post(mShowRunnable);
				isShowing = true;
				mHandler.postDelayed(mCancelTask, getDuration());
			}
		}
	}

	final public void cancel() {
		synchronized(this) {
			if(isShowing) {
				mHandler.post(mCancelRunnable);
				isShowing = false;
			}
		}
	}

	final public String getString(int resId) {
		return mContext.getResources().getString(resId);
	}

	public boolean isShowing() {
		return isShowing;
	}
}
