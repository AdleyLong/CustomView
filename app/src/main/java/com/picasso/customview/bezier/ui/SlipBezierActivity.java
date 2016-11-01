package com.picasso.customview.bezier.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.picasso.customview.R;
import com.picasso.customview.bezier.view.SlipBezier;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SlipBezierActivity extends AppCompatActivity {

    @BindView(R.id.slip_bezier)
    SlipBezier slipBezier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slip_bezier);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_clear)
    public void onClick() {
        slipBezier.clear();
    }
}
