package com.app.alldemo.animat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.app.alldemo.R;
import com.app.alldemo.animat.courview.SwingAnimation;
import com.app.alldemo.effect.AcActivity;

/**
 * Created by Administrator on 2016/2/18.
 */
public class PlumAnmationActivity extends AcActivity{
    private RelativeLayout pendulum_relay;
    private ImageView pendulum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plum_animat);
        pendulum=(ImageView)findViewById(R.id.pendulum);
        pendulum_relay=(RelativeLayout)findViewById(R.id.pendulum_relay);
        pendulum_relay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pendulumAnima();
            }
        });
    }
    private void pendulumAnima(){
        SwingAnimation swingAnimation=new SwingAnimation(5, SwingAnimation.Position.MAX, 15f, 0.5f, 1.0f);
        swingAnimation.setDuration(6000);
        swingAnimation.setFillAfter(true);
        pendulum.startAnimation(swingAnimation);
    }
}
