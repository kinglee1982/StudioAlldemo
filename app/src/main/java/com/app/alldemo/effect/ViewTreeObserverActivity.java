package com.app.alldemo.effect;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.utils.MyLog;

/**
 * 摘要 当一个视图树的布局发生改变时，可以被ViewTreeObserver监听到，
 * 这是一个注册监听视图树的观察者(observer)，在视图树的全局事件改变时得到通知。
 * ViewTreeObserver不能直接实例化，而是通过getViewTreeObserver()获得
 * OnGlobalLayoutListener是ViewTreeObserver的内部类
 * ,当一个视图树的布局发生改变时，可以被ViewTreeObserver监听到，
 *
 * @author Administrator 获取view的高度
 */
public class ViewTreeObserverActivity extends Activity {
    private static final String TAG = "OnGlobalLayoutListenerTestActivity";
    private TextView textview_test;
    private ImageView oneAblumImag;
    private int screen_width;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tree);
        findUI();
    }
    private void findUI(){
        textview_test = (TextView) findViewById(R.id.textview_test);
        oneAblumImag = (ImageView) findViewById(R.id.imageview);
        init();
        ViewTreeObserver viewTreeObserver = textview_test.getViewTreeObserver();
        // 当在一个视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变时调用这个回调函数。
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = textview_test.getWidth();
                int height = textview_test.getHeight();
                MyLog.v(TAG, "addOnGlobalLayoutListener-width:" + width
                        + "height:" + height);
                // OnGlobalLayoutListener可能会被多次触发，因此在得到了高度之后，要将OnGlobalLayoutListener注销掉。
                textview_test.getViewTreeObserver()
                        .removeGlobalOnLayoutListener(this);
            }
        });
        ViewTreeObserver vto = oneAblumImag.getViewTreeObserver();
        // 当一个视图树将要绘制时调用这个回调函数。
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {
                int width = oneAblumImag.getWidth();
                int height = oneAblumImag.getHeight();
                MyLog.v(TAG, "addOnPreDrawListener-width:" + width + "height:"
                        + height);
                if (width != screen_width) {
                    setAblumImageWidth();
                } else {
                    oneAblumImag.getViewTreeObserver().removeOnPreDrawListener(
                            this);
                }
                return true;
            }
        });
    }
    private void init() {
        getScreenWidth(this);
        int width = textview_test.getWidth();
        int height = textview_test.getHeight();
        MyLog.v(TAG, "onCreate-width:" + width + "height:" + height);
    }

    private void setAblumImageWidth() {
        MyLog.v(TAG, "setAblumImageWidth");
        int imagWidth = oneAblumImag.getWidth();
        float screenWidthFloat = screen_width;
        float imagWidthFloat = imagWidth;
        float scale = screenWidthFloat / imagWidthFloat;
        Matrix matrix = oneAblumImag.getImageMatrix();
        matrix.setScale(scale, scale);
        oneAblumImag.setImageMatrix(matrix);
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.width = screen_width;
        oneAblumImag.setLayoutParams(params);
        oneAblumImag.invalidate();
    }

    /** 获取屏幕宽度 */
    public int getScreenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        screen_width = dm.widthPixels;
        return screen_width;
    }

    @Override
    public void onResume() {
        super.onResume();
        int width = textview_test.getWidth();
        int height = textview_test.getHeight();
        MyLog.v(TAG, "onResume-width:" + width + "height:" + height);
    }
}
