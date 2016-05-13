package com.app.alldemo.courview;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.listenner.MyTagListenner;
import com.app.alldemo.utils.MyLog;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Administrator on 2015/11/9.
 */
public class DialogView {
    private static final String TAG="DialogView";
    private static DialogView instance;

    public static DialogView getInstance() {
        if (instance == null) {
            instance = new DialogView();
        }
        return instance;
    }

    public static final String success = "success";

    /**
     * 底部弹出框
     *
     * @param myTagListenner
     * @param ctx
     * @param parentView
     */
    public void avarClick(final MyTagListenner myTagListenner, Context ctx, View parentView) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.avar_dialog, null);
        final PopupWindow popupWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        //popu以为响应相应的触摸事件
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(ctx.getResources()));
        popupWindow.setAnimationStyle(android.R.style.Animation_InputMethod);
        popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
        LinearLayout phto_lineay = (LinearLayout) view.findViewById(R.id.phto_lineay);
        LinearLayout camra_lineay = (LinearLayout) view.findViewById(R.id.camra_lineay);
        LinearLayout delete_lineay = (LinearLayout) view.findViewById(R.id.delete_lineay);
        LinearLayout cancel_lineay = (LinearLayout) view.findViewById(R.id.cancel_lineay);
        phto_lineay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                myTagListenner.onTagComplete(success, 1);
            }
        });
        camra_lineay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                myTagListenner.onTagComplete(success, 2);
            }
        });
        delete_lineay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                myTagListenner.onTagComplete(success, 3);
            }
        });
        cancel_lineay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                popupWindow.dismiss();
            }
        });
    }

    public void customDialog(Activity activity,MyTagListenner myTagListenner) {
        final View layout = activity.getLayoutInflater().inflate(R.layout.dialog_item, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("标题");
        builder.setView(layout);
        builder.setPositiveButton("OK",
                new android.content.DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        EditText edtPID = (EditText) layout.findViewById(R.id.edtPID);
                    }
                });
        builder.setNegativeButton("cancel",
                new android.content.DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        // 表示
        builder.create().show();
    }

    /**
     * 系统的日期
     * @param context
     */
    public void tv_data(Context context,MyTagListenner myTagListenner){
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        DatePickerDialog dialog=new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                MyLog.v(TAG, "年" + year + "月" + monthOfYear + "日" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//		TimePickerDialog dialog = new TimePickerDialog(this,
//				new OnTimeSetListener() {
//
//					@Override
//					public void onTimeSet(TimePicker view, int hourOfDay,
//							int minute) {
//					}
//				}, calendar.get(Calendar.HOUR_OF_DAY), calendar
//						.get(Calendar.MINUTE), true);
        dialog.setCancelable(false);
        dialog.show();
    }
    public void dateRange(Activity activity,MyTagListenner myTagListenner){
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        final View layout = activity.getLayoutInflater().inflate(R.layout.my_datedialog_item, null);
        DatePicker my_date=(DatePicker)layout.findViewById(R.id.my_date);
        my_date.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String monString = String.valueOf(monthOfYear + 1);
                if (monString.length() == 1) {
                    monString = "0" + monString;
                }
                String dayString = String.valueOf(dayOfMonth);
                if (dayString.length() == 1) {
                    dayString = "0" + dayString;
                }
                String bufferString = year + monString + dayString;
                int historyDay = Integer.valueOf(bufferString);
                //控制范围，重新设置
                view.init(2000, 11, 30, this);
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("标题");
        builder.setView(layout);
        builder.setPositiveButton("OK",
                new android.content.DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.setNegativeButton("cancel",
                new android.content.DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        // 表示
        builder.create().show();
    }
    public Dialog getAroundDialog(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.around_dialog, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
        // main.xml中的ImageView
        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        // 加载动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                context, R.anim.around_anim);
        // 使用ImageView显示动画
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);
        tipTextView.setText(context.getString(R.string.loading));// 设置加载信息

        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
        loadingDialog.setCanceledOnTouchOutside(false);//空白部分不消除dialog

        loadingDialog.setCancelable(true);// 可以用“返回键”取消
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        return loadingDialog;
    }
}
