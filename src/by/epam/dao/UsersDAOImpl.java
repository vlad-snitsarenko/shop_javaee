package by.epam.dao;

import by.epam.beans.User;
import by.epam.db.ConnectionManager;
import by.epam.exceptions.SourceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAOImpl {


    private final static String SQL_INSERT_USER =
            "INSERT INTO users(login, password, email, dob) VALUES(?, ?, ?, ?)";

    private final static String SQL_CHECK_LOGIN =
            "SELECT login FROM users WHERE login = ?";

    private final static String SQL_GET_USER =
            "SELECT users.login, users.password, users.email, users.role, users.dob " +
                    "FROM users " +
                    "WHERE login = ? AND password = ?";

    private final static String SQL_GET_ALL_USERS =
            "SELECT users.login, users.password, users.email, roles.name, users.dob, gender.name " +
                    "FROM users " +
                    "Inner Join roles ON users.role = roles.id_role " +
                    "Inner Join gender ON users.id_gender = gender.id_gender ";

    public static int addUser(String login, String password, String email, String dob)
            throws SourceException {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cn = ConnectionManager.getConnection();
            ps = cn.prepareStatement(SQL_INSERT_USER);
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, dob);


            return ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new SourceException(e);
        } finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(ps);
            ConnectionManager.closeConn(cn);
        }
    }

    public static User getUser(String login, String password) throws SourceException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            cn = ConnectionManager.getConnection();
            ps = cn.prepareStatement(SQL_GET_USER);
            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getString("login"), rs.getString("password"), rs.getString("email"), rs.getInt("role"), rs.getString("dob"));
            }
            return user;
        } catch (SQLException | ClassNotFoundException e) {
            throw new SourceException(e);
        } finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(ps);
            ConnectionManager.closeConn(cn);
        }
    }

    public static boolean isLoginExist(String login) throws SourceException {
        boolean flag = false;
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = ConnectionManager.getConnection();
            ps = cn.prepareStatement(SQL_CHECK_LOGIN);

            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
            }
            return flag;
        } catch (SQLException | ClassNotFoundException e) {
            throw new SourceException(e);
        } finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(ps);
            ConnectionManager.closeConn(cn);
        }
    }

    public List<User> getAllUsers() throws SourceException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<User>();
        User user = null;
        try {
            cn = ConnectionManager.getConnection();
            ps = cn.prepareStatement(SQL_GET_ALL_USERS);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getString("login"), rs.getString("password"), rs.getString("email"), rs.getString("dob"));
                userList.add(user);
            }
            return userList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new SourceException(e);
        } finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(ps);
            ConnectionManager.closeConn(cn);
        }
    }
}
