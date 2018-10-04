package jms_pubSub;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;

public class Sub {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (JMSContext context = connectionFactory.createContext("admin", "admin",JMSContext.AUTO_ACKNOWLEDGE)) {
            connectionFactory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");//устанавливем свойство
            Destination topic = context.createTopic("Topic1");
            //не долговременная
            String selector = "aa=aa";//SELECTOR(FILTR)
            JMSConsumer consumer = context.createConsumer(topic,selector);
            while (true) {
                String message = consumer.receiveBody(String.class);
                System.out.println("Сообщение: " + message);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
