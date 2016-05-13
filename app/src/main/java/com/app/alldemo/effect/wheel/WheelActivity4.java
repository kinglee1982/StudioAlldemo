package com.app.alldemo.effect.wheel;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.courview.wheel.WheelScrollView;

import java.util.Arrays;

public class WheelActivity4 extends Activity {
    private static final String TAG="MainActivityTest";
    private final String[] PLANETS = new String[]{"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Uranus", "Neptune", "Pluto"};
    TextView textview;
    private String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel4);
        findUI();
    }
    private void findUI(){
        textview=(TextView)findViewById(R.id.main_show_dialog_btn);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wheel_dialog();
            }
        });
        text=PLANETS[0];
        textview.setText(text);
    }
    private void wheel_dialog(){
        View outerView = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
        WheelScrollView wv = (WheelScrollView) outerView.findViewById(R.id.wheel_view_wv);
        wv.setOffset(1);
        wv.setDisplayItemCount(3);
        wv.setItems(Arrays.asList(PLANETS));
        int positio=0;
        for(int i=0;i<PLANETS.length;i++){
            if(text.equals(PLANETS[i])){
                positio=i;
            }
        }
        wv.setSeletion(positio);
        wv.setOnWheelViewListener(new WheelScrollView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                Log.v(TAG, "item:" + item + "selectedIndex:" + selectedIndex);
                text = item;
            }
        });
        Builder builder = new Builder(this);
        builder.setTitle("WheelView in Dialog");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                textview.setText(text);
            }
        });
        AlertDialog nInfoDlg = builder.create();
        nInfoDlg.setCanceledOnTouchOutside(false);
        nInfoDlg.setView(outerView);
        nInfoDlg.show();
    }
}
