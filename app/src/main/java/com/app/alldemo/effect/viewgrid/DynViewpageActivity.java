package com.app.alldemo.effect.viewgrid;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.utils.AppInfoUtils;
import com.app.alldemo.utils.MyLog;

import java.util.ArrayList;
import java.util.List;

public class DynViewpageActivity extends Activity {
    private static final String TAG="DynViewpageActivity";
    private TextView searchAllmusicTitle, searchAblumTitle, searchMusicTitle;
    private ImageView searchLineimag;
    private ViewPager searchViewpage;
    private int screen_width;
    private List<View> listViews = new ArrayList<View>();
    private int currIndex;// 记录当前的位置
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpage);
        init();
        findUI();
        initView();
    }
    private void findUI(){
        searchAllmusicTitle =(TextView)findViewById(R.id.search_allmusic_title);
        searchAblumTitle =(TextView)findViewById(R.id.search_ablum_title);
        searchMusicTitle =(TextView)findViewById(R.id.search_music_title);
        searchLineimag=(ImageView)findViewById(R.id.search_lineimag);
        searchViewpage = (ViewPager) findViewById(R.id.search_viewpage);
        searchAllmusicTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchViewpage.setCurrentItem(0);
            }
        });
        searchAblumTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchViewpage.setCurrentItem(1);
            }
        });
        searchMusicTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchViewpage.setCurrentItem(2);
            }
        });
    }
    private void init(){
        screen_width= AppInfoUtils.getInstance().getScreenWidth(this);
    }
    private void initView(){
        ViewGroup.LayoutParams params = searchLineimag.getLayoutParams();
        int imagWidth = (screen_width / 3);
        params.width = imagWidth;
        searchLineimag.setLayoutParams(params);
        viewpageUI();
    }
    private void viewpageUI() {
        LayoutInflater inflater = getLayoutInflater();
        View allMusicView = inflater.inflate(R.layout.viewpage_item, null);
        View ablumView = inflater.inflate(R.layout.viewpage_item, null);
        View musicView = inflater.inflate(R.layout.viewpage_item, null);
        listViews.add(allMusicView);
        listViews.add(ablumView);
        listViews.add(musicView);
        searchViewpage.setAdapter(new MyPagerAdapter(listViews));
        searchViewpage.setCurrentItem(0);
        searchViewpage.setOnPageChangeListener(new MyOnPageChangeListener());
    }
    private void setImagPosition(int position) {
        Animation animation = null;
        if (currIndex == 0) {
            animation = new TranslateAnimation(0,
                    (screen_width / 3) * position, 0, 0);
        } else if (currIndex == 1) {
            animation = new TranslateAnimation((screen_width / 4),
                    (screen_width / 3) * position, 0, 0);
            ;
        } else if (currIndex == 2) {
            animation = new TranslateAnimation((screen_width / 4) * 2,
                    (screen_width / 3) * position, 0, 0);
            ;
        }
        if (position == 0) {
            searchAllmusicTitle.setTextColor(getResources().getColor(
                    R.color.search_select_title));
            searchAblumTitle.setTextColor(getResources().getColor(
                    R.color.search_noselect_title));
            searchMusicTitle.setTextColor(getResources().getColor(
                    R.color.search_noselect_title));
        } else if (position == 1) {
            searchAllmusicTitle.setTextColor(getResources().getColor(
                    R.color.search_noselect_title));
            searchAblumTitle.setTextColor(getResources().getColor(
                    R.color.search_select_title));
            searchMusicTitle.setTextColor(getResources().getColor(
                    R.color.search_noselect_title));
        } else if (position == 2) {
            searchAllmusicTitle.setTextColor(getResources().getColor(
                    R.color.search_noselect_title));
            searchAblumTitle.setTextColor(getResources().getColor(
                    R.color.search_noselect_title));
            searchMusicTitle.setTextColor(getResources().getColor(
                    R.color.search_select_title));
        }
        animation.setFillAfter(true);// True:图片停在动画结束位置
        animation.setDuration(300);
        searchLineimag.startAnimation(animation);
        currIndex = position;
    }
    public class MyPagerAdapter extends PagerAdapter {
        private List<View> mListViews;

        public MyPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(mListViews.get(arg1));
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(mListViews.get(arg1), 0);
            return mListViews.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (arg1);
        }
    }
    private boolean isScrolling = false;
    private int lastValue = -1;
    private int oldPosition=-1;
    private int isPosition=-1;
    public class MyOnPageChangeListener implements OnPageChangeListener {

        public void onPageSelected(int position) {
                oldPosition=position;
                setImagPosition(position);
        }
        /**
         * 状态改变时调用:三种状态(0,1,2)
         * 0:默示什么都没做
         * 1:正在滑动
         * 2:滑动完毕了
         */
        @Override
        public void onPageScrollStateChanged(int arg0) {
            if (arg0 == 1) {
                isScrolling = true;
            } else {
                isScrolling = false;
                if(arg0 == 0){
                    if(oldPosition==0 && isPosition == 3){

                    }
                }
            }
        }
        /**
         * 当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法回一直得到
         * arg0:当前页面，及你点击滑动的页面
         * arg1:当前页面偏移的百分比
         * arg2:当前页面偏移的像素位置
         */
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            if (isScrolling) {
                if (lastValue > arg2) {
                    // 递减，向右侧滑动
                    isPosition=2;
                    MyLog.v(TAG, "右滑");
                } else if (lastValue < arg2) {
                    // 递减，向右侧滑动
                    isPosition=1;
                    MyLog.v(TAG, "左滑");
                } else if (lastValue == arg2) {
                    isPosition=3;
                    MyLog.v(TAG, "没滑");
                }
            }
            lastValue = arg2;
        }
    }
}
