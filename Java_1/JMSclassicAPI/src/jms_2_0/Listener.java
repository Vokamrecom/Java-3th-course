package jms_2_0;
import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.*;
//асинхронно
public class Listener implements MessageListener{
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try(JMSContext context=connectionFactory.createContext("admin","admin")) {
            connectionFactory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            Destination queue = context.createQueue("QueueJMS3");//создаем адресата
//создать JMS объекты,зарегать listener сообщение
            JMSConsumer consumer = context.createConsumer(queue);//создаем потребитель и подкл
            consumer.setMessageListener(new Listener());//установка для асинхр
            Thread.sleep(100000);// wait for messages(предотвращаем закрытие проги)
        }
        catch (JMSException|InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
           // System.out.println("Асинхронное сообщение получено: "+message.getBody(String.class));
            Student student=message.getBody(Student.class);
            System.out.println("Асинхронное сообщение получено: "+student.id+" "+student.name);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
