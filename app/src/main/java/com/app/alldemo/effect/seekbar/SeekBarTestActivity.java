package com.app.alldemo.effect.seekbar;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.app.alldemo.R;
import com.app.alldemo.courview.seekbar.CircleBtmSeekBar;
import com.app.alldemo.courview.seekbar.CircleTopSeekBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
public class SeekBarTestActivity extends Activity {
    private static final String TAG="SeekBarTestActivity";
    CircleTopSeekBar mCircularSeekBar;
    CircleBtmSeekBar circularbtmseekbar;
    private Button button;
    private String url="http://img.xiami.net/images/album/img5/67605/676051433067605_5.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar_test);
        findUI();
    }
    private void findUI(){
        button=(Button)findViewById(R.id.button);
        mCircularSeekBar = (CircleTopSeekBar) findViewById(R.id.circularseekbar);
        circularbtmseekbar = (CircleBtmSeekBar) findViewById(R.id.circularbtmseekbar);
        mCircularSeekBar.setOnProgressChangedListener(new CircleTopSeekBar.OnProgressChangedListener() {

            @Override
            public void onProgressChanged(CircleTopSeekBar seekBar, int currentProgress,
                                          int maxProgress) {

            }

            @Override
            public void onDraging(CircleTopSeekBar seekBar, int currentProgress,
                                  int maxProgress) {

            }

            @Override
            public void onDragStop(CircleTopSeekBar seekBar, int currentProgress,
                                   int maxProgress) {
                Log.v(TAG, "currentProgress:" + currentProgress);
            }

            @Override
            public void onDragStart(CircleTopSeekBar seekBar, int currentProgress,
                                    int maxProgress) {

            }
        });
        mCircularSeekBar.setMaxProgress(100);
        circularbtmseekbar.setOnProgressChangedListener(new CircleBtmSeekBar.OnProgressChangedListener() {

            @Override
            public void onProgressChanged(CircleBtmSeekBar seekBar, int currentProgress,
                                          int maxProgress) {

            }

            @Override
            public void onDraging(CircleBtmSeekBar seekBar, int currentProgress,
                                  int maxProgress) {

            }

            @Override
            public void onDragStop(CircleBtmSeekBar seekBar, int currentProgress,
                                   int maxProgress) {
                Log.v(TAG, "currentProgress:"+currentProgress);
            }

            @Override
            public void onDragStart(CircleBtmSeekBar seekBar, int currentProgress,
                                    int maxProgress) {

            }
        });
        circularbtmseekbar.setMaxProgress(100);
        setBlurBg(url);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mCircularSeekBar.setCurrentProgress(20);
                circularbtmseekbar.setCurrentProgress(20);
            }
        });
    }
    private void setBlurBg(final String urlString) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                    InputStream in = url.openStream();
                    final Bitmap bitmap = BitmapFactory.decodeStream(in);
                    SeekBarTestActivity.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            mCircularSeekBar.setCenterImage(bitmap);
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                }
            }
        }).start();

    }
}
