package by.epam.beans;


public class Product {
    private int id;
    private String title;
    private String description;
    private String image;
    private int id_category;
    private int quantity;
    private String brand;
    private String screen;
    private String os;
    private String storage;
    private int rate;
    private int p_id;
    private double price;

    public Product() {
    }

    public Product(String title, String description, String image, int id_category, int quantity, String brand, String screen, String os, String storage) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.id_category = id_category;
        this.quantity = quantity;
        this.brand = brand;
        this.screen = screen;
        this.os = os;
        this.storage = storage;
    }

    public Product(int id, String title, String description, String image, int id_category, int quantity, String brand, String screen, String os, String storage, int rate, int p_id, double price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.id_category = id_category;
        this.quantity = quantity;
        this.brand = brand;
        this.screen = screen;
        this.os = os;
        this.storage = storage;
        this.rate = rate;
        this.p_id = p_id;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", id_category=" + id_category +
                ", quantity=" + quantity +
                ", brand='" + brand + '\'' +
                ", screen='" + screen + '\'' +
                ", os='" + os + '\'' +
                ", storage='" + storage + '\'' +
                ", p_id=" + p_id +
                ", p_id=" + price + '}';
    }
}
