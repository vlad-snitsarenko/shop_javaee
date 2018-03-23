package by.epam.dao;

import by.epam.beans.Product;
import by.epam.beans.User;
import by.epam.db.ConnectionManager;
import by.epam.exceptions.SourceException;
import by.epam.soapclient.SoapClient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsImpl {


    private final static String SQL_DELETE_PRODUCT_BY_ID =
            "DELETE  FROM shop_db.products where id_product = ?";

    private final static String SQL_GET_POPULAR_PRODUCTS =
            "SELECT * FROM (Select * From products Order By rating DESC )as top LIMIT 5;";

    private final static String SQL_BUY_PRODUCT =
            "Update products set products.quantity=quantity-? where id_product=?";

    private final static String SQL_CHANGE_OPTION =
            "Update options set options.value = ? where id_option=1";

    private final static String SQL_GET_LAST_ADDED_PRODUCTS =
            "SELECT * FROM (Select * From products Order By id_product DESC )as top LIMIT 5;";

    private final static String SQL_GET_ALL_PRODUCTS =
            "SELECT * FROM products";

    private final static String SQL_UPDATE_RATING =
            "UPDATE shop_db.products SET products.rating = (products.rating + ?)/2 where products.id_product = ?";

    private final static String SQL_ADD_PRODUCT = "INSERT INTO shop_db.products(title,description,image," +
            "id_category,quantity,brand,screen,os,storage,p_id) VALUES(?, ?, ?, ?, ?,?,?,?,?,?)";


    private final static String SQL_CHECK_OPTION =
            "SELECT value FROM options WHERE shop_db.options.id_option=1";

    private final static String SQL_UPDATE_PRODUCT =
            "Update products set title=?, description=?, image=?, id_category=?," +
                    "quantity=?, brand=?, screen=?, os=?, storage=?, p_id=? where id_product=?";

    public static int updateProduct(Product p) throws SourceException {
        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = ConnectionManager.getConnection();
            ps = cn.prepareStatement(SQL_UPDATE_PRODUCT);
            ps.setString(1, p.getTitle());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getImage());
            ps.setInt(4, p.getId_category());
            ps.setInt(5, p.getQuantity());
            ps.setString(6, p.getBrand());
            ps.setString(7, p.getScreen());
            ps.setString(8, p.getOs());
            ps.setString(9, p.getStorage());
            ps.setInt(10, p.getP_id());
            ps.setInt(11, p.getId());
            return ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new SourceException(e);
        } finally {
            ConnectionManager.closeStatement(ps);
            ConnectionManager.closeConn(cn);
        }
    }

    public static int buyProduct(int id, int quantity) throws SourceException {
        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = ConnectionManager.getConnection();
            ps = cn.prepareStatement(SQL_BUY_PRODUCT);
            ps.setInt(1, quantity);
            ps.setInt(2, id);
            return ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new SourceException(e);
        } finally {
            ConnectionManager.closeStatement(ps);
            ConnectionManager.closeConn(cn);
        }
    }

    public static int changeOption(String value) throws SourceException {
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = ConnectionManager.getConnection();
            ps = cn.prepareStatement(SQL_CHANGE_OPTION);
            ps.setString(1, value);
            return ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new SourceException(e);
        } finally {

            ConnectionManager.closeStatement(ps);
            ConnectionManager.closeConn(cn);
        }
    }

    //method for filterProducts
    private static String getParametersString(String[] arr) {
        StringBuilder result = new StringBuilder("(?");
        for (int i = 1; i < arr.length; i++) {
            result.append(", ?");
        }
        result.append(")");
        return result.toString();
    }

    public static String checkOptionValue() throws SourceException {
        String optionValue = "";
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = ConnectionManager.getConnection();
            ps = cn.prepareStatement(SQL_CHECK_OPTION);
            rs = ps.executeQuery();
            while (rs.next()) {
                optionValue = rs.getString(1);
            }

            return optionValue;
        } catch (SQLException | ClassNotFoundException e) {
            throw new SourceException(e);
        } finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(ps);
            ConnectionManager.closeConn(cn);
        }
    }

    public static List<Product> getFilteredProducts(int id_pr, String[] brand, String[] screen, String[] os, String[] storage) throws SourceException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> products = new ArrayList<Product>();
        Product product = null;
        SoapClient soapClient = new SoapClient();
        try {
            cn = ConnectionManager.getConnection();
            ps = cn.prepareStatement(String.format("SELECT * FROM products WHERE id_category = ? and `brand` in %s and `screen` in %s and os in %s and storage in %s",
                    getParametersString(brand), getParametersString(screen), getParametersString(os), getParametersString(storage)));
            int x = 1;
            ps.setInt(x++, id_pr);
            for (String str : brand) {
                ps.setString(x++, str);
            }
            for (String str : screen) {
                ps.setString(x++, str);
            }
            for (String str : os) {
                ps.setString(x++, str);
            }
            for (String str : storage) {
                ps.setString(x++, str);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                product = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12),
                        soapClient.getPriceByID(rs.getInt(12)));
                products.add(product);
            }
            return products;
        } catch (SQLException | ClassNotFoundException e) {
            throw new SourceException(e);
        } finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(ps);
            ConnectionManager.closeConn(cn);
        }
    }

    public static List<Product> getAllProducts() throws SourceException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> productsList = new ArrayList<Product>();
        Product product = null;
        SoapClient soapClient = new SoapClient();
        try {
            cn = ConnectionManager.getConnection();
            ps = cn.prepareStatement(SQL_GET_ALL_PRODUCTS);
            rs = ps.executeQuery();
            while (rs.next()) {
                product = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12),
                        soapClient.getPriceByID(rs.getInt(12)));

                productsList.add(product);
            }
            return productsList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new SourceException(e);
        } finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(ps);
            ConnectionManager.closeConn(cn);
        }
    }

    public static List<Product> getPopularProducts() throws SourceException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> productsList = new ArrayList<Product>();
        Product product = null;
        SoapClient soapClient = new SoapClient();
        try {
            cn = ConnectionManager.getConnection();
            ps = cn.prepareStatement(SQL_GET_POPULAR_PRODUCTS);
            rs = ps.executeQuery();
            while (rs.next()) {
                product = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12),
                        soapClient.getPriceByID(rs.getInt(12)));

                productsList.add(product);
            }
            return productsList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new SourceException(e);
        } finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(ps);
            ConnectionManager.closeConn(cn);
        }
    }

    public static List<Product> getLastAddedProducts() throws SourceException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> productsList = new ArrayList<Product>();
        Product product = null;
        SoapClient soapClient = new SoapClient();
        try {
            cn = ConnectionManager.getConnection();
            ps = cn.prepareStatement(SQL_GET_LAST_ADDED_PRODUCTS);
            rs = ps.executeQuery();
            while (rs.next()) {
                product = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12),
                        soapClient.getPriceByID(rs.getInt(12)));

                productsList.add(product);
            }
            return productsList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new SourceException(e);
        } finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(ps);
            ConnectionManager.closeConn(cn);
        }
    }

    public static int addProduct(Product product) throws SourceException {
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = ConnectionManager.getConnection();
            ps = cn.prepareStatement(SQL_ADD_PRODUCT);
            ps.setString(1, product.getTitle());
            ps.setString(2, product.getDescription());
            ps.setString(3, product.getImage());
            ps.setInt(4, product.getId_category());
            ps.setInt(5, product.getQuantity());
            ps.setString(6, product.getBrand());
            ps.setString(7, product.getScreen());
            ps.setString(8, product.getOs());
            ps.setString(9, product.getStorage());
            ps.setInt(10, product.getP_id());
            return ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new SourceException(e);
        } finally {

            ConnectionManager.closeStatement(ps);
            ConnectionManager.closeConn(cn);
        }
    }

    public static int updateRating(int id, double rating) throws SourceException {
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = ConnectionManager.getConnection();
            ps = cn.prepareStatement(SQL_UPDATE_RATING);
            ps.setInt(2, id);
            ps.setDouble(1, rating);

            return ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new SourceException(e);
        } finally {

            ConnectionManager.closeStatement(ps);
            ConnectionManager.closeConn(cn);
        }
    }

    public static int deleteProduct(int id) throws SourceException {
        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = ConnectionManager.getConnection();
            ps = cn.prepareStatement(SQL_DELETE_PRODUCT_BY_ID);
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


