package com.app.alldemo.effect.wheel;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.courview.wheel.ArrayWheelAdapter;
import com.app.alldemo.courview.wheel.WheelView;
import com.app.alldemo.listenner.OnWheelChangedListener;

public class WheelActivity2 extends Activity {
    private WheelView shengWV = null;
    private WheelView wheel_test;
    private TextView textview;
    private String[] shengs = { "-500", "-450", "-400", "-350", "-300","-250","-200","-150","-100","-50","0"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel2);
        findUI();
        findWheel();
        getDatas();
    }
    private void findUI(){
        textview=(TextView)findViewById(R.id.textview);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(textview);
            }
        });
    }
    private void findWheel(){
        shengWV = (WheelView) findViewById(R.id.place_sheng);
        shengWV.setVisibleItems(5);
        shengWV.setCyclic(true);
        shengWV.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String sheng = shengs[newValue];
            }
        });
    }
    private void findWheelTest(View view){
        wheel_test = (WheelView)view.findViewById(R.id.wheel_test);
        wheel_test.setVisibleItems(5);
        wheel_test.setCyclic(true);
        wheel_test.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String oldsheng = shengs[oldValue];
                String newsheng = shengs[newValue];
                Log.v("test", "oldsheng:" + oldsheng + "newsheng:" + newsheng);
            }
        });
        wheel_test.setAdapter(new ArrayWheelAdapter<String>(shengs));
        wheel_test.setCurrentItem(2);
    }
    private void getDatas() {
        shengWV.setAdapter(new ArrayWheelAdapter<String>(shengs));
    }
    private void dialog(View v){
        final AlertDialog nInfoDlg = new AlertDialog.Builder(this).create();
        nInfoDlg.setCanceledOnTouchOutside(true);
        View view = View.inflate(this, R.layout.wheel_dialog, null);
        findWheelTest(view);
        nInfoDlg.setView(view);
        nInfoDlg.show();
    }
}
