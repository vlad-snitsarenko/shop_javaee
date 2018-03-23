import by.epam.beans.Product;
import by.epam.dao.ProductsImpl;
import by.epam.exceptions.SourceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/FilterProductsServlet")
public class FilterProductsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int id = Integer.parseInt(request.getParameter("id_cat"));
        String[] brands = request.getParameter("brands").split(";");
        String[] screen = request.getParameter("screen").split(";");
        String[] os = request.getParameter("os").split(";");
        String[] storage = request.getParameter("storage").split(";");

        List<Product> products;

        try {
            products = ProductsImpl.getFilteredProducts(id, brands, screen, os, storage);

        } catch (SourceException e) {
            products = new ArrayList<>();
        }
        String jsonResponse;
        if (products.isEmpty()) {
            jsonResponse = "{\"status\" : \"false\"}";
        }
        jsonResponse = Json.getProducts(products);

        out.println(jsonResponse);
    }
}

