package com.avseredyuk.activemq.pubsub;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        JMSSubscriberHelper helper = new JMSSubscriberHelper();
        if (helper.create()) {
            while (true) {
                String message = helper.receive();
                System.out.println(message);
            }
        }
    }
}
