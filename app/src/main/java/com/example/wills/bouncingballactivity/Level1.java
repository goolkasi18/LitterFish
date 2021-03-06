package com.example.wills.bouncingballactivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Level1 extends Activity{

    public static int badies = 5;
    public static int goodies = 1;
    public static int goal = goodies;
    public static int collected = 0;
    public static ImageView[] bads, goods, food;
    public static RelativeLayout WinScreen, LoseScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_level1);

        bads = new ImageView[badies];
        goods = new ImageView[goodies];
        food = new ImageView[goal];

        for(int i=0;i<badies;i++)
            bads[i] = (ImageView)((RelativeLayout) findViewById(R.id.badies1)).getChildAt(i);
        for(int i=0;i<goodies;i++)
            goods[i] = (ImageView)((RelativeLayout) findViewById(R.id.goodies1)).getChildAt(i);
        for(int i=0;i<goal;i++)
            food[i] = (ImageView)((RelativeLayout) findViewById(R.id.foods1)).getChildAt(i);

        WinScreen = (RelativeLayout)findViewById(R.id.WinScreen);
        LoseScreen = (RelativeLayout)findViewById(R.id.LoseScreen);

        collected = 0;
    }

    protected static void tick(Rect R1)
    {

        int j=0;
        int k=0;
        for(int i = 0; i<badies; i++){
            Rect R2 = new Rect(bads[i].getLeft(), bads[i].getTop(), bads[i].getRight(), bads[i].getBottom());
            if(R1.intersect(R2)) {
                CustomDrawableView1.kill1=true;
                LoseScreen.setVisibility(View.VISIBLE);
            }
        }
        for(int i = 0; i<goodies; i++){
            Rect R2 = new Rect(goods[i].getLeft(), goods[i].getTop(), goods[i].getRight(), goods[i].getBottom());
            if(R1.intersect(R2)) {
                goods[i].setVisibility(View.GONE);
                food[i].setVisibility(View.GONE);
                collected++;
            }
           if(collected==goal)
               WinScreen.setVisibility(View.VISIBLE);
        }

    }

    public void toLevelSelect(View view){
        finish();
    }

    public void rePlay(View view){
        Intent replay = new Intent(this, Level1.class);
        startActivity(replay);
        finish();
    }

}
