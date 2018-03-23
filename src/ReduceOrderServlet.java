import by.epam.dao.OrdersDAOImpl;
import by.epam.exceptions.SourceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ReduceOrderServlet")
public class ReduceOrderServlet extends HttpServlet {

    private String jsonResponse;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        int id_product = Integer.parseInt(request.getParameter("id_product"));
        String login = request.getParameter("login");

        try {
            OrdersDAOImpl.reduceOrder(login, id_product);
            jsonResponse = "{\"status\" : \"OK\"}";

        } catch (SourceException e) {
            e.printStackTrace();
        }
        out.print(jsonResponse);
    }
}




