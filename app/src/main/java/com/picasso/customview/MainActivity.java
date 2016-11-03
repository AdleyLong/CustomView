package com.picasso.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.picasso.customview.bezier.ui.Clean360Activity;
import com.picasso.customview.bezier.ui.SecondOrderBezierActivity;
import com.picasso.customview.bezier.ui.SlipBezierActivity;
import com.picasso.customview.bezier.ui.ThreeOrderBezierActivity;
import com.picasso.customview.gradient.ui.FlickerTextActivity;
import com.picasso.customview.gradient.ui.VolumeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * bezier系列
     */
    public void secondOrder(View view){
        startActivity(new Intent(MainActivity.this, SecondOrderBezierActivity.class));
    }

    public void threeOrder(View view){
        startActivity(new Intent(MainActivity.this, ThreeOrderBezierActivity.class));
    }

    public void slip(View view){
        startActivity(new Intent(MainActivity.this, SlipBezierActivity.class));
    }

    public void clean360(View view){
        startActivity(new Intent(MainActivity.this, Clean360Activity.class));
    }

    public void flickerText(View view) {
        startActivity(new Intent(MainActivity.this, FlickerTextActivity.class));
    }

    public void volumeBar(View view) {
        startActivity(new Intent(MainActivity.this, VolumeActivity.class));
    }
}
