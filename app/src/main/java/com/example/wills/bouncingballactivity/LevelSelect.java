package com.example.wills.bouncingballactivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class LevelSelect extends Activity {

    Intent level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_level_select);
    }

    public void level1(View view){
        level = new Intent(this, Level1.class);
        findViewById(R.id.Level1Popup).setVisibility(View.VISIBLE);
    }

    public void level2(View view){
        level = new Intent(this, Level2.class);
        findViewById(R.id.Level2Popup).setVisibility(View.VISIBLE);
    }

    public void level3(View view){
        level = new Intent(this, Level3.class);
        findViewById(R.id.Level3Popup).setVisibility(View.VISIBLE);
    }

    public void dismissPopup(View view){
       view.setVisibility(View.GONE);
        startActivity(level);
        finish();
    }
}
