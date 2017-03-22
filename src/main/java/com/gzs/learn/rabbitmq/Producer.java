package com.gzs.learn.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
	public static final String QUEUE_NAME = "test";
	public static final String HOST = "localhost";

	public Producer() {

	}

	public void send() {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		Connection connection = null;
		Channel channel = null;
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			String message = "hello world!";
			for (int i = 0; i < 10; i++) {
				channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
			}
			System.out.println("Producer send message:" + message);
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

	public static void main(String[] args) {
		Producer producer = new Producer();
		producer.send();
	}
}
