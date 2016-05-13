package com.app.alldemo.courview.list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.app.alldemo.R;

import java.util.List;


/**
 * 
 * 右侧字母索引。可作为一个独立的view插入自己的布局中<br>
 * <br>
 * 描述：<br>
 * 线型布局中嵌套一个线型布局（目的为了增大可滑动区域的面积，若不考虑此可不使用嵌套）。<br>
 * 每个字母插入内嵌的线性布局中，根据控件的高度计算每个字母的尺寸。<br>
 * <br>
 * 使用：<br>
 * 将此view插入你的布局文件中，初始化完成之后设置你的ListView和索引用的数据源(需要自己组织排序数据源)即可。<br>
 * <br>
 * 数据源的组织：若某项不参与排序则数据源中设置为'0'或其他小于'A'的ASCII字符，内部会将所有字符转换成大写，所以务必在外部做好排序。<br>
 * <br>
 * 建议：若你的数据中包括ASCII码为a0、20的字符，建议剔除，如：<br>
 * str.replace(' ', ' '); // a0->20 str.replaceAll(" ", "");<br>
 * <br>
 * 如果你处理的是中文，可使用libs中的pinyin4j处理
 * 
 * @author ttdevs 2014-07-31
 * 
 */
public class ListViewLetterIndicator extends LinearLayout implements OnScrollListener {

	private static final String INDICATOR = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private LinearLayout llMain;

	private ListView mListView; // ListView
	private List<String> mData; // 数据源
	private TextView tvAlert; // 显示当前的字母

	private int mIndex; // 当前所处的indicator位置
	private boolean scrollable = true; //

	public ListViewLetterIndicator(Context context) {
		this(context, null);
	}

	public ListViewLetterIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);

		setOrientation(LinearLayout.VERTICAL);
		setBackgroundColor(getResources().getColor(R.color.letter_indicator_background));
		// setPadding(8, 8, 8, 8);

		llMain = new LinearLayout(getContext());
		llMain.setOrientation(LinearLayout.VERTICAL);
		llMain.setGravity(Gravity.CENTER);
		// llMain.setPadding(2, 2, 2, 2);

		int width = (int) getResources().getDimension(R.dimen.letter_indicator_width);
		addView(llMain, width, LayoutParams.MATCH_PARENT);
	}

	/**
	 * 设置数据源
	 * 
	 * @param lv
	 *            绑定的ListView
	 * @param data
	 *            排序用的数据源
	 */
	public void setData(ListView lv, List<String> data) {
		setData(lv, data, null);
	}

	/**
	 * 设置数据源
	 * 
	 * @param lv
	 *            绑定的ListView
	 * @param data
	 *            排序用的数据源
	 * @param tv
	 *            显示当前所处字母的TextView
	 */
	@SuppressLint("DefaultLocale")
	public void setData(ListView lv, List<String> data, TextView tv) {
		mListView = lv;
		mData = data;
		tvAlert = tv;

		for (int i = 0; i < mData.size(); i++) {
			String str = mData.get(i);
			mData.set(i, str.toUpperCase());
		}

		mListView.setOnScrollListener(this);
		mIndex = 0;
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		initView();
	}

	private void initView() {
		int childCount = llMain.getChildCount();
		if (childCount == INDICATOR.length()) {
			// llMain.invalidate();
			return;
		}

		int height = llMain.getHeight();
		int textHeight = (int) Math.floor(height / (INDICATOR.length() + 6));

		LayoutParams llpText = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		for (int i = 0; i < INDICATOR.length(); i++) {
			String str = String.valueOf(INDICATOR.charAt(i));
			TextView tvIndicator = new TextView(getContext());
			tvIndicator.setText(str);
			tvIndicator.setIncludeFontPadding(false);
			tvIndicator.setTextSize(TypedValue.COMPLEX_UNIT_PX, textHeight);
			tvIndicator.setTextColor(getResources().getColor(R.color.letter_indicator_text_normal));
			// tvIndicator.setPadding(0, -4, 0, -4);
			llMain.addView(tvIndicator, llpText);
		}
	}

	@SuppressLint("DefaultLocale")
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			scrollable = false;
			float y = ev.getY();
			float childY = 0;
			int index = 0;
			for (int i = 0; i < INDICATOR.length(); i++) {
				TextView view = (TextView) llMain.getChildAt(i);
				childY = view.getTop();
				int height = view.getHeight();
				if (childY < y && childY + height > y) {
					index = i;
					break;
				}
				view = null; // not neccessary
			}

			TextView view = (TextView) llMain.getChildAt(INDICATOR.length() - 1);
			if (y > view.getTop()) {
				index = INDICATOR.length() - 1;
			}
			view = null;

			changeIndicatorColor(index);

			char indexIndicator = INDICATOR.charAt(index);// A:65, #:23
			if (indexIndicator < 'A') {
				mListView.setSelection(0);
			} else {
				for (int i = 0; i < mData.size(); i++) {
					if (mData.get(i).charAt(0) >= indexIndicator) {
						mListView.setSelection(i);
						return true;
					}
				}
			}

			break;
		case MotionEvent.ACTION_UP:
			postDelayed(new Runnable() {

				@Override
				public void run() {
					scrollable = true;
				}
			}, 100);
			showText("", false);
			break;

		default:
			break;
		}
		return true;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// invalidate();
	}

	@SuppressLint("DefaultLocale")
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		try {
			if (!scrollable || null == mData || mData.size() == 0) {
				return;
			}
			if (null != mData) {
				String str = mData.get(firstVisibleItem);
				char indicator = str.charAt(0);
				if (indicator < 'A') {
					changeIndicatorColor(0);
					return;
				}
				for (int i = 1; i < INDICATOR.length(); i++) {
					if (INDICATOR.charAt(i) == indicator) {
						changeIndicatorColor(i);
						return;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void changeIndicatorColor(int index) {
		if (mIndex != 0 && mIndex == index) {
			return;
		}

		TextView tv = (TextView) llMain.getChildAt(mIndex);
		tv.setTextColor(getResources().getColor(R.color.letter_indicator_text_normal));

		tv = (TextView) llMain.getChildAt(index);
		tv.setTextColor(getResources().getColor(R.color.letter_indicator_text_select));

		mIndex = index;

		showText(String.valueOf(INDICATOR.charAt(mIndex)), true);
	}

	private void showText(String text, boolean isShow) {
		if (null != tvAlert) {
			tvAlert.setText(text);
			tvAlert.setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
		}
	}

	// private Drawable initBackground() {
	// float[] roundRect = new float[] { 12, 12, 12, 12, 12, 12, 12, 12 };
	// RoundRectShape reoundRechShape = new RoundRectShape(roundRect, null,
	// null);
	// ShapeDrawable drawable = new ShapeDrawable(reoundRechShape);
	// drawable.getPaint().setColor(Color.parseColor(GRAY_AA));
	// // drawable.getPaint().setStyle(Paint.Style.FILL);
	// return drawable;
	// }
}