package com.app.alldemo.courview.wheel;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.app.alldemo.R;

import java.util.List;

import lm.widget.PickerTextView;

/**
 * Created by limin on 15/11/11.
 */
public class LoveDialog extends CalBaseDialog implements PickerTextView.OnPositionChangeListener, View.OnClickListener {
	private List<String> mPickerTexts;

	private int mLove;
	private PickerTextView mPickerView;

	public LoveDialog(Context context, int love, List<String> pickerTexts,CalBaseDialog.Callback callback) {
		super(context, callback);
		this.mPickerTexts=pickerTexts;
		mLove = love;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_cal_love);

		mPickerView = (PickerTextView) findViewById(R.id.picker);
		mPickerView.setAdapter(new PickerTextView.Adapter() {
			@Override
			public int getCount() {
				return mPickerTexts.size();
			}

			@Override
			public String getString(int position) {
				return mPickerTexts.get(position);
			}
		});

		mPickerView.setOnPositionChangeListener(this);
		mPickerView.setPosition(mLove - 1);

		findViewById(R.id.clear).setOnClickListener(this);
		findViewById(R.id.ok).setOnClickListener(this);
	}

	@Override
	public void onPositionChange(PickerTextView sender, int position) {
		mLove = position + 1;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.clear:
		case R.id.ok:
			mLove = v.getId() == R.id.clear ? 0 : mPickerView.getPosition() + 1;
			callback(mLove);
			break;

		default:
			break;
		}
		dismiss();
	}
}
