package com.app.alldemo.effect.seekbar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.utils.MyLog;
import com.app.alldemo.utils.ViewUtils;

import java.util.HashMap;
import java.util.Map;
public class AnminBarActivity extends Activity {
    private static final String TAG = "AnminBarActivity";
    private static final int Flashprogress = 300;
    EditText editext;
    Button button;
    ProgressBar progressBar;
    ImageView progressThum;
    private RelativeLayout tv_relay;
    private TextView tv_num;
    private int progressValue;
    private Handler mhandler = new Handler() {
        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Flashprogress:
                    Map<String, Object> haMap = (Map<String, Object>) msg.obj;
                    int thumBarLeft = (Integer) haMap.get("thumBarLeft");
                    int progress = (Integer) haMap.get("progress");
                    flashProgress(thumBarLeft, progress);
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anmin_bar);
        findUI();
    }
    private void findUI(){
        editext = (EditText) findViewById(R.id.editext);
        progressThum = (ImageView) findViewById(R.id.imageview);
        button = (Button) findViewById(R.id.button);
        progressBar = (ProgressBar) findViewById(R.id.preogress);
        tv_num = (TextView) findViewById(R.id.tv_num);
        tv_relay = (RelativeLayout) findViewById(R.id.tv_relay);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String textString = editext.getText().toString();
                int value = Integer.valueOf(textString);
                progressValue = value;
                int progressWidth = progressBar.getWidth();
                setProgressPosition(progressWidth);
            }
        });
    }
    private void flashProgress(int thumBarLeft, int progress) {
        LayoutParams thumParams = (LayoutParams) tv_relay.getLayoutParams();
        thumParams.leftMargin = thumBarLeft;
        tv_relay.setLayoutParams(thumParams);
        progressThum.setImageResource(R.drawable.face2);
        tv_num.setText(progress + "%");
        progressBar.setProgress(progress);
        progressThum.postInvalidate();
        int progressWidth = progressBar.getWidth();
        setProgressPosition(progressWidth);
    }

    private void setProgressPosition2(int progress) {
        int progressWidth = progressBar.getWidth();
        float progressWidthFloat = progressWidth;
        int thumBarLeft = (int) (progressWidthFloat / 100 * progress)
                + ViewUtils.getInstance().dp2px(this, 2f);
        MyLog.v(TAG, "progressWidth:" + progressWidth + "thumBarLeft:"
                + thumBarLeft);
        LayoutParams thumParams = (LayoutParams) tv_relay.getLayoutParams();
        thumParams.leftMargin = thumBarLeft;
        tv_relay.setLayoutParams(thumParams);
        progressThum.setImageResource(R.drawable.face2);
        tv_num.setText(progress + "%");
        progressBar.setProgress(progress);
        progressThum.postInvalidate();
    }

    private void setProgressPosition(final int progressWidth) {
        new Thread() {
            @Override
            public void run() {
                int oldProgress = progressBar.getProgress();
                if(oldProgress!=progressValue){
                    try {
                        Thread.sleep(50);
                        getLeftWidth(oldProgress,progressWidth);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
    private void getLeftWidth(int oldProgress,int progressWidth){
        int progress=0;
        if (oldProgress < progressValue) {
            progress = oldProgress + 1;
        }else{
            progress = oldProgress - 1;
        }
        float progressWidthFloat = progressWidth;
        int thumBarLeft = (int) (progressWidthFloat / 100 * progress)
                + ViewUtils.getInstance().dp2px(AnminBarActivity.this, 2f);
        Message msg = mhandler.obtainMessage();
        Map<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("thumBarLeft", thumBarLeft);
        hashMap.put("progress", progress);
        msg.obj = hashMap;
        msg.what = Flashprogress;
        mhandler.sendMessage(msg);
    }
}
