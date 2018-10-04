package jms_pubSub;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.*;

public class SubAs implements MessageListener {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try(JMSContext context=connectionFactory.createContext("admin","admin")) {
            context.setClientID("client02");
            connectionFactory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            //публикация топика
            Destination priceDTopic = context.createTopic("Topic1");
            //долговременная
            JMSConsumer consumer = context.createDurableConsumer((Topic)priceDTopic, "SecurityCenter");
            //АСИНХРОННО
            consumer.setMessageListener(new SubAs());
            System.out.println("Прослушка начата");
            Thread.sleep(10000);
            consumer.close();
            //context.unsubscribe("SecurityCenter");
        }
        catch (JMSException |InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("Асинхронное сообщение получено: "+message.getBody(String.class));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
