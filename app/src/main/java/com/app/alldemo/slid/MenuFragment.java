package com.app.alldemo.slid;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.alldemo.R;


public class MenuFragment extends Fragment {
	private Button button;
	private SLMenuItemClickListener listener;
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		listener=(SLMenuItemClickListener)activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_menu, null);
		initView(view);
		return view;
	}
	private void initView(View view) {
		button=(Button)view.findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				listener.itemClick();
			}
		});
	}
	public interface SLMenuItemClickListener{
		public void itemClick();
	}
}
