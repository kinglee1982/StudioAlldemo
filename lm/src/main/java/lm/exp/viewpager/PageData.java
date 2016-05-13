package lm.exp.viewpager;

import android.database.DataSetObservable;
import android.database.DataSetObserver;

/**
 * Created by limin on 16/01/07.
 */
public class PageData {
	private final DataSetObservable mObservable = new DataSetObservable();

	final public void registerObserver(DataSetObserver observer) {
		mObservable.registerObserver(observer);
	}

	final public void unregisterObserver(DataSetObserver observer) {
		mObservable.unregisterObserver(observer);
	}

	public void notifyDataChanged() {
		mObservable.notifyChanged();
	}
}