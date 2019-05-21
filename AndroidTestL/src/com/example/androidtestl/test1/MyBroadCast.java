package com.example.androidtestl.test1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class MyBroadCast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            //获取联网状态的NetworkInfo对象
            NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (info != null) {
                //如果当前的网络连接成功并且网络连接可用
                if (NetworkInfo.State.CONNECTED == info.getState() && info.isAvailable()) {
//                    if (isServiceRunning(context, HttpService.class.getName()) == false) {
////                        AppUtil.Loge("onReceiver", "start service");
//                        context.startService(new Intent(context,HttpService.class));
//                    }
                    if (info.getType() == ConnectivityManager.TYPE_WIFI){
                        Log.e("TAG",  "连上（wifi）");
                        Toast.makeText(context, "wifi", Toast.LENGTH_SHORT).show();
                    }else if (info.getType() == ConnectivityManager.TYPE_MOBILE){
                        Toast.makeText(context, "3g", Toast.LENGTH_SHORT).show();
                        Log.e("TAG",  "连上（移动）");
                    }
                } else {
                    Toast.makeText(context, "断开", Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "断开");
                }
            }  
        }
    }

}
