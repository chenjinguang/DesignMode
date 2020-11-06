package com.hkrt.servicekeepalive;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

/**
 * @author chenjinguang
 * @描述
 * @创建时间 2020/11/6
 * @修改人和其它信息
 */
public class GuardService extends Service{

    private final int GuardId = 1;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 提高进程优先级
        startForeground();

        // 绑定简历链接
        bindService(new Intent(this,MessageService.class),mServiceConnection, Context.BIND_IMPORTANT);

        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Log.e("TAG","进程守护中。。。");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ProcessConnection.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }
        };
    }


    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 链接上
            Toast.makeText(GuardService.this, "建立连接", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 断开链接 ,重新启动，重新绑定
            startService(new Intent(GuardService.this,MessageService.class));

            bindService(new Intent(GuardService.this, MessageService.class),
                    mServiceConnection, Context.BIND_IMPORTANT);
        }
    };

    /**
     * 设置服务在前台可见
     */
    private void startForeground(){
        /**
         * 通知栏点击跳转的intent
         */
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0, new Intent(
                this, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);

        /**
         * 创建Notification
         */
        NotificationChannel notificationChannel;
        Notification notification;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            notificationChannel= new NotificationChannel(GuardId + "",
                    GuardId+"", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setShowBadge(true);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(notificationChannel);

            notification = new NotificationCompat.Builder(this, GuardId + "")
                    .setContentTitle("title")
                    .setContentText("contentText")
                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setContentIntent(pendingIntent)
                    .build();
        }else{
            notification = new Notification.Builder(this)
                    .setContentTitle("title")
                    .setContentText("contentText")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent)
                    .build();
        }

        /**
         * 设置notification在前台展示
         */
        startForeground(23, notification);
    }
}
