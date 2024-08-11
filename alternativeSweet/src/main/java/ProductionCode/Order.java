package ProductionCode;

public class Order {
    String orderNum;
    String senderName;
    String reciver;
    String productName;
    private String status;

    public Order(String orderNum, String senderName, String reciver, String productName, String status) {
        this.orderNum = orderNum;
        this.senderName = senderName;
        this.reciver = reciver;
        this.productName = productName;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderDetails() {
        return orderNum + ", " + senderName + ", " + reciver + ", " + productName + ", " + status;
    }
}
