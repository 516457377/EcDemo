package com.example.androidtestl.act;

import com.example.androidtestl.R;
import com.example.androidtestl.act.Server1.MsgBack;
import com.example.androidtestl.act.Server1.MyBind;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity1 extends Activity implements ServiceConnection {

    private Button btn_start, btn_stop, btn_bind, btn_unbind,btn_send;
    Button bt_t;
    TextView tv_t;
    private MyBind serviceBind;
    

    TelephonyManager Tel;
 
    //MyPhoneStateListener类的对象，即设置一个监听器对象
    MyPhoneStateListener MyListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);
        initView(); 
        //初始化对象
        MyListener = new MyPhoneStateListener();
        //Return the handle to a system-level service by name.通过名字获得一个系统级服务
        Tel = ( TelephonyManager )getSystemService(Context.TELEPHONY_SERVICE);
        //Registers a listener object to receive notification of changes in specified telephony states.设置监听器监听特定事件的状态
        Tel.listen(MyListener ,PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);


    }

    private void initView() {
        btn_start = findViewById(R.id.btn_start);
        btn_stop = findViewById(R.id.btn_stop);
        btn_bind = findViewById(R.id.bind);
        btn_unbind = findViewById(R.id.unbind);
        btn_send = findViewById(R.id.send_msg);
        bt_t = findViewById(R.id.bt_t);
        tv_t = findViewById(R.id.tv_t);
        tv_t.bringToFront();
    }

    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btn_start:
            startService(new Intent(Activity1.this, Server1.class));
            break;

        case R.id.btn_stop:
            stopService(new Intent(Activity1.this, Server1.class));
            break;
            
        case R.id.bind:
            bindService(new Intent(Activity1.this, Server1.class), this, Context.BIND_AUTO_CREATE);
            break;
            
        case R.id.unbind:
            unbindService(this);
            break;
            
        case R.id.send_msg:
            if (serviceBind != null) {
                serviceBind.setData("send mssg");
            }
            break;
            
        case R.id.bt_t:
            tv_t.bringToFront();
            break;
            
        case R.id.tv_t:
            bt_t.bringToFront();
            break;
            
        default:
            break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName arg0, IBinder arg1) {
        serviceBind = (MyBind) arg1;
        serviceBind.setOnMsgBack(new MsgBack() {
            
            @Override
            public void onMsg(String msg) {
                Log.d("activity1", "get service msg:"+msg);
            }
        });
        
    }

    @Override
    public void onServiceDisconnected(ComponentName arg0) {
        serviceBind = null;
    }
    
    
    //监听器类
    private class MyPhoneStateListener extends PhoneStateListener{
        /*得到信号的强度由每个tiome供应商,有更新*/
//        TextView myText = (TextView)findViewById(R.id.myText);
//        TextView myText1=(TextView)findViewById(R.id.myText1);
        @Override
 
        public void onSignalStrengthsChanged(SignalStrength signalStrength){
            //调用超类的该方法，在网络信号变化时得到回答信号
            super.onSignalStrengthsChanged(signalStrength);
 
            //cinr：Carrier to Interference plus Noise Ratio（载波与干扰和噪声比）
            Toast.makeText(getApplicationContext(), "= "+ String.valueOf(signalStrength.getGsmSignalStrength())+"+"+signalStrength.getCdmaDbm(), Toast.LENGTH_SHORT).show();
//            myText.setText("CDMA RSSI = "+ String.valueOf(signalStrength.getCdmaDbm()));
//            myText1.setText("GSM Cinr = "+ String.valueOf(signalStrength.getGsmSignalStrength()));
        }}


}
