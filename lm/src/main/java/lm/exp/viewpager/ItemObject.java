package lm.exp.viewpager;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Created by limin on 16/01/07.
 */
public abstract class ItemObject {
	private Context mContext;
	private View mRootView;
	private PageData mPageData;

	private boolean isAttach;

	public ItemObject(Context context) {
		mContext = context;
		mRootView = onCreateView();
		onViewCreated(mRootView);
	}

	protected abstract View onCreateView();

	protected abstract void onBind(PageData data);

	protected void onViewCreated(View view) {}

	public final Context getContext() {
		return mContext;
	}

	public final Resources getResources() {
		return mContext.getResources();
	}

	public final DisplayMetrics getDisplayMetrics() {
		return getResources().getDisplayMetrics();
	}

	public final View getView() {
		return mRootView;
	}

	final void bind(PageData data) {
		if(mPageData != null) {
			mPageData.unregisterObserver(mDataObserver);
			mPageData = null;
		}

		if(data != null) {
			mPageData = data;
			mPageData.registerObserver(mDataObserver);
			onBind(data);
		}
	}

	final synchronized void attach(ViewGroup container) {
		if(!isAttach) {
			ViewParent parent = mRootView.getParent();
			if(parent != null && parent.equals(container)) {
				return;
			}

			if(parent != null && parent instanceof ViewGroup) {
				((ViewGroup) parent).removeView(mRootView);
			}

			container.addView(mRootView, new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.MATCH_PARENT));
			onAttach();
			isAttach = true;
		}
		else {
			throw new Error("This object has been attach!");
		}
	}

	protected void onAttach() {}

	final synchronized void detach() {
		if(isAttach) {
			ViewParent parent = mRootView.getParent();
			if(parent != null && parent instanceof ViewGroup) {
				((ViewGroup) parent).removeView(mRootView);
				onDetach();
				isAttach = false;
			}
		}
		else {
			throw new Error("This object has been detach!");
		}
	}

	protected void onDetach() {}

	protected void onDataChange() {}

	private final DataSetObserver mDataObserver = new DataSetObserver() {
		@Override
		public void onChanged() {
			final PageData data = mPageData;
			if(data != null) {
				onDataChange();
			}
		}

		@Override
		public void onInvalidated() {
		}
	};
}
