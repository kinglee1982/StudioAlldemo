package com.app.alldemo.effect.uiset;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Administrator on 2015/12/28.
 */
public class OperatorActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        test();
    }
    public void test() {
//        >>表示带符号右移，如：int i=15; i>>2的结果是3，移出的部分将被抛弃。
//        转为二进制的形式可能更好理解，0000 1111(15)右移2位的结果是0000 0011(3)，0001 1010(18)右移3位的结果是0000 0011(3)。
    }
}
