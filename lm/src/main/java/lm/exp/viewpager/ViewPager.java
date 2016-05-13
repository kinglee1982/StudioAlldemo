package lm.exp.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;

/**
 * Created by limin on 16/01/07.
 */
public class ViewPager extends android.support.v4.view.ViewPager {
	public ViewPager(Context context) {
		super(context);
	}

	public ViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setAdapter(PagerAdapter adapter) {
		super.setAdapter(adapter);
	}

	public ItemObject getCurrentItemObject() {
		PagerAdapter adapter = (PagerAdapter) getAdapter();
		SparseArray<ItemObject> attached = adapter.getAttachedItemObject();
		return attached.get(getCurrentItem());
	}
}