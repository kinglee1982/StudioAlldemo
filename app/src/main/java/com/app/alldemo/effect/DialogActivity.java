package com.app.alldemo.effect;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.app.alldemo.R;
import com.app.alldemo.courview.CustomDialog;
import com.app.alldemo.courview.DialogGradleView;
import com.app.alldemo.courview.DialogView;
import com.app.alldemo.courview.OnDialogCreateListener;
import com.app.alldemo.listenner.MyTagListenner;

public class DialogActivity extends Activity {
    private LinearLayout dialog_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        initProgressDialog();
        findUI();
    }
    private void findUI(){
        dialog_layout=(LinearLayout)findViewById(R.id.dialog_layout);
        Button button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogView.getInstance().avarClick(new MyTagListenner() {
                    @Override
                    public void onTagComplete(String values, Object object) {

                    }
                },DialogActivity.this,dialog_layout);
            }
        });
        Button button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogView.getInstance().customDialog(DialogActivity.this, new MyTagListenner() {
                    @Override
                    public void onTagComplete(String values, Object object) {

                    }
                });
            }
        });
        Button button3=(Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogView.getInstance().tv_data(DialogActivity.this, new MyTagListenner() {
                    @Override
                    public void onTagComplete(String values, Object object) {

                    }
                });
            }
        });
        Button button4=(Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogView.getInstance().dateRange(DialogActivity.this, new MyTagListenner() {
                    @Override
                    public void onTagComplete(String values, Object object) {

                    }
                });
            }
        });
        Button button5=(Button)findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressFragmetDialog.show();
            }
        });
        Button button6=(Button)findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogView.getInstance().getAroundDialog(DialogActivity.this).show();
            }
        });
        Button button7=(Button)findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogGradleTest();
            }
        });
    }
    private CustomDialog progressFragmetDialog;
    private void initProgressDialog() {
        progressFragmetDialog = CustomDialog.createDialog(this, new OnDialogCreateListener() {

            @Override
            public void onDialogCreate(CustomDialog dialog) {
                dialog.setContentView(R.layout.waiting_dialog);
            }
        });
    }
    private DialogGradleView dialogGradleView;
    private void dialogGradleTest(){
        if(dialogGradleView==null){
            dialogGradleView=new DialogGradleView(this);
        }
        dialogGradleView.show();
    }
}
