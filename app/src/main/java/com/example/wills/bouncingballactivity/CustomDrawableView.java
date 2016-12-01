package com.example.wills.bouncingballactivity;

import java.util.Timer;
import java.util.TimerTask;
import com.example.wills.bouncingballactivity.R;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.SensorEventListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.jar.Attributes;

/**
 * Created by Will's on 1/23/2016.
 */
class CustomDrawableView extends View implements SensorEventListener {

    public static float xPosition, xAcceleration,xVelocity = 0.0f;
    public static float yPosition, yAcceleration,yVelocity = 0.0f;
    public float xmax,ymax;
    private Bitmap mBitmap;
    private SensorManager sensorManager = null;
    public float frameTime = 0.666f;

    public CustomDrawableView(Context context, AttributeSet attrs){
        super(context, attrs);
        Bitmap fish = BitmapFactory.decodeResource(getResources(), R.drawable.fish);
        final int dstWidth = 115;
        final int dstHeight = 150;
        mBitmap = Bitmap.createScaledBitmap(fish, dstWidth, dstHeight, true);
        sensorManager = (SensorManager)context.getSystemService(context.SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);

        xmax = 1898.0f;
        ymax = 1290.0f;

        xPosition = xmax/2;
        yPosition = ymax/2;
    }

    protected void onDraw(Canvas canvas)
    {
        double angle = (-1)*Math.atan2(xAcceleration, yAcceleration)/(Math.PI/180);
        canvas.drawBitmap(RotateBitmap(mBitmap, (float) angle), xPosition, yPosition, null);
        invalidate();
    }

    public void onSensorChanged(SensorEvent sensorEvent)
    {
        {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ORIENTATION) {
                //Set sensor values as acceleration
                yAcceleration = -sensorEvent.values[2];
                xAcceleration = sensorEvent.values[1];

                //mBitmap = RotateBitmap(mBitmap, (float)angle);
                updateBall();
            }
        }
    }

    private void updateBall() {

        xPosition -= xAcceleration;
        yPosition -= yAcceleration;

        if (xPosition > xmax) {
            xPosition = xmax;
            xVelocity = 0;
        } else if (xPosition < 0) {
            xPosition = 0;
            xVelocity = 0;
        }
        if (yPosition > ymax) {
            yPosition = ymax;
            yVelocity = 0;
        } else if (yPosition < 0) {
            yPosition = 0;
            yVelocity = 0;
        }
    }

    public void onAccuracyChanged(Sensor arg0, int arg1)
    {
        // TODO Auto-generated method stub
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

}
