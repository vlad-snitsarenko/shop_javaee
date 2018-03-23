import by.epam.dao.ProductsImpl;
import by.epam.exceptions.SourceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/UpdateRatingServlet")
public class UpdateRatingServlet extends HttpServlet {

    private String jsonResponse;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int id_product = Integer.parseInt(request.getParameter("id_product"));
        double rating = Double.parseDouble(request.getParameter("rating"));
        try {
            ProductsImpl.updateRating(id_product, rating);
            jsonResponse = "{\"status\" : \"OK\"}";

        } catch (SourceException e) {
            e.printStackTrace();
        }
        out.print(jsonResponse);
    }
}
