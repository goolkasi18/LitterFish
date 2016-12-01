package com.example.wills.bouncingballactivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Will's on 1/23/2016.
 */
class CustomDrawableView1 extends View implements SensorEventListener {

    public static float xPosition, xAcceleration,xVelocity = 0.0f;
    public static float yPosition, yAcceleration,yVelocity = 0.0f;
    public float xmax,ymax;
    private Bitmap mBitmap;
    private SensorManager sensorManager = null;
    public float frameTime = 0.666f;
    public static float left, right, top, bottom;
    public static Rect R1 = new Rect(0,0,0,0);
    public static boolean kill1 = false;

    public CustomDrawableView1(Context context, AttributeSet attrs){
        super(context, attrs);
        Bitmap fish = BitmapFactory.decodeResource(getResources(), R.drawable.fish);
        final int dstWidth = 115;
        final int dstHeight = 150;
        mBitmap = Bitmap.createScaledBitmap(fish, dstWidth, dstHeight, true);
        sensorManager = (SensorManager)context.getSystemService(context.SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);

        kill1 = false;
        xmax = 1898.0f;
        ymax = 1290.0f;

        xPosition = xmax/2;
        yPosition = ymax/2;
    }

    protected void onDraw(Canvas canvas)
    {
        if(!kill1) {
            double angle = (-1) * Math.atan2(xAcceleration, yAcceleration) / (Math.PI / 180);
            canvas.drawBitmap(RotateBitmap(mBitmap, (float) angle), xPosition, yPosition, null);
            invalidate();
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent)
    {
        {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ORIENTATION) {
                //Set sensor values as acceleration
                yAcceleration = -sensorEvent.values[2];
                xAcceleration = sensorEvent.values[1];
                //Log.i("xAcc:", String.valueOf(xAcceleration));
               // Log.i("yAcc:", String.valueOf(yAcceleration));

                if(Math.abs(yAcceleration) >= Math.abs(xAcceleration))
                {
                    if(yAcceleration < 0) //device is "up"
                    {
                        left = xPosition;
                        top = yPosition+45;
                        bottom = yPosition+160;
                        right = xPosition+115;
                    }
                    else //device is front "down"
                    {
                        left = xPosition;
                        right = xPosition+115;
                        top = yPosition;
                        bottom = yPosition+115;
                    }
                }
                else if(Math.abs(yAcceleration) < Math.abs(xAcceleration))
                {
                    if(xAcceleration < 0) //device is tilted to the right
                    {
                        left = xPosition+45;
                        right = xPosition+160;
                        top = yPosition;
                        bottom = yPosition+115;
                    }
                    else //device is tilted to the left
                    {
                        left = xPosition;
                        right = xPosition+115;
                        top = yPosition;
                        bottom = yPosition+115;
                    }
                }

                updateBall();
                R1 = new Rect((int)left,(int)top,(int)right,(int)bottom);
                Level1.tick(R1);

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
