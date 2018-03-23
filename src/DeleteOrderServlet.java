import by.epam.exceptions.SourceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static by.epam.dao.OrdersDAOImpl.deleteOrder;

@WebServlet("/DeleteOrderServlet")
public class DeleteOrderServlet extends HttpServlet {

    private String jsonResponse;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        int id_order = Integer.parseInt(request.getParameter("id_order"));

        try {
            deleteOrder(id_order);
            jsonResponse = "{\"status\" : \"OK\"}";

        } catch (SourceException e) {
            e.printStackTrace();
        }
        out.print(jsonResponse);
    }
}


