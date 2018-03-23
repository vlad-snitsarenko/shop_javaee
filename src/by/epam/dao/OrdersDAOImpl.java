package by.epam.dao;

import by.epam.beans.Order;
import by.epam.beans.User;
import by.epam.db.ConnectionManager;
import by.epam.exceptions.SourceException;

import javax.xml.transform.Source;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOImpl {
    private final static String SQL_DELETE_ORDER_BY_ID =
            "DELETE  FROM shop_db.`order` where id_order = ?";

    private final static String SQL_REDUCE_ORDER_QUANTITY =
            "UPDATE shop_db.`order` SET quantity = quantity-1 where id_product = ? and login = ?";

    private final static String SQL_GET_ORDERS_BY_LOGIN =
            "SELECT * FROM shop_db.`order` where login = ? and status = 1";

    private final static String SQL_GET_ORDERS_HISTORY =
            "SELECT * FROM shop_db.`order` where login = ? and status = 2";

    private final static String SQL_UPDATE_QUANTITY =
            "UPDATE shop_db.`order` SET quantity = quantity+1, date = ? where id_product = ? and login = ?";

    private final static String SQL_INSERT_ORDER =
            "INSERT INTO shop_db.`order`(login, id_product, date, quantity, total) VALUES(?, ?, ?, ?, ?)";

    private final static String SQL_CHECK_ORDER =
            "SELECT  id_order, login, id_product  FROM shop_db.`order` WHERE login = ? AND id_product = ? and `order`.status = 1";

    private final static String SQL_CHANGE_ORDER_STATUS =
            "UPDATE shop_db.`order` SET status = 2  where id_product = ? and login = ?;";

    public static List<Order> getOrdersHistory(String login) throws SourceException {
        List<Order> orders = new ArrayList<Order>();

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order order = null;
        try {
            cn = ConnectionManager.getConnection();
            ps = cn.prepareStatement(SQL_GET_ORDERS_HISTORY);
            ps.setString(1, login);
            rs = ps.executeQuery();
            while (rs.next()) {
                order = new Order(rs.getInt("id_order"),
                        rs.getString("login"),
                        rs.getInt("id_product"),
                        rs.getString("date"),
                        rs.getInt("quantity"),
                        rs.getString("total"));
                orders.add(order);
            }
            return orders;
        } catch (SQLException | ClassNotFoundException e) {
            throw new SourceException(e);
        } finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(ps);
            ConnectionManager.closeConn(cn);
        }
    }

    public static int changeOrderStatus(int id_product, String login) throws SourceException {
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = ConnectionManager.getConnection();
            ps = cn.prepareStatement(SQL_CHANGE_ORDER_STATUS);
            ps.setInt(1, id_product);
            ps.setString(2, login);

            return ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new SourceException(e);
        } finally {

            ConnectionManager.closeStatement(ps);
            ConnectionManager.closeConn(cn);
        }
    }

    public static int addOrder(Order order) throws SourceException {
        Connection cn = null;
        PreparedStatement ps = null;
        if (!(OrdersDAOImpl.isOrderExist(order.getLogin(), order.getId_product()))) {
            try {
                cn = ConnectionManager.getConnection();
                ps = cn.prepareStatement(SQL_INSERT_ORDER);
                ps.setString(1, order.getLogin());
                ps.setInt(2, order.getId_product());
                ps.setString(3, order.getDate());
                ps.setInt(4, order.getQuantity());
                ps.setString(5, order.getTotal());

                return ps.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                throw new SourceException(e);
            } finally {

                ConnectionManager.closeStatement(ps);
                ConnectionManager.closeConn(cn);
            }
        } else {
            //update quantity
            try {
                cn = ConnectionManager.getConnection();
                ps = cn.prepareStatement(SQL_UPDATE_QUANTITY);
                ps.setString(1, order.getDate());
                ps.setInt(2, order.getId_product());
                ps.setString(3, order.getLogin());

                return ps.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                throw new SourceException(e);
            } finally {

                ConnectionManager.closeStatement(ps);
                ConnectionManager.closeConn(cn);
            }
        }
    }

    public static int reduceOrder(String login, int id_product) throws SourceException {
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = ConnectionManager.getConnection();
            ps = cn.prepareStatement(SQL_REDUCE_ORDER_QUANTITY);
            ps.setInt(1, id_product);
            ps.setString(2, login);

            return ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new SourceException(e);
        } finally {

            ConnectionManager.closeStatement(ps);
            ConnectionManager.closeConn(cn);
        }
    }

    public static boolean isOrderExist(String login, int id_product) throws SourceException {
        boolean flag = false;
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = ConnectionManager.getConnection();
            ps = cn.prepareStatement(SQL_CHECK_ORDER);

            ps.setString(1, login);
            ps.setInt(2, id_product);
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

    public static List<Order> getOrders(String login) throws SourceException {
        List<Order> orders = new ArrayList<Order>();

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order order = null;
        try {
            cn = ConnectionManager.getConnection();
            ps = cn.prepareStatement(SQL_GET_ORDERS_BY_LOGIN);
            ps.setString(1, login);
            rs = ps.executeQuery();
            while (rs.next()) {
                order = new Order(rs.getInt("id_order"),
                        rs.getString("login"),
                        rs.getInt("id_product"),
                        rs.getString("date"),
                        rs.getInt("quantity"),
                        rs.getString("total"));
                orders.add(order);
            }
            return orders;
        } catch (SQLException | ClassNotFoundException e) {
            throw new SourceException(e);
        } finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(ps);
            ConnectionManager.closeConn(cn);
        }
    }

    public static int deleteOrder(int id) throws SourceException {
        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = ConnectionManager.getConnection();
            ps = cn.prepareStatement(SQL_DELETE_ORDER_BY_ID);
            ps.setInt(1, id);

            return ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new SourceException(e);
        } finally {

            ConnectionManager.closeStatement(ps);
            ConnectionManager.closeConn(cn);
        }
    }
}

