import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.jms.*;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

@WebServlet("/ServletProducer")
public class ServletProducer extends HttpServlet {
    @Resource(lookup = "jms/__defaultConnectionFactory")
    javax.jms.ConnectionFactory factory;
    @Resource(lookup = "jms/queueDestination")
    Destination queue;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message=request.getParameter("msg");
        try (JMSContext context = factory.createContext("admin", "admin")) {
            JMSProducer producer = context.createProducer();
            producer.send(queue, message);
        }
        response.sendRedirect("index.jsp");
    }
}
