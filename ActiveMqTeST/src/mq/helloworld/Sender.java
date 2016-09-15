package mq.helloworld;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.region.policy.DeadLetterStrategy;

import javax.jms.*;

/**
 * Created by Mark Tao on 2016/9/13 20:49.
 */


public class Sender {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER, ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://localhost:61616");


        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue("queue1");

        MessageProducer messageProducer = session.createProducer(destination);

        messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        for (int i = 0; i < 5; i++) {

            TextMessage textMessage = session.createTextMessage();

            textMessage.setText("我是 内容 啊！！！");

            messageProducer.send(textMessage);
        }
        if (connection != null) {

            connection.close();
        }

    }
}
