package com.hkrt.aidlservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Toast;

/**
 * @author chenjinguang
 */
public class MainActivity extends AppCompatActivity {

    private UserAidl mUserAidl;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mUserAidl = UserAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this,MessageService.class));

        Intent intent = new Intent(this,MessageService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void getUserName(View view) throws RemoteException {
        Toast.makeText(this,mUserAidl.getUserName(),Toast.LENGTH_SHORT).show();
    }

    public void getUserPwd(View view) throws RemoteException {
        Toast.makeText(this,mUserAidl.getUserPassword(),Toast.LENGTH_SHORT).show();
    }
}
