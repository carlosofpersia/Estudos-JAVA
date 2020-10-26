package br.com.alura.ecommerce;

import br.com.alura.ecommerce.dispatcher.KafkaDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderServlet extends HttpServlet {

    private final KafkaDispatcher<Order> orderDispatcher = new KafkaDispatcher<>();
    private final KafkaDispatcher<Email> emailDispatcher = new KafkaDispatcher<>();

    @Override
    public void destroy() {
        orderDispatcher.close();
        emailDispatcher.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

                try {
                    // var email = "cadu_" + Math.random() + "@email.com";
                    // var amount = new BigDecimal(Math.random() * 5000 + 1);

                    var email = req.getParameter("email");
                    var amount = new BigDecimal(req.getParameter("amount"));

                    var orderId = UUID.randomUUID().toString();

                    var order = new Order(orderId, amount, email);
                    orderDispatcher.send("ECOMMERCE_NEW_ORDER"
                            , email
                            , new CorrelationId(NewOrderServlet.class.getSimpleName())
                            , order);

                    var emailCode = new Email("carlosofpersia@hotmail.com", "Thank you for your order! We are processing your order!");
                    emailDispatcher.send("ECOMMERCE_SEND_EMAIL"
                            , email
                            , new CorrelationId(NewOrderServlet.class.getSimpleName())
                            , emailCode);

                    System.out.println("New order sent sucessfully.");

                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().println("New order sent sucessfully.");

                } catch (ExecutionException e) {
                    throw new ServletException(e);
                } catch (InterruptedException e) {
                    throw new ServletException(e);
                }


    }
}
