package com.app.alldemo.effect;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.widget.VideoView;

import com.app.alldemo.R;
public class MyVideoActivity extends Activity {
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myvideo);
        findUI();
    }
    private void findUI(){
        videoView = (VideoView) findViewById(R.id.videoView);
        setpath();
        videoView.setOnPreparedListener(preparedListener);
    }
    public void setpath(){
        videoView.setVideoPath("android.resource://" + "com.app.alldemo" + "/" + R.raw.loadingmv);
    }
    private OnPreparedListener preparedListener = new OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            mp.start();
            mp.setLooping(true);
        }
    };
}
