import java.util.Formatter;
import java.util.List;


import by.epam.beans.Order;

import by.epam.beans.Product;
import by.epam.beans.User;

class Json {

    static String getProducts(List<Product> products) {
        StringBuilder result;
        if (products != null && !products.isEmpty()) {
            result = new StringBuilder("[\n");

            for (Product product : products) {
                Formatter fm = new Formatter();
                String value = String.valueOf(fm.format("{\"id_product\": %d," +
                                " \"title\": \"%s\", \"description\": \"%s\", \"image\": \"%s\", " +
                                "\"id_category\": %d,\"quantity\": %d,\"brand\": \"%s\"," +
                                "\"screen\": \"%s\",\"os\": \"%s\",\"storage\": \"%s\", \"rating\": %d, \"p_id\": %d, \"price\": \"%.2f\"},\n",
                        product.getId(), product.getTitle(), product.getDescription(),
                        product.getImage(), product.getId_category(), product.getQuantity(), product.getBrand(),
                        product.getScreen(), product.getOs(), product.getStorage(), product.getRate(), product.getP_id(), product.getPrice()));
                result.append(value);
            }
            if (result.length() > 1) {
                result.delete((result.length() - 2), (result.length() - 1));
            }
            result.append("]");
            return result.toString();
        } else return null;

    }

    static String getOrders(List<Order> orders) {
        StringBuilder result = new StringBuilder();
        if (!orders.isEmpty()) {
            result.append("[\n");
            for (Order order : orders) {
                Formatter fm = new Formatter();

                String value = String.valueOf(fm.format("{\"id_order\": %d, \"login\": \"%s\", \"id_product\": %d, \"date\": \"%s\", \"quantity\": %d, \"total\": \"%s\"},\n", order.getId(), order.getLogin(), order.getId_product(), order.getDate(), order.getQuantity(), order.getTotal()));
                result.append(value);
            }
            if (result.length() > 1) {
                result.delete((result.length() - 2), (result.length() - 1));
            }
            result.append("]");

        }
        return result.toString();
    }

    static String getUsers(List<User> users) {
        StringBuilder result = new StringBuilder("[\n");
        for (User user : users) {
            Formatter fm = new Formatter();
            String value = String.valueOf(fm.format("{\"login\": \"%s\", \"password\": \"%s\", \"email\": \"%s\", \"dob\": \"%s\"},\n", user.getLogin(), user.getPassword(), user.getEmail(), user.getDob()));
            result.append(value);
        }
        if (result.length() > 1) {
            result.delete((result.length() - 2), (result.length() - 1));
        }
        result.append("]");
        return result.toString();
    }

    static String getUser(User user) {
        StringBuilder result = new StringBuilder("");

        Formatter fm = new Formatter();
        String value = String.valueOf(fm.format("{\"login\": \"%s\", \"password\": \"%s\", \"email\": \"%s\", \"role\": \"%s\", \"dob\": \"%s\"}\n", user.getLogin(), user.getPassword(), user.getEmail(), user.getRole(), user.getDob()));
        result.append(value);

        return result.toString();
    }

}
