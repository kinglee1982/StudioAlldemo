package com.app.alldemo.courview.wheel;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.app.alldemo.R;


/**
 * Created by limin on 15/11/17.
 */
public class CalBaseDialog extends Dialog {

	private static CalBaseDialog sDialog = null;

	public interface Callback {
		void onCallback(Object value);
	}

	protected final Callback mCallback;

	public CalBaseDialog(Context context, Callback callback) {
		super(context, R.style.Calendar_Control_dialog);
		mCallback = callback;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(sDialog != null) {
			sDialog.dismiss();
		}
		sDialog = this;
	}

	final protected void callback(Object value) {
		if(mCallback != null) {
			mCallback.onCallback(value);
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		sDialog = null;
	}
}
