package com.app.alldemo.effect;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.alldemo.R;
import com.app.alldemo.courview.TitlePopup;
import com.app.alldemo.model.ActionItem;
import com.app.alldemo.utils.ViewUtils;

public class PopuActivity extends Activity {
    private Button up,down,left;
    private Button left_button;
    private TitlePopup titlePopup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popu);
        findUI();
        popo();
    }
    private void findUI(){
        up=(Button)findViewById(R.id.up);
        down=(Button)findViewById(R.id.down);
        left=(Button)findViewById(R.id.left);
        left_button=(Button)findViewById(R.id.left_button);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewUtils.getInstance().popuUp(PopuActivity.this, v);
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewUtils.getInstance().popuDown(PopuActivity.this, v);
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left(v);
            }
        });
        left_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewUtils.getInstance().popuLeft(PopuActivity.this, v);
            }
        });
    }
    private void left(View v){
        titlePopup.setAnimationStyle(R.style.cricleBottomAnimation);
        titlePopup.show(v);
    }
    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }
    public static int dip2px(Context context, float px) {
        final float scale = getScreenDensity(context);
        return (int) (px * scale + 0.5);
    }
    private void popo(){
        titlePopup = new TitlePopup(this, dip2px(this, 165), dip2px(
                this, 40));
        titlePopup
                .addAction(new ActionItem(this, "赞", R.drawable.circle_praise));
        titlePopup.addAction(new ActionItem(this, "评论",
                R.drawable.circle_comment));
        titlePopup.setItemOnClickListener(new TitlePopup.OnItemOnClickListener() {
            @Override
            public void onItemClick(ActionItem item, int position) {

            }
        });
    }
}
