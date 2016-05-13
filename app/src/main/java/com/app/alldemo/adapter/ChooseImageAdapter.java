package com.app.alldemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.app.alldemo.R;
import com.app.alldemo.model.ImageItem;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;


@SuppressLint("NewApi")
public class ChooseImageAdapter extends BaseAdapter {
    private static final String TAG = "ListAdapterTest";
    private List<ImageItem> datas;
    private Context ctx;

    public ChooseImageAdapter(List<ImageItem> datas, Context ctx) {
        this.datas = datas;
        this.ctx = ctx;
    }

    public void setDatas(List<ImageItem> datas) {
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
                    R.layout.choose_imag_item, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ImageItem imageItem=datas.get(position);
        ImageLoader.getInstance().displayImage("file://" + imageItem.imagePath,
                viewHolder.imageView);
        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
    }
}
