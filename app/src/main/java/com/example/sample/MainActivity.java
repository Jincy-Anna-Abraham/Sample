package com.example.sample;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private AnimationDrawable frameAnimation;
    private ImageView frameImageView,imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseViews();
        setWorld();

    }

    private void initialiseViews() {
        frameImageView = (ImageView) findViewById(R.id.imageAnimation);
        frameImageView.setBackgroundResource(R.drawable.frame_animation_list);
        frameAnimation = (AnimationDrawable) frameImageView.getBackground();
        imageView = (ImageView)findViewById(R.id.imageView);
    }

    private void setWorld() {
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            frameAnimation.start();
        } else {
            frameAnimation.stop();
        }
    }
}