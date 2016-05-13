package com.app.alldemo.adapter.other;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;
import com.app.alldemo.R;
import com.app.alldemo.model.Info;

import java.util.ArrayList;

public class VolleyAdapter extends BaseAdapter {
	private Context ctx;
	private ArrayList<Info> infos;
	private RequestQueue mQueue;
    private ImageLoader mImageLoader;

	public VolleyAdapter(Context ctx, ArrayList<Info> infos) {
		this.ctx = ctx;
		this.infos = infos;
		mQueue = Volley.newRequestQueue(ctx);
		mImageLoader = new ImageLoader(mQueue, new BitmapCache());
	}

	@Override
	public int getCount() {
		return infos.size();
	}

	@Override
	public Info getItem(int position) {
		return infos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(ctx).inflate(
					R.layout.item, null);
			viewHolder.item = (ImageView) convertView
					.findViewById(R.id.item);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		ImageListener listener = ImageLoader.getImageListener(viewHolder.item, android.R.drawable.ic_menu_rotate, android.R.drawable.ic_delete);
        mImageLoader.get(getItem(position).getUrl(), listener);
		return convertView;
	}
	private class ViewHolder {
		ImageView item;
	}
}
