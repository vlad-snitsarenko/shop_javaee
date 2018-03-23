import by.epam.beans.Order;
import by.epam.dao.OrdersDAOImpl;
import by.epam.exceptions.SourceException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/GetOrdersServlet")
public class GetOrdersServlet extends HttpServlet {

    public GetOrdersServlet() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String login = request.getParameter("login");
        List<Order> orders = null;
        try {
            orders = OrdersDAOImpl.getOrders(login);
        } catch (SourceException e) {
            e.printStackTrace();
        }

        String jsonOrders = Json.getOrders(orders);
        out.println(jsonOrders);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}






