package com.app.alldemo.adapter.other;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.model.other.DraftBoxBean;

import java.util.List;


/**
 * 草稿箱
 * @author Administrator
 *
 */
public class DraftBoxAdapter extends BaseAdapter{
	ViewHolder viewHolder;
	private Context context;
	private List<DraftBoxBean> dataBeans;
	private LayoutInflater inflater;
	public DraftBoxAdapter(Context context,List<DraftBoxBean> dataBeans){
		this.context=context;
		this.dataBeans = dataBeans;
		this.inflater = LayoutInflater.from(context);
	}
	
	public void setdatas(List<DraftBoxBean> dataBeans){
		this.dataBeans = dataBeans;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dataBeans.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return dataBeans.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null){
			convertView = inflater.inflate(R.layout.draftbox_item, null);
			viewHolder = new ViewHolder();
			viewHolder.textView=(TextView)convertView.findViewById(R.id.tv_name);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		DraftBoxBean bean = dataBeans.get(position);
		viewHolder.textView.setText(bean.getName());
		return convertView;
	}
	
	class ViewHolder{
		TextView textView;
	}

}
