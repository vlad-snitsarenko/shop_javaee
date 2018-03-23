import by.epam.beans.Product;
import by.epam.dao.ProductsImpl;
import by.epam.exceptions.SourceException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/GetProductsServlet")
public class GetProductsServlet extends HttpServlet {

    public GetProductsServlet() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products;
        try {
            products = ProductsImpl.getAllProducts();
        } catch (SourceException e) {
            products = new ArrayList<>();
        }
        String jsonProducts = Json.getProducts(products);

        PrintWriter out = response.getWriter();
        out.println(jsonProducts);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
