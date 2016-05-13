package com.app.alldemo.animat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.courview.Rotate3dAnimation;

public class Rate3DActivity extends Activity {
    private ImageView imageview1,imageview2,imageview3,imageview4,imageview5,imageview6;
    private TextView imageview7;
    private RelativeLayout rate_realy,rate_realy2;
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate3d);
        findUI();
    }
    private void findUI(){
        imageview1=(ImageView)findViewById(R.id.imageview1);
        imageview2=(ImageView)findViewById(R.id.imageview2);
        imageview3=(ImageView)findViewById(R.id.imageview3);
        imageview4=(ImageView)findViewById(R.id.imageview4);
        imageview5=(ImageView)findViewById(R.id.imageview5);
        imageview6=(ImageView)findViewById(R.id.imageview6);
        imageview7=(TextView)findViewById(R.id.imageview7);
        rate_realy=(RelativeLayout)findViewById(R.id.rate_realy);
        rate_realy2=(RelativeLayout)findViewById(R.id.rate_realy2);
        button1=(Button)findViewById(R.id.button1);
        mStartAnimView=imageview5;
        imageview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objectAnimatorY();
            }
        });
        imageview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objectAnimatorX();
            }
        });
        imageview7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageview7(0);
            }
        });
        rate_realy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objectAnimator3();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                custonRateAnimation();
            }
        });
    }

    /**
     * ObjectAnimator动画
     */
    private void objectAnimatorY(){
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(imageview1, "rotationY", 0f, 360f);
        objectAnimator.setDuration(500);
        objectAnimator.setInterpolator(new AccelerateInterpolator());
        objectAnimator.start();
    }
    /**
     * ObjectAnimator动画
     */
    private void objectAnimatorX(){
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(imageview2, "rotationX", 0f, 360f);
        objectAnimator.setDuration(500);
        objectAnimator.setInterpolator(new AccelerateInterpolator());
        objectAnimator.start();
    }
    private void objectAnimator3(){
        final ImageView visibleList;
        final ImageView invisibleList;
        if (imageview3.getVisibility() == View.GONE) {
            visibleList = imageview4;
            invisibleList = imageview3;
        } else {
            invisibleList = imageview4;
            visibleList = imageview3;
        }
        ObjectAnimator visToInvis = ObjectAnimator.ofFloat(visibleList, "rotationY", 0f, 90f);
        visToInvis.setDuration(500);
        visToInvis.setInterpolator(new AccelerateInterpolator());
        final ObjectAnimator invisToVis = ObjectAnimator.ofFloat(invisibleList, "rotationY", -90f, 0f);
        invisToVis.setDuration(500);
        invisToVis.setInterpolator(new DecelerateInterpolator());
        visToInvis.start();
        visToInvis.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator anim) {
                visibleList.setVisibility(View.GONE);
                invisToVis.start();
                invisibleList.setVisibility(View.VISIBLE);
            }
        });
    }
    float mCenterX = 0.0f;
    float mCenterY = 0.0f;
    ImageView mStartAnimView=null;
    int mIndex = 0;
    private void custonRateAnimation(){
        mCenterX = rate_realy2.getWidth() / 2;
        mCenterY = rate_realy2.getHeight() / 2;
        float startAngle=0f;
        float toAngle=90f;
        Rotate3dAnimation rotation = new Rotate3dAnimation(
                startAngle, toAngle, mCenterX, mCenterY, 0.0f, true);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new DisplayNextView());
        mStartAnimView.startAnimation(rotation);
    }
    private final class DisplayNextView implements Animation.AnimationListener {

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {

            rate_realy2.post(new SwapViews());
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }
    private final class SwapViews implements Runnable
    {
        @Override
        public void run()
        {
            imageview5.setVisibility(View.GONE);
            imageview6.setVisibility(View.GONE);
            mIndex++;
            if (0 == mIndex % 2)
            {
                mStartAnimView = imageview5;
            }
            else
            {
                mStartAnimView = imageview6;
            }
            mStartAnimView.setVisibility(View.VISIBLE);
            mStartAnimView.requestFocus();
            Rotate3dAnimation rotation = new Rotate3dAnimation(-90,0,mCenterX,mCenterY, 0.0f, false);
            rotation.setDuration(500);
            rotation.setFillAfter(true);
            rotation.setInterpolator(new DecelerateInterpolator());
            mStartAnimView.startAnimation(rotation);
        }
    }
    private void imageview7(final int page){
        Animation a = AnimationUtils.loadAnimation(this,
                R.anim.ratetion_in);
        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                imageview7.setText((page + 1) + "");
                imageview7.startAnimation(AnimationUtils.loadAnimation(Rate3DActivity.this,
                        R.anim.ratetion_out));
            }
        });
        imageview7.startAnimation(a);
    }
}
