package glassf;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.sun.messaging.ConnectionFactory;

public class Consumer {
    public static void main(String[] args) throws NamingException, JMSException {

        InitialContext jndicontext = new InitialContext();
        ConnectionFactory factory=(ConnectionFactory) jndicontext.lookup("jms/__defaultConnectionFactory");
        Destination queue=(Destination)jndicontext.lookup("jms/queueDestination");
        try(JMSContext context=factory.createContext("admin", "admin"))
        {
            while (true)
            {
                String message=context.createConsumer(queue).receiveBody(String.class);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}



