package com.app.alldemo.courview.wheel;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WheelScrollView extends ScrollView {
	public static final String TAG = "WheelView";
	private int initialY;
	private Runnable scrollerTask;
	private int newCheck = 50;
	private List<String> items;
	private static final int OFF_SET_DEFAULT = 1;
	private int offset = OFF_SET_DEFAULT;// 前面后面需要不全的个数
	private int displayItemCount; // 每页显示的数量
	private int selectedIndex = 1;
	private int itemHeight = 0;
	private String selectText = "#0288ce";// 选择字体的颜色
	private String noSelectText = "#bbbbbb";// 未选择字体的颜色
	private int scrollDirection = -1;
	private static final int SCROLL_DIRECTION_UP = 0;
	private static final int SCROLL_DIRECTION_DOWN = 1;
	private Paint paint;
	private int viewWidth;

	public static class OnWheelViewListener {
		public void onSelected(int selectedIndex, String item) {
		};
	}

	private Context context;
	private LinearLayout views;

	public WheelScrollView(Context context) {
		super(context);
		init(context);
	}

	public WheelScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public WheelScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private List<String> getItems() {
		return items;
	}

	private OnWheelViewListener onWheelViewListener;

	public OnWheelViewListener getOnWheelViewListener() {
		return onWheelViewListener;
	}

	public void setOnWheelViewListener(OnWheelViewListener onWheelViewListener) {
		this.onWheelViewListener = onWheelViewListener;
	}

	/**
	 * 添加子布局
	 * 
	 * @param list
	 */
	public void setItems(List<String> list) {
		if (null == items) {
			items = new ArrayList<String>();
		}
		items.clear();
		items.addAll(list);
		// 前面和后面补全
		for (int i = 0; i < offset; i++) {
			items.add(0, "");
			items.add(items.size(), "");
		}
		initData();
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getDisplayItemCount() {
		return displayItemCount;
	}

	public void setDisplayItemCount(int displayItemCount) {
		this.displayItemCount = displayItemCount;
	}

	private void init(Context context) {
		this.context = context;
		this.setVerticalScrollBarEnabled(false);
		views = new LinearLayout(context);
		views.setOrientation(LinearLayout.VERTICAL);
		this.addView(views);

		scrollerTask = new Runnable() {

			public void run() {
				int newY = getScrollY();
				if (initialY - newY == 0) { // stopped
					final int remainder = initialY % itemHeight;
					final int divided = initialY / itemHeight;
					if (remainder == 0) {
						selectedIndex = divided + offset;
						onSeletedCallBack();
					} else {
						if (remainder > itemHeight / 2) {
							WheelScrollView.this.post(new Runnable() {
								@Override
								public void run() {
									WheelScrollView.this.smoothScrollTo(0, initialY
											- remainder + itemHeight);
									selectedIndex = divided + offset + 1;
									onSeletedCallBack();
								}
							});
						} else {
							WheelScrollView.this.post(new Runnable() {
								@Override
								public void run() {
									WheelScrollView.this.smoothScrollTo(0, initialY
											- remainder);
									selectedIndex = divided + offset;
									onSeletedCallBack();
								}
							});
						}
					}
				} else {
					initialY = getScrollY();
					WheelScrollView.this.postDelayed(scrollerTask, newCheck);
				}
			}
		};
	}

	public void startScrollerTask() {
		initialY = getScrollY();
		this.postDelayed(scrollerTask, newCheck);
	}

	private void initData() {
		for (String item : items) {
			views.addView(createView(item));
		}
		refreshItemView(0);
	}

	private TextView createView(String item) {
		TextView tv = new TextView(context);
		tv.setLayoutParams(new LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
		tv.setSingleLine(true);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
		tv.setText(item);
		tv.setGravity(Gravity.CENTER);
		int padding = WheelUtils.dip2px(context, 15);
		tv.setPadding(padding, padding, padding, padding);
		if (0 == itemHeight) {
			itemHeight = getViewMeasuredHeight(tv);
			views.setLayoutParams(new LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT, itemHeight
							* displayItemCount));
			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) this
					.getLayoutParams();
			this.setLayoutParams(new LinearLayout.LayoutParams(lp.width,
					itemHeight * displayItemCount));
		}
		tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextView textView=(TextView)v;
				String text=textView.getText().toString();
				if(!TextUtils.isEmpty(text)){
					int position=0;
					for(int i=0;i<items.size();i++){
						if(text.equals(items.get(i))){
							position=i;
						}
					}
					Log.v(TAG,"text:"+text+":position:"+position);
					setSeletion(position-offset);
					selectedIndex=position;
					onSeletedCallBack();
				}
			}
		});
		return tv;
	}

	/**
	 * 获取控件的高度，如果获取的高度为0，则重新计算尺寸后再返回高度
	 *
	 * @param view
	 * @return
	 */
	public int getViewMeasuredHeight(View view) {
		calcViewMeasure(view);
		return view.getMeasuredHeight();
	}

	/**
	 * 测量控件的尺寸
	 *
	 * @param view
	 */
	public void calcViewMeasure(View view) {
		int width = MeasureSpec.makeMeasureSpec(0,
				MeasureSpec.UNSPECIFIED);
		int expandSpec = MeasureSpec.makeMeasureSpec(
				Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		view.measure(width, expandSpec);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		refreshItemView(t);
		if (t > oldt) {
			scrollDirection = SCROLL_DIRECTION_DOWN;
		} else {
			scrollDirection = SCROLL_DIRECTION_UP;
		}
	}

	/**
	 * 刷新当前的位置
	 * 
	 * @param y
	 */
	private void refreshItemView(int y) {
		int position = y / itemHeight + offset;
		int remainder = y % itemHeight;
		int divided = y / itemHeight;
		if (remainder == 0) {
			position = divided + offset;
		} else {
			if (remainder > itemHeight / 2) {
				position = divided + offset + 1;
			}
		}
		int childSize = views.getChildCount();
		for (int i = 0; i < childSize; i++) {
			TextView itemView = (TextView) views.getChildAt(i);
			if (null == itemView) {
				return;
			}
			if (position == i) {
				itemView.setTextColor(Color.parseColor(selectText));
			} else {
				itemView.setTextColor(Color.parseColor(noSelectText));
			}
		}
	}

	/**
	 * 获取选中区域的边界
	 */
	int[] selectedAreaBorder;
	private int[] obtainSelectedAreaBorder() {
		if (null == selectedAreaBorder) {
			selectedAreaBorder = new int[2];
			selectedAreaBorder[0] = itemHeight * offset;
			selectedAreaBorder[1] = itemHeight * (offset + 1);
		}
		return selectedAreaBorder;
	}

	@Override
	public void setBackgroundDrawable(Drawable background) {
		if (viewWidth == 0) {
			viewWidth = ((Activity) context).getWindowManager()
					.getDefaultDisplay().getWidth();
		}
		if (null == paint) {
			paint = new Paint();
			paint.setColor(Color.parseColor("#83cde6"));
			paint.setStrokeWidth(WheelUtils.dip2px(context, 1f));
		}
		background = new Drawable() {
			@Override
			public void draw(Canvas canvas) {
				canvas.drawLine(viewWidth * 1 / 6,
						obtainSelectedAreaBorder()[0], viewWidth * 5 / 6,
						obtainSelectedAreaBorder()[0], paint);
				canvas.drawLine(viewWidth * 1 / 6,
						obtainSelectedAreaBorder()[1], viewWidth * 5 / 6,
						obtainSelectedAreaBorder()[1], paint);
			}

			@Override
			public void setAlpha(int alpha) {

			}

			@Override
			public void setColorFilter(ColorFilter cf) {

			}

			@Override
			public int getOpacity() {
				return 0;
			}
		};

		super.setBackgroundDrawable(background);

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		viewWidth = w;
		setBackgroundDrawable(null);
	}

	/**
	 * 选中回调
	 */
	private void onSeletedCallBack() {
		if (null != onWheelViewListener) {
			onWheelViewListener.onSelected(selectedIndex,
					items.get(selectedIndex));
		}
	}

	public void setSeletion(int position) {
		final int p = position;
		selectedIndex = p + offset;
		this.post(new Runnable() {
			@Override
			public void run() {
				WheelScrollView.this.smoothScrollTo(0, p * itemHeight);
			}
		});

	}

	public String getSeletedItem() {
		return items.get(selectedIndex);
	}

	public int getSeletedIndex() {
		return selectedIndex - offset;
	}

	@Override
	public void fling(int velocityY) {
		super.fling(velocityY / 3);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_UP) {
			startScrollerTask();
		}
		return super.onTouchEvent(ev);
	}

}
