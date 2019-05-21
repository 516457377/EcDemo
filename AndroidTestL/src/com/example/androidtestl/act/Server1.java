package com.example.androidtestl.act;

import com.test.aidl.AidlInterface;
import com.test.aidl.CallBack;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class Server1 extends Service {
    
    private static final String TAG = "Server1";
    
    private String data = "no msg";
    private MsgBack msgBack ;
    
    private CallBack mBack;
    
    public class MyBind extends Binder{
        public void setData(String msg){
            Server1.this.data = msg;
//            Toast.makeText(Server1.this, "String", Toast.LENGTH_SHORT).show();
            setLog(msg);
        }
        
        public void setOnMsgBack(MsgBack msgBack){
            Server1.this.msgBack = msgBack; 
        }
    }
    
    public interface MsgBack {
        void onMsg(String msg);
    }
    
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        Log.d(TAG, "creat");
        super.onCreate();
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        Log.d(TAG, "start command");
        return super.onStartCommand(intent, flags, startId);
    }
    
    @Override
    public IBinder onBind(Intent arg0) {
        Log.d(TAG, "bind");
        return aidl;
//        return new MyBind();
    }
    
    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        Log.d(TAG, "destroy");
        super.onDestroy();
    }
    
    public void setLog(String log) {
        if (msgBack != null) {
            msgBack.onMsg(log);
        }
        
        if(mBack != null){
            try {
                mBack.getMsg(log);
                Log.d("Service1", "发送了一个回调");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        Log.d("Service1", "收到消息：" + log);
    }
    
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "unbind");
        return super.onUnbind(intent);
    }
    
    private AidlInterface.Stub aidl = new AidlInterface.Stub() {
        
        @Override
        public void setOnCallBack(CallBack callBack) throws RemoteException {
            Server1.this.mBack = callBack;
            Log.d("Service1", "设置回调成功");
        }
        
        @Override
        public void setMsg(String msg) throws RemoteException {
            Server1.this.data = msg;
            setLog(msg);
        }
    };

}
