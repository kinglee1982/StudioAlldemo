package com.app.alldemo.courview.wheel;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.app.alldemo.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lm.widget.PickerTextView;

/**
 * Created by limin on 15/11/12.
 */
public class TemperatureDialog extends CalBaseDialog implements PickerTextView.OnPositionChangeListener,
		View.OnClickListener {
	private PickerTextView picker1;
	private PickerTextView picker2;
	private List<String> picker1Values;
	private List<String> picker2Values;
	private int picker1Position;//从1开始
	private int picker2Position;//从1开始
	public TemperatureDialog(Context context,List<String> mPicker1Values,int mPicker1Position,List<String> mPicker2Values,int mPicker2Position
							 ,CalBaseDialog.Callback callback) {
		super(context, callback);
		this.picker1Values=mPicker1Values;
		this.picker1Position=mPicker1Position;
		this.picker2Values=mPicker2Values;
		this.picker2Position=mPicker2Position;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_wheel_two);
		picker1 = (PickerTextView) findViewById(R.id.picker1);
		picker2 = (PickerTextView) findViewById(R.id.picker2);
		picker1.setAdapter(new PickerTextView.Adapter() {
			@Override
			public int getCount() {
				return picker1Values.size();
			}

			@Override
			public String getString(int position) {
				return picker1Values.get(position);
			}
		});

		picker1.setOnPositionChangeListener(this);
		picker1.setPosition(picker1Position);

		picker2.setAdapter(new PickerTextView.Adapter() {
			@Override
			public int getCount() {
				return picker2Values.size();
			}

			@Override
			public String getString(int position) {
				return picker2Values.get(position);
			}
		});
		picker2.setOnPositionChangeListener(this);
		picker2.setPosition(picker2Position);
		findViewById(R.id.clear).setOnClickListener(this);
		findViewById(R.id.ok).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.clear:
		case R.id.ok:
			Map<String,Integer> hashMap=new HashMap<>();
			hashMap.put("picker1",picker1Position);
			hashMap.put("picker2",picker2Position);
			callback(hashMap);
			break;
		default:
			break;
		}
		dismiss();
	}

	@Override
	public void onPositionChange(PickerTextView sender, int position) {
		picker1Position=picker1.getPosition();
		picker2Position=picker2.getPosition();
	}
}
