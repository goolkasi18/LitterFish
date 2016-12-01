package com.example.wills.bouncingballactivity;

        import java.util.Timer;
        import java.util.TimerTask;
        import com.example.wills.bouncingballactivity.R;
        import android.app.Activity;
        import android.content.Intent;
        import android.content.pm.ActivityInfo;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Matrix;
        import android.graphics.drawable.ShapeDrawable;
        import android.opengl.Visibility;
        import android.os.Bundle;
        import android.os.Handler;
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
        import android.widget.RelativeLayout;

public class BouncingBallActivity extends Activity{



    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //Set FullScreen & landscape
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_bouncing_ball);

    }

    public void play(View view){
        Intent toLevelSelect = new Intent(this, LevelSelect.class );
        startActivity(toLevelSelect);
        finish();
    }

    public void showSettings(View view){
        findViewById(R.id.SettingsPage).setVisibility(View.VISIBLE);
        findViewById(R.id.Play).setVisibility(View.GONE);
        findViewById(R.id.Settings).setVisibility(View.GONE);
    }

    public void dismissSettings(View view){
        findViewById(R.id.SettingsPage).setVisibility(View.GONE);
        findViewById(R.id.Play).setVisibility(View.VISIBLE);
        findViewById(R.id.Settings).setVisibility(View.VISIBLE);
    }


}