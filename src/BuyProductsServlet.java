import by.epam.dao.OrdersDAOImpl;
import by.epam.dao.ProductsImpl;
import by.epam.exceptions.SourceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/BuyProductsServlet")
public class BuyProductsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String jsonResponse;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id_product"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String login = request.getParameter("login");
        try {
            ProductsImpl.buyProduct(id, quantity);
            OrdersDAOImpl.changeOrderStatus(id, login);
            jsonResponse = "{\"status\" : \"Product successfully added\"}";
        } catch (SourceException e) {
            e.printStackTrace();
        }
        out.print(jsonResponse);
    }


}


