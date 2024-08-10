package ProductionCode;

public class Product {
    private String productName;
    private String price;
    private String expDate;

    public Product(String productName, String price, String expDate) {
        this.productName = productName;
        this.price = price;
        this.expDate = expDate;
    }

    public String getProductName() {
        return productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }
}