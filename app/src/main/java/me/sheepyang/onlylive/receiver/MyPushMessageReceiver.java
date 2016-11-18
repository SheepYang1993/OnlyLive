package me.sheepyang.onlylive.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import cn.bmob.push.PushConstants;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.activity.MainActivity;
import me.sheepyang.onlylive.utils.MyLog;

/**
 * Created by SheepYang on 2016/11/18 23:45.
 */

public class MyPushMessageReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
            String msg = intent.getStringExtra("msg");
            MyLog.i("客户端收到推送内容：" + msg);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setContentTitle("这就是通知的头")
                    .setTicker("这是通知的ticker")
                    .setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT)) //设置通知栏点击意图
                    .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                    .setSmallIcon(R.mipmap.ic_launcher);
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(2, builder.build());
        }
    }
}
