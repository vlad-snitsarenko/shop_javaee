import by.epam.beans.User;
import by.epam.dao.UsersDAOImpl;
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

@WebServlet("/GetUsersServlet")
public class GetUsersServlet extends HttpServlet {

    public GetUsersServlet() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> usersList;
        try {
            UsersDAOImpl usersDAO = new UsersDAOImpl();
            usersList = usersDAO.getAllUsers();
        } catch (SourceException e) {
            usersList = new ArrayList<>();
        }
        String jsonUsers = Json.getUsers(usersList);

        PrintWriter out = response.getWriter();
        out.println(jsonUsers);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String email = request.getParameter("login");
        String password = request.getParameter("password");
        User user = null;
        try {
            user = UsersDAOImpl.getUser(email, password);
        } catch (SourceException e) {
            e.printStackTrace();
        }

        String jsonUser = Json.getUser(user);
        out.println(jsonUser);
    }


}




