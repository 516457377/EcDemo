package com.test.aidl;

import com.test.aidl.CallBack;

interface AidlInterface {
    void setMsg(String msg);
    void setOnCallBack(CallBack callBack);
}
