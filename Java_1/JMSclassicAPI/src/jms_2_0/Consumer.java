package jms_2_0;
import javax.jms.*;
import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;
//синхронно
public class Consumer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try(JMSContext context=connectionFactory.createContext("admin","admin"))
        {
            connectionFactory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            Destination queue = context.createQueue("QueueJMS3");
            JMSConsumer consumer = context.createConsumer(queue);
            while (true) {
                Student student=consumer.receiveBody(Student.class);
                System.out.println("синхронно сообщение получено: "+student.id+" "+student.name);
            }
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
