package by.epam.db;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String className = "org.gjt.mm.mysql.Driver";
        String dbUrl = "jdbc:mysql://localhost:3306/shop_db";
        final String user = "root";
        final String password = "root";

        Class.forName(className);
        return DriverManager.getConnection(dbUrl, user, password);
    }

    public static void closeConn(Connection cn) {
        try {
            if ((cn != null) && (!cn.isClosed()))

            {
                cn.close();
            }
        } catch (SQLException e) {
            System.err.println("Resource closing problem : " + e);

        }
    }

    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                System.err.println("Resource closing problem : " + e);
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if ((rs != null)) {
                rs.close();
            }
        } catch (SQLException e) {
            System.err.println("Resource closing problem : " + e);
        }
    }

}