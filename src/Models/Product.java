package Models;

public class Product {

    private int id;
    private String name;
    private String description;
    private double sellingPrice;
    private Double purchasePrice;
    private int quantity;

    public Product() {
    }


    public Product(String name, String description, double sellingPrice, Double purchasePrice, int quantest) {
        this.name = name;
        this.description = description;
        this.sellingPrice = sellingPrice;
        this.purchasePrice = purchasePrice;
        this.quantity = quantest;
    }

    public Product(int id, String name, String description, double sellingPrice, Double purchasePrice, int quantest) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sellingPrice = sellingPrice;
        this.purchasePrice = purchasePrice;
        this.quantity = quantest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
