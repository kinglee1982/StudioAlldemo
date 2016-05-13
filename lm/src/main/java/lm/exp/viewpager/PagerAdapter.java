package lm.exp.viewpager;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by limin on 16/01/07.
 */
public abstract class PagerAdapter extends android.support.v4.view.PagerAdapter {

	private SparseArray<ItemObject> mAttachedItemObject = new SparseArray<ItemObject>();

	protected abstract PageData getPageData(int position);

	protected abstract ItemObject instantiateItem(int position);

	@Override
	final public Object instantiateItem(ViewGroup container, int position) {
		ItemObject itemObject = instantiateItem(position);
		itemObject.attach(container);
		itemObject.bind(getPageData(position));
		mAttachedItemObject.put(position, itemObject);
		return itemObject;
	}

	protected void destroyItem(int position, ItemObject object) {}

	@Override
	final public void destroyItem(ViewGroup container, int position, Object object) {
		ItemObject itemObject = (ItemObject) object;
		itemObject.detach();
		mAttachedItemObject.remove(position);
		destroyItem(position, itemObject);
	}

	@Override
	final public boolean isViewFromObject(View view, Object object) {
		return view.equals(((ItemObject) object).getView());
	}

	final SparseArray<ItemObject> getAttachedItemObject() {
		return mAttachedItemObject;
	}
}