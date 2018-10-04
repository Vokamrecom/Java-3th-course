package jms;
import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;
import javax.jms.*;
import java.util.Scanner;

public class Producer {
    public static void main(String[] args) {
        try {
            Scanner in=new Scanner(System.in);
            ConnectionFactory connectionFactory = new ConnectionFactory();
            JMSContext jmdiComtext=connectionFactory.createContext("admin","admin");
            connectionFactory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            //ConnectionFactory connectionFactory=(ConnectionFactory)jmdiComtext.lookup("jms/javaee7/ConnectionFactory");
            Destination queue = jmdiComtext.createQueue("Queue");
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue);
            System.out.println("Введите сообщение :");
            String mes = in.nextLine();
            TextMessage message = session.createTextMessage(mes);
            producer.send(message);
            connection.close();
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
