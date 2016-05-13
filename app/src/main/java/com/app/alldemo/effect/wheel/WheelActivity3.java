package com.app.alldemo.effect.wheel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.courview.wheel.NumberAdapter;
import com.app.alldemo.courview.wheel.TosAdapterView;
import com.app.alldemo.courview.wheel.WheelGalleryView;
import com.app.alldemo.courview.wheel.WheelTextView;

public class WheelActivity3 extends Activity {
    private TextView textview;
    private WheelGalleryView mHours = null;
    private NumberAdapter hourAdapter;
    private String[] hoursArray = { "00", "01", "02", "03", "04", "05", "06",
            "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17",
            "18", "19", "20", "21", "22", "23" };
    private String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel3);
        findUI();
    }
    private void findUI(){
        textview=(TextView)findViewById(R.id.textview);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(v);
            }
        });
        text=hoursArray[0];
        textview.setText(text);
    }
    private void dialog(View v){
        final AlertDialog nInfoDlg = new AlertDialog.Builder(this).create();
        nInfoDlg.setCanceledOnTouchOutside(true);
        nInfoDlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
        View view = View.inflate(this, R.layout.wheeltest_dialog, null);
        findWheelTest(view);
        nInfoDlg.setView(view);
        nInfoDlg.show();
    }
    private void findWheelTest(View view){
        mHours = (WheelGalleryView) view.findViewById(R.id.wheel1);
        mHours.setScrollCycle(true);
        hourAdapter = new NumberAdapter(this,hoursArray);
        mHours.setAdapter(hourAdapter);
        int position=0;
        for(int i=0;i<hoursArray.length;i++){
            if(text.equals(hoursArray[i])){
                position=i;
            }
        }
        mHours.setSelection(position, true);
        ((WheelTextView) mHours.getSelectedView()).setTextSize(30);
        mHours.setOnItemSelectedListener(mListener);
        mHours.setUnselectedAlpha(0.5f);
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
            textview.setText(text);
        }
        @Override
        public void onNothingSelected(TosAdapterView<?> parent) {

        }
    };
}
