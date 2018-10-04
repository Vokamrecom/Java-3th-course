package jms;

import javax.jms.*;
import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;
//места назнач
public class Consumer {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            JMSContext jmdiComtext = connectionFactory.createContext("admin","admin");
            connectionFactory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            //ConnectionFactory connectionFactory=(ConnectionFactory)jmdiComtext.lookup("jms/javaee7/ConnectionFactory");
            Destination queue=jmdiComtext.createQueue("Queue");
            Connection connection=connectionFactory.createConnection();
            Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);//ПОСЫЛАЕТ ПОДТВЕРЖДЕНИЕ КАК ТОЛЬКО ВЫПОЛНИТСЯ onmessage()
            MessageConsumer consumer=session.createConsumer(queue);
            connection.start();
            while (true){
                TextMessage message=(TextMessage) consumer.receive();
                System.out.println("Сообщение "+message.getText() );
            }
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
