package jms_2_0;
import javax.jms.*;
import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import java.util.Scanner;

public class Producer {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try(JMSContext context = connectionFactory.createContext("admin","admin")){
            connectionFactory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            Destination queue = context.createQueue("QueueJMS3");
            JMSProducer producer = context.createProducer();
            //System.out.print("Введите сообщение: ");
            //String message = in.nextLine();
            //producer.send(queue, message);
            Student student1 = new Student(1,"Alexey");
            ObjectMessage objectMessage = context.createObjectMessage(student1);
            producer.send(queue,objectMessage);
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
