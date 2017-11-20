package com.rabbitmq;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.administrator.gittest.R;

/**
 * Created by Administrator on 2017/11/8.
 */
public class NoticeImpl implements NoticeListener {
    @Override
    public void showNotice(Context context, String msg) {
        NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            Notification notification = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("New msg")
                    .setContentText(msg)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .build();
//            Notification notification = builder.build();
//        }
//        notification.icon=R.mipmap.ic_launcher;//通知图标
//        notification.tickerText="来了一条消息:"+msg;//状态栏显示的通知文本提示
//        notification.when=System.currentTimeMillis();//通知产生的时间，会在通知信息里显示
//        notification.flags=Notification.FLAG_AUTO_CANCEL;//点击后消失
//        notification.flags |= Notification.FLAG_SHOW_LIGHTS; //LED灯闪烁
//        notification.defaults |= Notification.DEFAULT_LIGHTS;
//        notification.defaults |= Notification.DEFAULT_SOUND; //发出提示音
//        notification.defaults |= Notification.DEFAULT_VIBRATE;

//        Intent intent=new Intent(this,ShowMainActivity.class);
//        PendingIntent pendingIntent=PendingIntent.getActivity(this,
//                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        notification.setLatestEventInfo(this, "contentTitle", "contentText", pendingIntent);
            manager.notify(1, notification);

        }
    }
}