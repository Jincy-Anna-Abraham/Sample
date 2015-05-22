package com.example.sample;

import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
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
        setAnimation();
    }

    private void initialiseViews() {
        imageView = (ImageView)findViewById(R.id.imageView);
    }

    private void setWorld() {
        frameImageView = (ImageView) findViewById(R.id.imageAnimation);
        frameImageView.setBackgroundResource(R.drawable.frame_animation_list);
        frameAnimation = (AnimationDrawable) frameImageView.getBackground();

    }

    private void setAnimation() {
        final Path mPath = new Path();
        mPath.moveTo(0, 0);
        mPath.quadTo(50, -50, 100, 225);
        mPath.quadTo(150, 400, 200, 225);
        mPath.quadTo(250, -50, 300, 225);
        mPath.quadTo(350, 400, 400, 225);
        mPath.quadTo(450, -50, 500, 225);
        mPath.moveTo(500, 225);
        ValueAnimator pathAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);

        pathAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            float[] point = new float[2];

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float val = animation.getAnimatedFraction();
                PathMeasure pathMeasure = new PathMeasure(mPath, true);
                pathMeasure.getPosTan(pathMeasure.getLength() * val, point, null);
                imageView.setX(point[0]);
                imageView.setY(point[1]);
                imageView.bringToFront();
                imageView.invalidate();
            }
        });

        pathAnimator.setRepeatMode(ValueAnimator.INFINITE);
        pathAnimator.setDuration(3000);
        pathAnimator.start();
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