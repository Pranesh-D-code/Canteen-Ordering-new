package com.canteen.model;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private int userId;
    private String items;
    private double totalPrice;
    private LocalDateTime orderDate;
    private String qrPath;
    // getters + setters
    public int getId(){return id;} public void setId(int id){this.id=id;}
    public int getUserId(){return userId;} public void setUserId(int userId){this.userId=userId;}
    public String getItems(){return items;} public void setItems(String items){this.items=items;}
    public double getTotalPrice(){return totalPrice;} public void setTotalPrice(double totalPrice){this.totalPrice=totalPrice;}
    public LocalDateTime getOrderDate(){return orderDate;} public void setOrderDate(LocalDateTime orderDate){this.orderDate=orderDate;}
    public String getQrPath(){return qrPath;} public void setQrPath(String qrPath){this.qrPath=qrPath;}
}
