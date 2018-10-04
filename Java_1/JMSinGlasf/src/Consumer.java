import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import java.util.Hashtable;

public class Consumer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory=new ConnectionFactory();
        try(JMSContext context=connectionFactory.createContext("admin","admin"))
        {
            connectionFactory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            Destination queue=context.createQueue("queueDestination");
            JMSConsumer consumer=context.createConsumer(queue);
            System.out.println("Ожидание сообщения");
            while (true)
            {
                String message=consumer.receiveBody(String.class);
                System.out.println("сообщение получено: "+message);
            }
        }
        catch (JMSException e)
        {
            e.printStackTrace();
        }
    }
}

