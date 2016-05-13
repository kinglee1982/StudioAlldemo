package com.app.alldemo.courview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.app.alldemo.R;

/**
 * Created by Administrator on 2016/2/14.
 */
public class DialogGradleView extends Dialog{
    protected Activity context;
    public DialogGradleView(Context context){
        super(context);
        this.context=(Activity)context;
        initView();
    }
    void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_gradle, null);
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        view.setMinimumWidth(display.getWidth());
        setCanceledOnTouchOutside(true);
        setContentView(view, new WindowManager.LayoutParams(-1, -1));
        windowDeploy();
    }
    //设置窗口显示
    public void windowDeploy() {
        Window window = getWindow(); //得到对话框
        window.setWindowAnimations(R.style.dialogWindowAnim); //设置窗口弹出动画
        window.setBackgroundDrawableResource(R.color.tw__transparent); //设置对话框背景为透明
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.gravity = Gravity.BOTTOM;
        window.setAttributes(wl);
    }
}
