package mq.helloworld;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.region.policy.DeadLetterStrategy;

import javax.jms.*;

/**
 * Created by Mark Tao on 2016/9/13 20:49.
 */


public class Receiver {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER, ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://localhost:61616");


        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue("queue1");

        MessageConsumer messageConsumer = session.createConsumer(destination);

        while (true) {
            TextMessage msg = (TextMessage) messageConsumer.receive();
            if (msg == null) {
                break;
            }
            System.out.println("收到任务: "+msg.getText());
        }


        if (connection != null) {

            connection.close();
        }

    }
}
