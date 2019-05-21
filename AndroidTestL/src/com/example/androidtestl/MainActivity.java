package com.example.androidtestl;

import com.example.androidtestl.act.Activity1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
    public static final String []defaultList = new String[]{"com.android.browser", "com.qiyi.video.pad", "com.qiyi.video","com.netflix.mediaclient",
            "com.google.android.youtube.tv", "com.google.android.youtube", "com.tencent.qqlive", "com.ktcp.video",
            "com.cibn.tv", "com.youku.phone", "com.letv.android.client", "com.tudou.android", "com.sohu.sohuvideo", "com.google.android.gms","com.android.vending"
            , "com.tencent.map", "com.tencent.mm", "com.tencent.qqlive", "com.tencent.news","com.tencent.qqmusiccar", "com.tencent.qqlivepad"};
    
    private static final String apiServer = "expand.video.iqiyi.com";
    private static final String apiKey = "71c300df4a7f4e89a43d8e19e5458e6f";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);
        setContentView(R.layout.activity_main);
       int x= getWindow().getDecorView().getId();
       Toast.makeText(MainActivity.this, "String"+x, Toast.LENGTH_SHORT).show();
        for (String s : defaultList) {
            if (isPkgInstalled(this, s)) {
                Log.d("test_L", s+":has");
            }else{
                Log.e("test_L", s+":no has");
            }
        }
        
    }

    @SuppressLint("NewApi")
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.button1:
//            getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE);
//            ((Button)v).setText("INVISIBLE");
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            
            break;
        case R.id.button2:
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE); 
            ((Button)v).setText("SYSTEM_UI_FLAG_VISIBLE");
            break;
        case R.id.button3:
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

            break;
        case R.id.button4:
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            break;
        case R.id.button5:
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
            break;
        case R.id.button6:
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_LAYOUT_FLAGS);
            break;
        case R.id.button7:
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            break;
        case R.id.button8:
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
            break;
        case R.id.button9:
          getWindow().getDecorView().setBackgroundColor(0xff334455);//设置当前view页面背景颜色
          getWindow().getStatusBarColor();
//          取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
          getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
          //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
          getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
          getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
          getWindow().addFlags(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
          getWindow().addFlags(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
          //设置状态栏颜色
//          statusBarView.setBackgroundColor(0xff334455);
//          decorViewGroup.addView(statusBarView);
          getWindow().setStatusBarColor(0xff334455);
//          getWindow().setNavigationBarColor(0xff334455);
          Toast.makeText(MainActivity.this, "String", Toast.LENGTH_SHORT).show();
            break;
        case R.id.btn_act:
            startActivity(new Intent(MainActivity.this, Activity1.class));
            break;
        case R.id.btn_act2:
            
            break;
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public static boolean isPkgInstalled(Context context, String pkgName) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(pkgName, 0);
        } catch (NameNotFoundException e) {
            packageInfo = null;
//            e.printStackTrace();
        }
        if (packageInfo == null) {
            return false;
        } else {
            return true;
        }
    }
}
