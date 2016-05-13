package com.app.alldemo.effect.list;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.app.alldemo.R;
import com.app.alldemo.adapter.other.QQAdapter;
import com.app.alldemo.model.other.SName;
import com.app.alldemo.utils.MyLog;
import com.app.alldemo.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;
public class QQFriendsActivity extends Activity implements OnTouchListener{
    private static final String TAG="QQFriendsActivity";
    private ListView friends_list;
    private RelativeLayout comment_layout;
    private EditText comment_edit;
    private LinearLayout lineay_layout;
    private List<SName> snames=new ArrayList<SName>();
    private QQAdapter adpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq_friends);
        friends_list=(ListView)findViewById(R.id.friends_list);
        comment_layout=(RelativeLayout)findViewById(R.id.comment_layout);
        comment_edit=(EditText)findViewById(R.id.comment_edit);
        lineay_layout=(LinearLayout)findViewById(R.id.lineay_layout);
        getdatas();
        adpter=new QQAdapter(this, snames, new com.app.alldemo.listenner.MyTagListenner() {
            @Override
            public void onTagComplete(String values, Object object) {
                if("comment".equals(values)){
                    int pos=(Integer)object;
                    commentView(pos);
                }
            }
        });
        friends_list.setAdapter(adpter);
        friends_list.setOnTouchListener(this);
        lineay_layout.setOnTouchListener(this);
    }
    private void getdatas(){
        String urlString="http://static.social.umeng.com/image_840dfb272f91ff9747577e0f5f226a95@360h_50Q.jpeg";
        String urlString2="http://static.social.umeng.com/image_f4f6eeed90afcd4df1fcbad39f8186ee@360w_50Q.jpeg";
        String urlString3="http://static.social.umeng.com/image_77706eac2771cdb3bc3aefa79dd49572@360w_50Q.jpeg";
        for(int i=0;i<20;i++){
            SName sName=new SName();
            sName.setName1("1名字"+i);
            sName.setName2("二名字"+i);
            List<String> strings=new ArrayList<String>();
            if(i==0){
                strings.add(urlString);
            }
            if(i==3){
                strings.add(urlString);
                strings.add(urlString2);
                strings.add(urlString3);
            }
            if(i==4){
                strings.add(urlString);
                strings.add(urlString2);
            }
            if(i==7){
                strings.add(urlString);
                strings.add(urlString2);
                strings.add(urlString3);
            }
            if(i==10){
                strings.add(urlString3);
            }
            sName.setImages(strings);
            snames.add(sName);
        }
    }
    private void commentView(int pos){
        MyLog.v(TAG, "pos:"+pos);
        adpter=new QQAdapter(this, snames, new com.app.alldemo.listenner.MyTagListenner() {
            @Override
            public void onTagComplete(String values, Object object) {
                if("comment".equals(values)){
                    int pos=(Integer)object;
                    commentView(pos);
                }
            }
        });
        friends_list.setAdapter(adpter);
        friends_list.setSelection(pos);
        comment_edit.requestFocus();
        ViewUtils.getInstance().showKeybord(comment_edit);
        comment_layout.setVisibility(View.VISIBLE);
    }
    public interface MyTagListenner {
        void onTagComplete(String values,Object object);
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        MyLog.v(TAG, "All_layout-onTouch");
        hideCommentView();
        return false;
    }
    private void hideCommentView(){
        if(comment_layout.getVisibility()==View.VISIBLE){
            ViewUtils.getInstance().hideKeybord(comment_edit);
            comment_layout.setVisibility(View.GONE);
        }
    }
}
