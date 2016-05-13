package com.app.alldemo.effect.list;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.app.alldemo.R;
import com.app.alldemo.adapter.other.dragAdapter;
import com.mobeta.android.dslv.DragSortController;
import com.mobeta.android.dslv.DragSortListView;

import java.util.ArrayList;

public class DragListActivity extends Activity {
    private DragSortListView mDslv;
    private DragSortController mController;
    public int dragStartMode = DragSortController.ON_DOWN;
    public boolean removeEnabled = false;
    public int removeMode = DragSortController.FLING_REMOVE;
    public boolean sortEnabled = true;
    public boolean dragEnabled = true;
    private dragAdapter adapter;
    private ArrayList<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_list);
        findUI();
        getData();
        initView();
    }

    private void findUI() {
        mDslv = (DragSortListView) findViewById(R.id.list_test);
        mController = buildController(mDslv);
        mDslv.setFloatViewManager(mController);
        mDslv.setOnTouchListener(mController);
        mDslv.setDragEnabled(dragEnabled);
        mDslv.setDropListener(onDrop);
        mDslv.setRemoveListener(onRemove);
    }

    private void initView() {
        adapter = new dragAdapter(list, this);
        mDslv.setAdapter(adapter);
    }

    private void getData() {
        for (int i = 0; i < 20; i++) {
            list.add("测试" + i);
        }
    }
    private void dropTo(int from, int to){
        if (from != to) {
            String item = (String) adapter.getItem(from);
            list.remove(item);
            list.add(to, item);
            adapter.setDatas(list);
            adapter.notifyDataSetChanged();
        }
    }
    private DragSortListView.DropListener onDrop = new DragSortListView.DropListener() {
        @Override
        public void drop(int from, int to) {
            dropTo(from,to);
        }
    };

    private DragSortListView.RemoveListener onRemove = new DragSortListView.RemoveListener() {
        @Override
        public void remove(int which) {
            Log.v("test", "测试" + which);
            list.remove((String) adapter.getItem(which));
        }
    };

    public DragSortController getController() {
        return mController;
    }

    /**
     * Called in onCreateView. Override this to provide a custom
     * DragSortController.
     */
    public DragSortController buildController(DragSortListView dslv) {
        DragSortController controller = new DragSortController(dslv);
        controller.setDragHandleId(R.id.drag_handle);
        controller.setClickRemoveId(R.id.click_remove);
        controller.setRemoveEnabled(removeEnabled);
        controller.setSortEnabled(sortEnabled);
        controller.setDragInitMode(dragStartMode);
        controller.setRemoveMode(removeMode);
        return controller;
    }
}
