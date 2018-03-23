
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.beans.User;
import by.epam.exceptions.SourceException;
import by.epam.dao.UsersDAOImpl;

@WebServlet("/GetUserServlet")
public class GetUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = null;
        try {
            user = UsersDAOImpl.getUser(login, password);
        } catch (SourceException e) {
            e.printStackTrace();
        }

        String jsonUser = Json.getUser(user);
        out.println(jsonUser);
    }

}  