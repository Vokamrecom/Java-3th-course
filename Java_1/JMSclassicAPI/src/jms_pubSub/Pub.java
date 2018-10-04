package jms_pubSub;
import javax.jms.*;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import java.util.Scanner;
public class Pub {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try(JMSContext context = connectionFactory.createContext("admin","admin")) {
            connectionFactory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            Destination topic = context.createTopic("Topic1");
            TextMessage outMsg = context.createTextMessage();
            outMsg.setStringProperty("aa", "aa");
            //context.createTextMessage().setJMSPriority(5);
            JMSProducer producer = context.createProducer();
            while (true) {
                System.out.print("Введите сообщение: ");
                String message = in.nextLine();
                outMsg.setText(message);
                producer.setPriority(5).send(topic, outMsg);
            }
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
