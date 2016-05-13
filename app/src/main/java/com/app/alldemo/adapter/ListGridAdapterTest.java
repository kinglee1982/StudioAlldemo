package com.app.alldemo.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.alldemo.R;
import com.app.alldemo.adapter.other.TestGridviewAdapter;
import com.app.alldemo.courview.list.WrapperGridView;
import com.app.alldemo.model.DataModel;
import com.app.alldemo.utils.AppInfoUtils;
import com.app.alldemo.utils.ViewUtils;

import java.util.List;


@SuppressLint("NewApi")
public class ListGridAdapterTest extends BaseAdapter {
    private static final String TAG = "ListAdapterTest";
    private List<DataModel> datas;
    private Activity ctx;
    private int wh;
    private int spac;
    public ListGridAdapterTest(List<DataModel> datas, Activity ctx) {
        this.datas = datas;
        this.ctx = ctx;
        wh=(AppInfoUtils.getInstance().getScreenWidth(ctx)- ViewUtils.getInstance().dp2px(ctx, 24))/3;
        spac=ViewUtils.getInstance().dp2px(ctx,2);
    }

    public void setDatas(List<DataModel> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(ctx).inflate(
                    R.layout.list_grid_item, null);
            viewHolder.name = (TextView) convertView
                    .findViewById(R.id.name);
            viewHolder.inco = (TextView) convertView
                    .findViewById(R.id.inco);
            viewHolder.image_vatar = (ImageView) convertView
                    .findViewById(R.id.image_vatar);
            viewHolder.gv_images = (WrapperGridView) convertView
                    .findViewById(R.id.gv_images);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        DataModel dataModel=datas.get(position);
        if(!TextUtils.isEmpty(dataModel.getPath())){
        }
        viewHolder.name.setText(dataModel.getName());
        if(!TextUtils.isEmpty(dataModel.getIrco())){
            viewHolder.inco.setText(dataModel.getIrco());
            Linkify.addLinks(viewHolder.inco, Linkify.WEB_URLS);
        }
        List<String> imags=dataModel.getImages();
        if(imags!=null && imags.size()>0){
            gridImag(viewHolder.gv_images,imags);
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView image_vatar;
        TextView name,inco;
        WrapperGridView gv_images;
    }
    private void gridImag(WrapperGridView gv_images,List<String> images){
        int w=0;
        int numColumns=images.size();
        int yu=numColumns%3;
        if(numColumns/3==0){
            if(yu==1){
                w=wh;
                gv_images.setNumColumns(1);
            }else if(yu==2){
                w=wh*2+spac;
                gv_images.setNumColumns(2);
            }
        }else{
            w=wh*3+spac*2;
            gv_images.setNumColumns(3);
        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(w, LinearLayout.LayoutParams.WRAP_CONTENT);
        gv_images.setLayoutParams(lp);
        TestGridviewAdapter adapterTest=new TestGridviewAdapter(images,ctx,wh);
        gv_images.setAdapter(adapterTest);
        gv_images.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Toast.makeText(ctx, "gird点击了第" + (arg2 + 1) + "张图片", Toast.LENGTH_LONG).show();
            }
        });
    }
}
