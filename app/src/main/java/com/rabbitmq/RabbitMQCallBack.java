package com.rabbitmq;

/**
 * Created by Administrator on 2017/11/11.
 */
public interface RabbitMQCallBack {
    void onSuccess(String msg);

    void onFail(String error);

}
