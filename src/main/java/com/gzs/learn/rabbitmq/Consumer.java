package com.gzs.learn.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Consumer {
	public static final String QUEUE_NAME = "test";
	public static final String HOST = "localhost";

	public Consumer() throws InterruptedException {
		recv();
	}

	public void recv() {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		Connection connection = null;
		Channel channel = null;
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			System.out.println("Waiting for queus message");
			com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					String message = new String(body, "utf8");
					System.out.println("Consumer recv message:" + message);
				}
			};

			channel.basicConsume(QUEUE_NAME, true, consumer);
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		} finally {
			try {
				channel.close();
				connection.close();
			} catch (IOException | TimeoutException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Consumer consumer = new Consumer();
		Thread.sleep(30*1000);
	}
}
