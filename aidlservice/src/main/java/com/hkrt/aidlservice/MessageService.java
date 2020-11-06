package com.hkrt.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

/**
 * @author chenjinguang
 * @描述
 * @创建时间 2020/11/5
 * @修改人和其它信息
 */
public class MessageService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    UserAidl.Stub binder = new UserAidl.Stub() {
        @Override
        public String getUserName() throws RemoteException {
            return "陈金广";
        }

        @Override
        public String getUserPassword() throws RemoteException {
            return "chen7091965";
        }
    };
}
