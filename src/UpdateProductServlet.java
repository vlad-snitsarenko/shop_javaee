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

@WebServlet("/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
    private String jsonResponse;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String title = request.getParameter("title");
        int cat_id = Integer.parseInt(request.getParameter("id_category"));
        String descr = request.getParameter("description");
        String image = request.getParameter("image");
        int quant = Integer.parseInt(request.getParameter("quantity"));
        String brand = request.getParameter("brand");
        String screen = request.getParameter("screen");
        String os = request.getParameter("os");
        String storage = request.getParameter("storage");
        int p_id = Integer.parseInt(request.getParameter("p_id"));
        int id_pr = Integer.parseInt(request.getParameter("id_pr"));
        Product product = new Product(title, descr, image, cat_id, quant, brand, screen, os, storage);
        product.setP_id(p_id);
        product.setId(id_pr);
        try {
            ProductsImpl.updateProduct(product);
            jsonResponse = "{\"status\" : \"Product successfully changed\"}";

        } catch (SourceException e) {
            e.printStackTrace();
        }
        out.print(jsonResponse);
    }

}
