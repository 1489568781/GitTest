package com.rabbitmq;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.gittest.R;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/11/8.
 */
public class RabbitMQActivity extends AppCompatActivity {
    private TextView tv_chat_history;
    private EditText et_myID, et_friendID, et_content;
    private final static String QUEUE_NAME = "hello";
    private final static String EXCHANGE_NAME = "chatting";
    NoticeListener listener = new NoticeImpl();
    private int i = 0;

    private String[] subRoutingKeys, proRoutingKeys;
    private RabbitMQDao rabbitMQDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rabbit_mq_activity);
        tv_chat_history = (TextView) findViewById(R.id.tv_chat_history);
        et_myID = (EditText) findViewById(R.id.et_myID);
        et_friendID = (EditText) findViewById(R.id.et_friendID);
        et_content = (EditText) findViewById(R.id.et_content);
        rabbitMQDao = new RabbitMQDaoImpl();
//        listener.showNotice(RabbitMQActivity.this, "info:kk");

    }

    private String str_content_show = "";

    //绑定
    public void onBindID(View view) {
        subRoutingKeys = new String[]{et_myID.getText().toString().trim()};
        rabbitMQDao.subscribe(subRoutingKeys, getApplicationContext(), EXCHANGE_NAME, new RabbitMQCallBack() {
            @Override
            public void onSuccess(final String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "接收到消息：" + msg, Toast.LENGTH_SHORT).show();
                        tv_chat_history.append(msg);
                    }
                });
//                str_content_show = msg;
//                tv_chat_history.setText(msg);
            }

            @Override
            public void onFail(final String error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "接收到消息：" + error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //发送
    public void onSend(View view) {

        proRoutingKeys = new String[]{et_friendID.getText().toString().trim()};
        rabbitMQDao.produce(proRoutingKeys, getApplicationContext(), EXCHANGE_NAME, et_content.getText().toString().trim(), new RabbitMQCallBack() {
            @Override
            public void onSuccess(final String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "发送消息：" + msg, Toast.LENGTH_SHORT).show();
                        tv_chat_history.append(msg);
                    }
                });

            }

            @Override
            public void onFail(final String error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "接收到消息：" + error, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rabbitMQDao.onCloseThread();
    }


    public void showNotice(String msg, Context context) {
        Notification.Builder builder = new Notification.Builder(RabbitMQActivity.this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            builder.setSmallIcon(R.mipmap.ic_launcher)
                    .setContentText(msg)
                    .setDefaults(Notification.DEFAULT_ALL);
            Notification notification = builder.build();
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
//            manager.notify(1, notification);
        }
    }
}
