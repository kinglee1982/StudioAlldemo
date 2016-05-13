package com.app.alldemo.effect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.alldemo.R;
import com.app.alldemo.effect.list.ListGridActivity;
import com.app.alldemo.effect.list.DeleteLeftMainActivity;
import com.app.alldemo.effect.list.DragListActivity;
import com.app.alldemo.effect.list.LetterSequenceActivity;
import com.app.alldemo.effect.list.ListJuBuActivity;
import com.app.alldemo.effect.list.ListLetterActivity;
import com.app.alldemo.effect.list.ListViewSectionActivity;
import com.app.alldemo.effect.list.ListViewTitleActivity;
import com.app.alldemo.effect.list.QQFriendsActivity;
import com.app.alldemo.effect.list.RefreshActivity;
import com.app.alldemo.effect.list.RefreshGridActivity;
import com.app.alldemo.effect.list.RefreshListActivity;
import com.app.alldemo.effect.list.RefreshScrolloListViewActivity;
import com.app.alldemo.effect.list.SGridviewActivity;
import com.app.alldemo.effect.list.SGridviewActivity3;
import com.app.alldemo.effect.list.SListViewActivity;
import com.app.alldemo.effect.list.SListViewTestActivity;
import com.app.alldemo.effect.list.ScoloListView;
import com.app.alldemo.effect.list.SlideMainActivity;
import com.app.alldemo.effect.list.SortListViewMainActivity;
import com.app.alldemo.effect.list.SrollListActivity;
import com.app.alldemo.effect.list.SwipMainActivity;
import com.app.alldemo.effect.list.TofloatlistActivity;
import com.app.alldemo.effect.list.ViewHodleTestActivity;

/**
 * Created by Administrator on 2015/10/30.
 */
public class ListViewTestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_test);
        findUI();
    }

    private void findUI() {
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, RefreshScrolloListViewActivity.class);
                startActivity(intent);
            }
        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, RefreshListActivity.class);
                startActivity(intent);
            }
        });
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, RefreshGridActivity.class);
                startActivity(intent);
            }
        });
        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, RefreshActivity.class);
                startActivity(intent);
            }
        });
        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, DragListActivity.class);
                startActivity(intent);
            }
        });
        Button button6= (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, LetterSequenceActivity.class);
                startActivity(intent);
            }
        });
        Button button7= (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, SortListViewMainActivity.class);
                startActivity(intent);
            }
        });
        Button button8= (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, ListLetterActivity.class);
                startActivity(intent);
            }
        });
        Button button9= (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, ListViewTitleActivity.class);
                startActivity(intent);
            }
        });
        Button button10= (Button) findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, SlideMainActivity.class);
                startActivity(intent);
            }
        });
        Button button11= (Button) findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, SwipMainActivity.class);
                startActivity(intent);
            }
        });
        Button button12= (Button) findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, TofloatlistActivity.class);
                startActivity(intent);
            }
        });
        Button button13= (Button) findViewById(R.id.button13);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, SrollListActivity.class);
                startActivity(intent);
            }
        });
        Button button14= (Button) findViewById(R.id.button14);
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, DeleteLeftMainActivity.class);
                startActivity(intent);
            }
        });
        Button button15= (Button) findViewById(R.id.button15);
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, ScoloListView.class);
                startActivity(intent);
            }
        });
        Button button16= (Button) findViewById(R.id.button16);
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, ViewHodleTestActivity.class);
                startActivity(intent);
            }
        });
        Button button17= (Button) findViewById(R.id.button17);
        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, SListViewActivity.class);
                startActivity(intent);
            }
        });
        Button button18= (Button) findViewById(R.id.button18);
        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, SListViewTestActivity.class);
                startActivity(intent);
            }
        });
        Button button19= (Button) findViewById(R.id.button19);
        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, SGridviewActivity.class);
                startActivity(intent);
            }
        });
        Button button20= (Button) findViewById(R.id.button20);
        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, ListGridActivity.class);
                startActivity(intent);
            }
        });
        Button button21= (Button) findViewById(R.id.button21);
        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, QQFriendsActivity.class);
                startActivity(intent);
            }
        });
        Button button22= (Button) findViewById(R.id.button22);
        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, SGridviewActivity3.class);
                startActivity(intent);
            }
        });
        Button button23= (Button) findViewById(R.id.button23);
        button23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, ListViewSectionActivity.class);
                startActivity(intent);
            }
        });
        Button button24= (Button) findViewById(R.id.button24);
        button24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTestActivity.this, ListJuBuActivity.class);
                startActivity(intent);
            }
        });
    }
}
