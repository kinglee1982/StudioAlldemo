package com.app.alldemo.effect.wheel;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.courview.wheel.NumberAdapter;
import com.app.alldemo.courview.wheel.TosAdapterView;
import com.app.alldemo.courview.wheel.WheelGalleryView;
import com.app.alldemo.courview.wheel.WheelTextView;

public class WheelActivity5 extends Activity {
    private WheelGalleryView mHours = null;
    private WheelGalleryView mMins = null;
    private String[] hoursArray = { "00", "01", "02", "03", "04", "05", "06",
            "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17",
            "18", "19", "20", "21", "22", "23" };
    private String[] minsArray = { "00", "01", "02", "03", "04", "05", "06",
            "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17",
            "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
            "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
            "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
            "51", "52", "53", "54", "55", "56", "57", "58", "59" };
    private NumberAdapter hourAdapter;
    private NumberAdapter minAdapter;
    private String text,mintext;
    private TextView sel_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel5);
        findUI();
    }
    private void findUI(){
        mHours = (WheelGalleryView) findViewById(R.id.wheel1);
        sel_password=(TextView)findViewById(R.id.sel_password);
        mHours.setScrollCycle(true);
        hourAdapter = new NumberAdapter(this,hoursArray);
        mHours.setAdapter(hourAdapter);
        int position=0;
        text=hoursArray[0];
        for(int i=0;i<hoursArray.length;i++){
            if(text.equals(hoursArray[i])){
                position=i;
            }
        }
        mHours.setSelection(position, true);
        ((WheelTextView) mHours.getSelectedView()).setTextSize(30);
        mHours.setOnItemSelectedListener(mListener);
        mHours.setUnselectedAlpha(0.5f);
        mMins = (WheelGalleryView) findViewById(R.id.wheel2);
        mMins.setScrollCycle(true);
        minAdapter = new NumberAdapter(this,minsArray);
        mMins.setAdapter(minAdapter);
        int minposition=0;
        mintext=minsArray[0];
        for(int i=0;i<minsArray.length;i++){
            if(mintext.equals(minsArray[i])){
                minposition=i;
            }
        }
        mMins.setSelection(minposition, true);
        ((WheelTextView) mMins.getSelectedView()).setTextSize(30);
        mMins.setOnItemSelectedListener(mListener2);
        mMins.setUnselectedAlpha(0.5f);
    }
    private TosAdapterView.OnItemSelectedListener mListener = new TosAdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(TosAdapterView<?> parent, View view,
                                   int position, long id) {
            ((WheelTextView) view).setTextSize(30);
            int index = Integer.parseInt(view.getTag().toString());
            int count = parent.getChildCount();
            if (index < count - 1) {
                ((WheelTextView) parent.getChildAt(index + 1)).setTextSize(25);
            }
            if (index > 0) {
                ((WheelTextView) parent.getChildAt(index - 1)).setTextSize(25);
            }
            text=hoursArray[position];
            sel_password.setText(text);
        }
        @Override
        public void onNothingSelected(TosAdapterView<?> parent) {

        }
    };
    private TosAdapterView.OnItemSelectedListener mListener2 = new TosAdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(TosAdapterView<?> parent, View view,
                                   int position, long id) {
            ((WheelTextView) view).setTextSize(30);
            int index = Integer.parseInt(view.getTag().toString());
            int count = parent.getChildCount();
            if (index < count - 1) {
                ((WheelTextView) parent.getChildAt(index + 1)).setTextSize(25);
            }
            if (index > 0) {
                ((WheelTextView) parent.getChildAt(index - 1)).setTextSize(25);
            }
            text=minsArray[position];
            sel_password.setText(text);
        }
        @Override
        public void onNothingSelected(TosAdapterView<?> parent) {

        }
    };
}
