package com.app.alldemo.effect;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.app.alldemo.R;
import com.app.alldemo.slid.MenuFragment;
import com.app.alldemo.slid.MenuFragment.SLMenuItemClickListener;
import com.app.alldemo.slid.Test2Fragment;
import com.app.alldemo.slid.TestFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class SlidMenuActivity extends SlidingFragmentActivity implements SLMenuItemClickListener {
    private SlidingMenu mSlidingMenu;
    private ImageView iv_toggle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slid_menu);
        initSlidingMenu();
        findUI();
    }
    private void findUI(){
        iv_toggle = (ImageView) findViewById(R.id.iv_toggle);
        iv_toggle.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                tager();
            }
        });
    }
    private void tager(){
        toggle();
    }
    private void initSlidingMenu() {
        // 设置侧面菜单要使用的布局文件
        setBehindContentView(R.layout.frame_menu);
        mSlidingMenu = getSlidingMenu();
        // 设置阴影图片
        // mSlidingMenu.setShadowDrawable(R.drawable.shadow);
        // 设置阴影图片的宽度
        // mSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        // 设置slidingMenu菜单划出后主界面剩余的宽度
        mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // SlidingMenu滑动时的渐变程度
        mSlidingMenu.setFadeDegree(0.35f);
        // 使SlidingMenu附加在Activity上 即： 左边，右边，内容等。。。。
        // mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        // 设置slidingmenu菜单的打开方式 ：边缘滑动 屏幕任意处滑动 不允许滑动屏幕打开
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        // 设置slidingmenu菜单的内容
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.frame_menu, new MenuFragment());
        fragmentTransaction.replace(R.id.content, new TestFragment());
        fragmentTransaction.commit();

        // 使用左上方icon可点，这样在onOptionsItemSelected里面才可以监听到R.id.home
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 设置slidingMenu 是否为activity的内容
        // setSlidingActionBarEnabled(false);
    }
    @Override
    public void itemClick() {
        // TODO Auto-generated method stub
        mSlidingMenu.showContent();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.content, new Test2Fragment());
        fragmentTransaction.commit();
    }
}
