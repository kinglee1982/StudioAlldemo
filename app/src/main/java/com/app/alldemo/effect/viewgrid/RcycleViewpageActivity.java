package com.app.alldemo.effect.viewgrid;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.courview.recycleviewpage.LoopPagerAdapterWrapper;
import com.app.alldemo.courview.recycleviewpage.LoopViewPager;
import com.app.alldemo.model.DataModel;
import com.app.alldemo.utils.MyLog;

import java.util.ArrayList;
import java.util.List;
//viewpagerindicator
public class RcycleViewpageActivity extends Activity {
    private LoopViewPager searchViewpage;
    private List<View> listViews = new ArrayList<View>();
    private List<DataModel> dataModels=new ArrayList<DataModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcyclepage);
        init();
        getDatas();
        findUI();
        initView();
    }
    public void getDatas(){
        for(int i=0;i<3;i++){
            DataModel dataModel=new DataModel();
            dataModel.setId(i);
            dataModel.setName("测试"+i);
            dataModels.add(dataModel);
        }
    }
    private void findUI(){
        searchViewpage = (LoopViewPager) findViewById(R.id.search_viewpage);
    }
    private void init(){
    }
    private void initView(){
        viewpageUI();
    }
    private void viewpageUI() {
        LayoutInflater inflater = getLayoutInflater();
        for(int i=0;i<3;i++){
            View childView = inflater.inflate(R.layout.viewpage_item, null);
            findChildViewUI(childView,i);
            listViews.add(childView);
        }
        searchViewpage.setAdapter(new LoopPagerAdapterWrapper(new MyPagerAdapter(listViews)));
        searchViewpage.setCurrentItem(0);
        searchViewpage.setOnPageChangeListener(new MyOnPageChangeListener());
    }
    private void findChildViewUI(View childView,int i){
        final TextView text=(TextView)childView.findViewById(R.id.text);
        for(DataModel dataModel:dataModels){
            if(dataModel.getId()==i){
                text.setText(dataModel.getName());
            }
        }
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyLog.e("","值:"+text.getText().toString());
            }
        });
    }
    public class MyPagerAdapter extends android.support.v4.view.PagerAdapter {
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

    public class MyOnPageChangeListener implements OnPageChangeListener {

        public void onPageSelected(int position) {
        }
        /**
         * 状态改变时调用:三种状态(0,1,2)
         * 0:默示什么都没做
         * 1:正在滑动
         * 2:滑动完毕了
         */
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
        /**
         * 当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法回一直得到
         * arg0:当前页面，及你点击滑动的页面
         * arg1:当前页面偏移的百分比
         * arg2:当前页面偏移的像素位置
         */
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
    }
}
