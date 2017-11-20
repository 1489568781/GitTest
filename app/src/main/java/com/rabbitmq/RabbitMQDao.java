package com.rabbitmq;

import android.content.Context;

/**
 * Created by Administrator on 2017/11/10.
 */
public interface RabbitMQDao {

    void subscribe(String[] routingKeys, Context context, String exchangeName, RabbitMQCallBack callBack);

    void produce(String[] routingKeys, Context context, String exchangeName, String content, RabbitMQCallBack callBack);

    void onCloseThread();
}
