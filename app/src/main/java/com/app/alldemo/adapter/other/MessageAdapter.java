package com.app.alldemo.adapter.other;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.alldemo.R;
import com.app.alldemo.model.other.DraftBoxBean;

import java.util.List;


/**
 * 草稿箱
 * @author Administrator
 *
 */
public class MessageAdapter extends BaseAdapter{
	ViewHolder viewHolder;
	private Context context;
	private List<DraftBoxBean> dataBeans;
	private LayoutInflater inflater;
	public MessageAdapter(Context context, List<DraftBoxBean> dataBeans){
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
			viewHolder.iv_icon=(ImageView)convertView.findViewById(R.id.iv_icon);
			viewHolder.item_layout=(RelativeLayout)convertView.findViewById(R.id.item_layout);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		DraftBoxBean bean = dataBeans.get(position);
		viewHolder.textView.setText(bean.getName() + bean.getContent());
		textClick(viewHolder.textView, bean);
		viewHolder.iv_icon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "头像的点击", Toast.LENGTH_SHORT).show();
			}
		});
//		convertView.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(context, "item的点击", Toast.LENGTH_SHORT).show();
//			}
//		});
		return convertView;
	}
	
	class ViewHolder{
		ImageView iv_icon;
		TextView textView;
		RelativeLayout item_layout;
	}
	private void textClick(TextView text_click,DraftBoxBean bean){
		SpannableString spannableString1 = new SpannableString(text_click.getText());
		String textString=text_click.getText().toString();
		if(textString.contains(bean.getName())){
			int start=textString.indexOf(bean.getName());
			int end=start+bean.getName().length();
			spannableString1.setSpan(new TextViewURLSpan(bean), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			text_click.setText(spannableString1);
			text_click.setMovementMethod(LinkMovementMethod.getInstance());
		}
	}
	private class TextViewURLSpan extends ClickableSpan {
		private DraftBoxBean bean;
		public TextViewURLSpan(DraftBoxBean bean) {
			this.bean=bean;
		}

		@Override
		public void updateDrawState(TextPaint ds) {
		}

		@Override
		public void onClick(View widget) {
			Toast.makeText(context,"名字点击",Toast.LENGTH_SHORT).show();
		}
	}
}
