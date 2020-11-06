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
 * @创建时间 2020/11/5
 * @修改人和其它信息
 */
public class MessageService extends Service {

    String channelId = "11";

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // Toast
            Toast.makeText(MessageService.this,"建立连接",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //断开连接，重新启动，重新绑定

            startService(new Intent(MessageService.this,MessageService.class));

            bindService(new Intent(MessageService.this,MessageService.class),serviceConnection, Context.BIND_IMPORTANT);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Log.e("TAG","等待接受消息");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //提高进程优先级
//        startForeground();

        bindService(new Intent(this,GuardService.class),serviceConnection,Context.BIND_IMPORTANT);

        return START_STICKY;
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
            notificationChannel= new NotificationChannel(channelId,
                    channelId, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setShowBadge(true);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(notificationChannel);

            notification = new NotificationCompat.Builder(this, channelId)
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

//    private void startForeground() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            createNotificationChannel("my_service", "My Background Service");
//        }
//
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId );
//        Notification notification = notificationBuilder.setOngoing(true)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setPriority(PRIORITY_MIN)
//                .setCategory(Notification.CATEGORY_SERVICE)
//                .build();
//        startForeground(101, notification);
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    private String createNotificationChannel( String channelId,  String channelName){
//        NotificationChannel chan = new NotificationChannel(channelId,
//                channelName, NotificationManager.IMPORTANCE_NONE);
//        chan.setLightColor(Color.BLUE);
//        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
//        NotificationManager service = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        service.createNotificationChannel(chan);
//        return channelId;
//    }
}
