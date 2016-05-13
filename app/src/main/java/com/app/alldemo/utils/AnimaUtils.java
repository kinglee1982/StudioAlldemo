package com.app.alldemo.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;

import com.app.alldemo.R;

/**
 * 基本动画描述
 * 无限旋转
 * 摇晃动画
 * 点赞效果
 * 心跳动画
 * 钟摆动画
 * 图片的突出缩进效果
 * 放大的动画
 * 喜欢动画
 */
public class AnimaUtils {
    private static AnimaUtils instance;
    public static AnimaUtils getInstance() {
        if (instance == null) {
            instance = new AnimaUtils();
        }
        return instance;
    }
    /**
     * 基本动画描述
     * @param view
     * @param context
     */
    private void anmationDescript(View view,Context context){
        android.view.animation.AnimationUtils.loadAnimation(context, R.anim.anmation_descript);
        //调用方式
        view.startAnimation(android.view.animation.AnimationUtils.loadAnimation(context,
                R.anim.anmation_descript));
        //调用方式2
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.1f, 1.0f,
                1.1f);
        scaleAnimation.setDuration(100);
        view.startAnimation(scaleAnimation);
    }
    /**
     * 无限旋转
     */
    public void roteCycle(View view,Context context) {
        view.startAnimation(android.view.animation.AnimationUtils.loadAnimation(context, R.anim.rotecycle));
    }
    /**
     * 摇晃动画
     */
    public void shake(View view,Context context) {
        view.startAnimation(android.view.animation.AnimationUtils.loadAnimation(context, R.anim.shake));
    }

    /**
     * 点赞效果
     * @param targetView
     */
    public void likeAnima(View targetView) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.1f, 1.0f,
                1.1f);
        scaleAnimation.setDuration(100);
        targetView.startAnimation(scaleAnimation);
    }

    /**
     * 心跳动画
     * @param view
     */
    public void headAnima(final View view){
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(new ScaleAnimation(1.0f, 1.3f, 1.0f, 1.3f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f));
        animationSet.addAnimation(new AlphaAnimation(1.0f, 0.8f));

        animationSet.setDuration(500);
        animationSet.setInterpolator(new AccelerateInterpolator());
        animationSet.setFillAfter(true);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                /**
                 * 缩小动画
                 */
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.addAnimation(new ScaleAnimation(1.3f, 1.0f, 1.3f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f));
                animationSet.addAnimation(new AlphaAnimation(0.8f, 1.0f));
                animationSet.setDuration(600);
                animationSet.setInterpolator(new DecelerateInterpolator());
                animationSet.setFillAfter(false);
                view.startAnimation(animationSet);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animationSet);
    }
    /**
     * 钟摆动画
     * @param context
     * @param view
     */
    public void pendulumAnima(Context context,View view) {
        Animation shake = AnimationUtils.loadAnimation(context,
                R.anim.pendulum_anim);
        shake.setFillAfter(true);
        view.startAnimation(shake);
    }
    /**
     * 钟摆动画
     * @param context
     * @param view
     */
    public void pendulumAnima2(Context context,View view) {
        Animation shake = AnimationUtils.loadAnimation(context,
                R.anim.pendulum_anim2);
        shake.setFillAfter(true);
        view.startAnimation(shake);
    }

    /**
     * 图片的突出缩进效果
     * @param context
     * @param view
     */
    public void grid_in(Context context,View view) {
        Animation shake = AnimationUtils.loadAnimation(context,
                R.anim.grid_in);
        view.startAnimation(shake);
    }

    /**
     * 放大的动画
     * @param context
     * @param view
     */
    public void reward(Context context,View view){
        Animation anim=AnimationUtils.loadAnimation(context,R.anim.reward_launcher);
        view.startAnimation(anim);
    }

    /**
     * 喜欢动画
     * @param context
     * @param view
     */
    public void like_anim(Context context,View view){
        Animation likeAnimation =AnimationUtils.loadAnimation(context,R.anim.private_message_launcher);
        view.startAnimation(likeAnimation);
    }
}
