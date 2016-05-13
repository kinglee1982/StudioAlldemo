package com.app.alldemo.courview;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.aigestudio.wheelpicker.view.IWheelPicker;
import com.aigestudio.wheelpicker.view.WheelPicker;
import com.app.alldemo.R;

/**
 * @author AigeStudio 2015-12-08
 */
public class MainDialog extends Dialog implements View.OnClickListener {
    private View root;
    private ViewGroup container;
    private Button btnObtain;

    private IWheelPicker picker;

    private String data;

    public MainDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCanceledOnTouchOutside(true);
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                container.removeAllViews();
            }
        });
        root = getLayoutInflater().inflate(R.layout.ac_main_dialog, null);
        container = (ViewGroup) root.findViewById(R.id.main_dialog_container);

        root.findViewById(R.id.btn_straight).setOnClickListener(this);
        btnObtain = (Button) root.findViewById(R.id.btn_obtain);
        btnObtain.setOnClickListener(this);
        root.findViewById(R.id.btn_curved).setOnClickListener(this);
    }

    @Override
    public void setContentView(View view) {
        if (view instanceof IWheelPicker) {
            picker = (IWheelPicker) view;
            picker.setOnWheelChangeListener(new WheelPicker.SimpleWheelChangeListener() {
                @Override
                public void onWheelScrollStateChanged(int state) {
                    if (state == WheelPicker.SCROLL_STATE_IDLE) {
                        btnObtain.setEnabled(true);
                    } else {
                        btnObtain.setEnabled(false);
                    }
                }

                @Override
                public void onWheelSelected(int index, String data) {
                    MainDialog.this.data = data;
                }
            });
        }
        container.addView(view);
        super.setContentView(root);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_straight:
                picker.setStyle(WheelPicker.STRAIGHT);
                break;
            case R.id.btn_obtain:
                Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_curved:
                picker.setStyle(WheelPicker.CURVED);
                break;
        }
    }
}