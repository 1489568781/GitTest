package com.rabbitmq;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * Created by Administrator on 2017/11/10.
 */
public class RabbitMQDaoImpl implements RabbitMQDao {
    ConnectionFactory factory = new ConnectionFactory();
    Thread subscribeThread, produceThread;
    public void initConnect() {
        factory.setHost("192.168.1.168");
        factory.setPort(5672);
        factory.setUsername("wxw");
        factory.setPassword("123456");
        factory.setVirtualHost("wxw");
    }

    @Override
    public void subscribe(final String[] routingKeys, Context context, final String exchangeName, final RabbitMQCallBack callBack) {
        initConnect();
        subscribeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        //使用之前的设置，建立连接
                        Connection connection = factory.newConnection();
                        //创建一个通道
                        Channel channel = connection.createChannel();
                        channel.exchangeDeclare(exchangeName, "topic");
                        //一次只发送一个，处理完成一个再获取下一个
//                        channel.basicQos(1);
                        AMQP.Queue.DeclareOk q = channel.queueDeclare();
                        //将队列绑定到消息交换机exchange上
                        //                  queue         exchange              routingKey路由关键字，exchange根据这个关键字进行消息投递。
                        for (String routingKey : routingKeys) {
                            channel.queueBind(q.getQueue(), exchangeName, routingKey);
                        }
                        //创建消费者
                        QueueingConsumer consumer = new QueueingConsumer(channel);
                        channel.basicConsume(q.getQueue(), true, consumer);

                        while (true) {
                            //wait for the next message delivery and return it.
                            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                            String message = new String(delivery.getBody());
                            callBack.onSuccess("朋友：" + message.toString() + "\n");
                            System.out.println("*************msg**********" + message.toString());
                            Log.d("", "[r] " + message);
                        }
                    } catch (InterruptedException e) {
                        callBack.onFail("发生异常！！");
                        break;
                    } catch (Exception e1) {
                        Log.d("", "Connection broken: " + e1.getClass().getName());
                        try {
                            Thread.sleep(5000); //sleep and then try again
                        } catch (InterruptedException e) {
                            break;
                        }
                        callBack.onFail("发生异常！！");
                    }
                }
            }
        });
        subscribeThread.start();
    }

    @Override
    public void produce(final String[] routingKeys, final Context context, final String exchangeName, final String content, final RabbitMQCallBack callBack) {
        initConnect();
        produceThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //使用之前的设置，建立连接
                    Connection connection = factory.newConnection();
                    //创建一个通道
                    Channel channel = connection.createChannel();
                    channel.exchangeDeclare(exchangeName, "topic");
                    for (String routingKey : routingKeys) {
                        channel.basicPublish(exchangeName, routingKey, null, content.getBytes("UTF-8"));
                        System.out.println(" [x] Sent '" + content + "'");
                    }
                    callBack.onSuccess("我：" + content + "\n");
                    channel.close();
                    connection.close();
                } catch (Exception e) {
                    callBack.onFail("发送异常！！");
                    e.printStackTrace();
                }
            }
        });
        produceThread.start();
    }

    @Override
    public void onCloseThread() {
        produceThread.interrupt();
        subscribeThread.interrupt();
    }

}
