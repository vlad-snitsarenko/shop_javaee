import by.epam.beans.Order;
import by.epam.dao.OrdersDAOImpl;
import by.epam.exceptions.SourceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {

    private String jsonResponse;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String login = request.getParameter("login");
        int id_product = Integer.parseInt(request.getParameter("id_product"));
        String date = request.getParameter("date");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String total = request.getParameter("total");

        Order order = new Order(login, id_product, date, quantity, total);
        try {
            OrdersDAOImpl.addOrder(order);
            jsonResponse = "{\"status\" : \"OK\"}";

        } catch (SourceException e) {
            e.printStackTrace();
        }
        out.print(jsonResponse);
    }
}
