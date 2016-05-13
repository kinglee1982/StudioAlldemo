package com.app.alldemo.effect;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.alldemo.R;
import com.app.alldemo.courview.BadgeCircleImageView;
import com.app.alldemo.courview.BasgeCircleLayout;

public class CircularmenuActivity extends Activity implements BasgeCircleLayout.OnItemSelectedListener
        ,BasgeCircleLayout.OnItemClickListener{
    private TextView selectedTextView;
    private BasgeCircleLayout circleMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_menu);
        findUI();
    }
    private void findUI(){
        circleMenu = (BasgeCircleLayout)findViewById(R.id.main_circle_layout);
        selectedTextView = (TextView)findViewById(R.id.main_selected_textView);
        selectedTextView.setText(((BadgeCircleImageView)circleMenu.getSelectedItem()).getName());
        circleMenu.setOnItemSelectedListener(this);
        circleMenu.setOnItemClickListener(this);
    }

    @Override
    public void onItemSelected(View view, int position, long id, String name) {
        selectedTextView.setText(name);
    }

    @Override
    public void onItemClick(View view, int position, long id, String name) {
        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
    }
}
