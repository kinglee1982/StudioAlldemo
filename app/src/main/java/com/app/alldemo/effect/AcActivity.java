package com.app.alldemo.effect;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aigestudio.wheelpicker.view.WheelPicker;
import com.aigestudio.wheelpicker.widget.WheelConstellationPicker;
import com.aigestudio.wheelpicker.widget.WheelDatePicker;
import com.aigestudio.wheelpicker.widget.WheelDayPicker;
import com.aigestudio.wheelpicker.widget.WheelMonthPicker;
import com.aigestudio.wheelpicker.widget.WheelYearPicker;
import com.aigestudio.wheelpicker.widget.WheelZodiacPicker;
import com.app.alldemo.R;
import com.app.alldemo.courview.MainDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/31.
 */
public class AcActivity extends Activity {
    private WheelPicker picker;
    private Button straight,content,curved;
    private Button main_btn_year,main_btn_month,main_btn_day;
    private Button main_btn_date;
    private Button main_btn_zodiac,main_btn_constellation;
    private List<String> datas=new ArrayList<>();
    private String data;
    private MainDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_test);
        picker = (WheelPicker) findViewById(R.id.main_wheel_picker);
        straight=(Button)findViewById(R.id.straight);
        content=(Button)findViewById(R.id.content);
        curved=(Button)findViewById(R.id.curved);
        main_btn_year=(Button)findViewById(R.id.main_btn_year);
        main_btn_month=(Button)findViewById(R.id.main_btn_month);
        main_btn_day=(Button)findViewById(R.id.main_btn_day);
        main_btn_date=(Button)findViewById(R.id.main_btn_date);
        main_btn_zodiac=(Button)findViewById(R.id.main_btn_zodiac);
        main_btn_constellation=(Button)findViewById(R.id.main_btn_constellation);
        straight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker.setStyle(WheelPicker.STRAIGHT);
            }
        });
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AcActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });
        curved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker.setStyle(WheelPicker.CURVED);
            }
        });
        main_btn_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yearDialog();
            }
        });
        main_btn_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monthDialog();
            }
        });
        main_btn_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayDialog();
            }
        });
        main_btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialog();
            }
        });
        main_btn_zodiac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zodiacDialog();
            }
        });
        main_btn_constellation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constellationDialog();
            }
        });
        dialog = new MainDialog(this);
        pickerTest();
    }
    private void pickerTest(){
        picker.setOnWheelChangeListener(new WheelPicker.SimpleWheelChangeListener() {
            @Override
            public void onWheelScrollStateChanged(int state) {
                if (state == WheelPicker.SCROLL_STATE_IDLE) {

                } else if (state == WheelPicker.SCROLL_STATE_DRAGGING) {

                } else if (state == WheelPicker.SCROLL_STATE_SCROLLING) {

                }
            }

            @Override
            public void onWheelSelected(int index, String data) {
                AcActivity.this.data = data;
                Log.e("", "index:" + index + "data:" + data);
            }
        });
        getDatas();
        picker.setItemIndex(2);
        picker.setData(datas);
    }
    private void getDatas(){
        for(int i=0;i<20;i++){
            datas.add("测试数据"+i);
        }
    }
    private void yearDialog(){
        WheelYearPicker wheelYearPicker = new WheelYearPicker(this);
        wheelYearPicker.setBackgroundColor(0xFFF3F0E6);
        wheelYearPicker.setTextColor(0xFFAFB0CB);
        wheelYearPicker.setTextSize(getResources()
                .getDimensionPixelSize(R.dimen.TextSizeLarge));
        wheelYearPicker.setItemSpace(getResources()
                .getDimensionPixelOffset(R.dimen.ItemSpaceLarge));
        wheelYearPicker.setItemCount(11);
        dialog.setContentView(wheelYearPicker);
        dialog.show();
    }
    private void monthDialog(){
        dialog.setContentView(new WheelMonthPicker(this));
        dialog.show();
    }
    private void dayDialog(){
        dialog.setContentView(new WheelDayPicker(this));
        dialog.show();
    }
    private void dateDialog(){
        WheelDatePicker wheelDatePicker = new WheelDatePicker(this);
        wheelDatePicker.setBackgroundColor(0xFFF0DF98);
        wheelDatePicker.setTextColor(0xFF3F96C3);
        wheelDatePicker.setLabelColor(0xFF94A0B6);
        dialog.setContentView(wheelDatePicker);
        dialog.show();
    }
    private void zodiacDialog(){
        WheelZodiacPicker wheelZodiacPicker = new WheelZodiacPicker(this);
        wheelZodiacPicker.setBackgroundColor(0xFFF7B983);
        wheelZodiacPicker.setTextColor(0xFF7774B7);
        dialog.setContentView(wheelZodiacPicker);
        dialog.show();
    }
    private void constellationDialog(){
        WheelConstellationPicker wheelConstellationPicker = new WheelConstellationPicker(this);
        wheelConstellationPicker.setBackgroundColor(0xFFE4DDCC);
        wheelConstellationPicker.setItemSpace(getResources()
                .getDimensionPixelOffset(R.dimen.ItemSpaceLarge));
        wheelConstellationPicker.setTextColor(0xFF47474A);
        dialog.setContentView(wheelConstellationPicker);
        dialog.show();
    }
}
