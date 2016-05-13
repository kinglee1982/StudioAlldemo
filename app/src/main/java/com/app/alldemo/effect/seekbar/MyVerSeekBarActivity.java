package com.app.alldemo.effect.seekbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.app.alldemo.R;
import com.app.alldemo.courview.seekbar.VocieProgressBar;
import com.app.alldemo.utils.AppInfoUtils;
import com.app.alldemo.utils.Constant;
import com.app.alldemo.utils.MyLog;
public class MyVerSeekBarActivity extends Activity {
    private static final String TAG="MyVerSeekBarActivity";
    private VocieProgressBar vocieProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Constant.SCREEN_WIDTH= AppInfoUtils.getInstance().getScreenWidth(this);
        Constant.SCREEN_HIGHT=AppInfoUtils.getInstance().getScreenHeight(this);
        Constant.SCREEN_DENSITY=AppInfoUtils.getInstance().getScreenDensity(this);
        setContentView(R.layout.activity_myver_seekbar);
        findUI();
    }
    private void findUI(){
        Button buton=(Button)findViewById(R.id.buton);
        vocieProgress=(VocieProgressBar)findViewById(R.id.vocie_progress);
        buton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                onclickVolum();
            }
        });
    }
    private void onclickVolum(){
        if(vocieProgress.getVisibility()==View.VISIBLE){
            MyLog.v(TAG, "隐藏");
            vocieProgress.setVisibility(View.GONE);
        }else if(vocieProgress.getVisibility()==View.GONE){
            MyLog.v(TAG, "显示");
            vocieProgress.setVisibility(View.VISIBLE);
        }
    }
}
