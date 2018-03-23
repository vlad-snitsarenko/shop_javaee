import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epam.exceptions.SourceException;
import by.epam.dao.UsersDAOImpl;


@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String jsonResponse;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String login = request.getParameter("login");

        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");

        try {
            if (UsersDAOImpl.isLoginExist(login)) {
                jsonResponse = "{\"error\" : \"Login exists\"}";
            } else {
                UsersDAOImpl.addUser(login, password, email, dob);
                jsonResponse = "{\"status\" : \"OK\"}";
            }
        } catch (SourceException e) {
            e.printStackTrace();
        }
        out.print(jsonResponse);
    }

}  