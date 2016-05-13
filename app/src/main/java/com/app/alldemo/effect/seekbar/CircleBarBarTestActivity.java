package com.app.alldemo.effect.seekbar;

import android.app.Activity;
import android.os.Bundle;

import com.app.alldemo.R;
import com.app.alldemo.courview.seekbar.CircularSeekBar;
import com.app.alldemo.utils.AppInfoUtils;
import com.app.alldemo.utils.Constant;

public class CircleBarBarTestActivity extends Activity {
    private CircularSeekBar music_player_seekbar;
    private boolean isMusicProgressFlah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_bar);
        findUI();
    }
    private void findUI(){
        Constant.SCREEN_WIDTH = AppInfoUtils.getInstance().getScreenWidth(this);
        Constant.SCREEN_HIGHT = AppInfoUtils.getInstance().getScreenHeight(this);
        Constant.SCREEN_DENSITY = AppInfoUtils.getInstance().getScreenDensity(this);
        music_player_seekbar = (CircularSeekBar)findViewById(R.id.music_player_seekbar);
        music_player_seekbar.setSeekBarChangeListener(new CircularSeekBar.OnSeekChangeListener() {

            @Override
            public void onProgressChange(CircularSeekBar musicBar, int newProgress) {
                isMusicProgressFlah=true;
                //计算音乐播放位置
            }
        });
        viewInit();
    }
    private void viewInit(){
        music_player_seekbar.setMaxProgress(100);
        music_player_seekbar.setProgress(0);
        music_player_seekbar.invalidate();
    }
}
