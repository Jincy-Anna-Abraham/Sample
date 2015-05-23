package com.example.sample;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private AnimationDrawable frameAnimation;
    private ImageView frameImageView,imageView,balloonImageView1,balloonImageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseViews();
        setWorld();
        setBeeAnimation();
    }

    private void initialiseViews() {
        imageView = (ImageView)findViewById(R.id.imageView);
        balloonImageView1 = (ImageView) findViewById(R.id.ballonImageView1);
        balloonImageView2 = (ImageView) findViewById(R.id.ballonImageView2);
        frameImageView = (ImageView) findViewById(R.id.imageAnimation);
        frameAnimation = (AnimationDrawable) frameImageView.getBackground();
    }

    private void setWorld() {
        setBalloonAnimation(balloonImageView1,"y",600.0f,-100.0f,10000);
        setBalloonAnimation(balloonImageView2,"y",600.0f,-100.0f,8000);
    }

    private void setBalloonAnimation(ImageView imageView,String property,float start,float end,long duration) {
        ObjectAnimator transAnimation= ObjectAnimator.ofFloat(imageView, property, start, end);
        transAnimation.setDuration(duration);//set duration
        transAnimation.setRepeatCount(ValueAnimator.INFINITE);
        transAnimation.start();
    }

    private void setBeeAnimation() {
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
//        bee animation code
//        pathAnimator.setRepeatMode(ValueAnimator.INFINITE);
//        pathAnimator.setDuration(3000);
//        pathAnimator.start();
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