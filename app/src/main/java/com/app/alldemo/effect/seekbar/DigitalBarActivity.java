package com.app.alldemo.effect.seekbar;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.app.alldemo.R;
public class DigitalBarActivity extends Activity {
    private SeekBar sb_pop;
    private TextView tv_num;
    private int position[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_bar);
        init();
        sb_pop = (SeekBar)findViewById(R.id.sb_pop);
        tv_num = (TextView) findViewById(R.id.tv_num);
        sb_pop.setOnSeekBarChangeListener(sb_pop_change_listener);
        findUI();
    }
    private void findUI(){
    }
    private void init(){
        position = new int[2];
    }
    private void initView(){
        sb_pop.setMax(1000);
        sb_pop.setProgress(50);
        LayoutParams lp = (LayoutParams) sb_pop.getLayoutParams();
        int leftMargin = lp.leftMargin;
        int screenWidth = getScreenWidth(this);
        int sb_width = screenWidth - dip2px(this, 40);
        Drawable drawable = getResources().getDrawable(R.drawable.image_thumb);
//		Drawable drawable = sb_pop.getThumb();
        //计算位置
        int thumb_x = sb_pop.getProgress()
                * (sb_width - drawable.getIntrinsicWidth()) / sb_pop.getMax();
        LayoutParams params = (LayoutParams) tv_num.getLayoutParams();
        params.leftMargin = thumb_x + leftMargin - getViewWidth(tv_num) / 2
                + drawable.getIntrinsicWidth() / 2;
        tv_num.setLayoutParams(params);
    }
    private int getViewWidth(View v) {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        v.measure(w, h);
        return v.getMeasuredWidth();
    }
    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     *            （DisplayMetrics类中属性density）
     * @return
     */
    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
    /**获取屏幕宽度*/
    public int getScreenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
    private OnSeekBarChangeListener sb_pop_change_listener = new OnSeekBarChangeListener() {

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            setPopNum(progress);
        }
    };
    private void setPopNum(int progress) {
        tv_num.setText(progress+"");
        sb_pop.getLocationOnScreen(position);
        System.out.println("position[0]=" + position[0]);
        System.out.println("position[1]=" + position[1]);
        Drawable drawable =getResources().getDrawable(R.drawable.image_thumb);
//		Drawable drawable = sb_pop.getThumb();
        int thumb_x = sb_pop.getProgress()
                * (sb_pop.getWidth() - drawable.getIntrinsicWidth())
                / sb_pop.getMax();
        LayoutParams param = (LayoutParams) tv_num.getLayoutParams();
        param.leftMargin = thumb_x + position[0] - getViewWidth(tv_num) / 2
                + drawable.getIntrinsicWidth() / 2;
        tv_num.setLayoutParams(param);
    }
}
