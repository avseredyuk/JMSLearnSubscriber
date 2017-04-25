package com.avseredyuk.activemq.pubsub;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;

/**
 * Created by Anton_Serediuk on 4/25/2017.
 */
public class JMSSubscriberHelper {
    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageConsumer consumer;

    public boolean create() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616?trace=true");
        try {
            connection = factory.createConnection("toha", "qwerty");
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = new ActiveMQTopic("topicname");
            consumer = session.createConsumer(destination);

            return true;
        } catch (JMSException e) {
            //todo: log
            System.out.println("create exception");
            e.printStackTrace();
            return false;
        }
    }

    public String receive() {
        try {
            Message msg = consumer.receive();
            if( msg instanceof  TextMessage ) {
                String messageText = ((TextMessage) msg).getText();
                return messageText;
            }
        } catch (JMSException e) {
            //todo: log
            System.out.println("rcv exception");
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        try {
            connection.close();
        } catch (JMSException e) {
            //todo: log
            System.out.println("close exception");
            e.printStackTrace();
        }
    }
}