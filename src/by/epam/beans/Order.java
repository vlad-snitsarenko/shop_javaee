package by.epam.beans;

public class Order {
    private int id;
    private String login;
    private int id_product;
    private String date;
    private int quantity;
    private String total;

    public Order() {
    }

    public Order(String login, int id_product, String date, int quantity, String total) {
        this.login = login;
        this.id_product = id_product;
        this.date = date;
        this.quantity = quantity;
        this.total = total;
    }

    public Order(int id, String login, int id_product, String date, int quantity, String total) {
        this.id = id;
        this.login = login;
        this.id_product = id_product;
        this.date = date;
        this.quantity = quantity;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", id_product=" + id_product +
                ", date='" + date + '\'' +
                ", quantity=" + quantity +
                ", total='" + total + '\'' +
                '}';
    }
}
