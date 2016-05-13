package com.app.alldemo.animat;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.app.alldemo.R;
import com.app.alldemo.utils.MyLog;

/**
 * 动画篇
 */
public class AnimationDescriptActivity extends Activity {
    private Button translate_button,fram_button;
    private ImageView translate_imag,fram_imag;
    private AnimationDrawable rocketAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_descript);
        translate_button=(Button)findViewById(R.id.translate_button);
        fram_button=(Button)findViewById(R.id.fram_button);
        translate_imag=(ImageView)findViewById(R.id.translate_imag);
        fram_imag=(ImageView)findViewById(R.id.fram_imag);
        fram_imag.setBackgroundResource(R.drawable.fram_animate);
        rocketAnimation=(AnimationDrawable)fram_imag.getBackground();
        translate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation(translate_imag);
            }
        });
        fram_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fram();
            }
        });
    }

    /**
     * 调用方式
     */
    private void animation(View view){
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anmation_descript));
    }
    /**
     * 调用方式2
     */
    private void animation2(View view){
        AnimationSet animationSet=new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 1.0f);
        animationSet.addAnimation(translateAnimation);
        view.setAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                MyLog.e("","重复执行的调用");
            }
        });
    }
    private void fram(){
        if(rocketAnimation!=null){
            rocketAnimation.start();
        }
    }
}
