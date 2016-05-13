package com.app.alldemo.animat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.app.alldemo.R;
import com.app.alldemo.utils.AnimaUtils;

/**
 * 动画说明AnimaUtils
 */
public class OtherAnimatActivity extends Activity {
    private ImageView pendulum,pendulum2,pendulum3,pendulum4;
    private ImageView tu_image,like_image,likepost_image,hard_image;
    private ImageView reward_image,like_image2;
    private RelativeLayout pendulum_relay4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_animat);
        findUI();
    }
    private void findUI(){
        pendulum=(ImageView)findViewById(R.id.pendulum);
        pendulum2=(ImageView)findViewById(R.id.pendulum2);
        pendulum3=(ImageView)findViewById(R.id.pendulum3);
        pendulum4=(ImageView)findViewById(R.id.pendulum4);
        tu_image=(ImageView)findViewById(R.id.tu_image);
        like_image=(ImageView)findViewById(R.id.like_image);
        likepost_image=(ImageView)findViewById(R.id.likepost_image);
        hard_image=(ImageView)findViewById(R.id.hard_image);
        reward_image=(ImageView)findViewById(R.id.reward_image);
        like_image2=(ImageView)findViewById(R.id.like_image2);
        pendulum_relay4=(RelativeLayout)findViewById(R.id.pendulum_relay4);
        pendulum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimaUtils.getInstance().pendulumAnima(OtherAnimatActivity.this, pendulum);
            }
        });
        pendulum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimaUtils.getInstance().pendulumAnima2(OtherAnimatActivity.this, pendulum2);
            }
        });
        pendulum3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimaUtils.getInstance().pendulumAnima2(OtherAnimatActivity.this, pendulum3);
            }
        });
        pendulum_relay4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pendulum4();
            }
        });
        tu_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimaUtils.getInstance().grid_in(OtherAnimatActivity.this, tu_image);
            }
        });
        like_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimaUtils.getInstance().likeAnima(like_image);
            }
        });
        likepost_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDelayedTest(likepost_image);
            }
        });
        like_image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimaUtils.getInstance().like_anim(OtherAnimatActivity.this, like_image2);
            }
        });
        hard_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimaUtils.getInstance().headAnima(hard_image);
            }
        });
        reward_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimaUtils.getInstance().reward(OtherAnimatActivity.this, reward_image);
            }
        });
    }
    private void postDelayedTest(final View view) {
        view.postDelayed(new Runnable() {

            @Override
            public void run() {
                AnimaUtils.getInstance().likeAnima(view);
            }
        }, 1000);
    }
    private void pendulum4(){
        pivotXValue=0.5f;
        pivotYValue=1.0f;
        fromDegrees=-8f;
        toDegrees=8f;
        pendulumAnimation1();
    }
    private long duration=500;
    private int repeatCount=3;
    private float fromDegrees;
    private float toDegrees;
    private float pivotXValue;
    private float pivotYValue;
    private void pendulumAnimation1(){
        RotateAnimation rotateAnimation=new RotateAnimation(0f,fromDegrees,Animation.RELATIVE_TO_SELF,pivotXValue,Animation.RELATIVE_TO_SELF,pivotYValue);
        rotateAnimation.setRepeatCount(0);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        rotateAnimation.setDuration(duration);
        rotateAnimation.setFillAfter(true);
        pendulum4.startAnimation(rotateAnimation);
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                pendulumAnimation2();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    private void pendulumAnimation2(){
        RotateAnimation rotateAnimation=new RotateAnimation(fromDegrees,toDegrees,Animation.RELATIVE_TO_SELF,pivotXValue,Animation.RELATIVE_TO_SELF,pivotYValue);
        rotateAnimation.setRepeatCount(repeatCount);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        rotateAnimation.setDuration(duration);
        rotateAnimation.setFillAfter(true);
        pendulum4.startAnimation(rotateAnimation);
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                updataPendulum();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    private void pendulumAnimation3(){
        RotateAnimation rotateAnimation=new RotateAnimation(fromDegrees,0f,Animation.RELATIVE_TO_SELF,pivotXValue,Animation.RELATIVE_TO_SELF,pivotYValue);
        rotateAnimation.setRepeatCount(0);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        rotateAnimation.setDuration(duration);
        rotateAnimation.setFillAfter(true);
        pendulum4.startAnimation(rotateAnimation);
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    private void updataPendulum(){
        if(fromDegrees==0){
            pendulumAnimation3();
        }else{
            if(fromDegrees!=0){
                fromDegrees=fromDegrees+1;
                toDegrees=toDegrees-1;
            }
            pendulumAnimation2();
        }
    }
}
