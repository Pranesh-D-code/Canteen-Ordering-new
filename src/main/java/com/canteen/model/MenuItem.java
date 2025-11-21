package com.canteen.model;

import java.math.BigDecimal;

public class MenuItem {
    private int id;
    private String name;
    private BigDecimal price;
    private String category;
    private String imagePath; // e.g. "/images/veg_burger.png"

    public MenuItem(int id, String name, BigDecimal price, String category, String imagePath) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.imagePath = imagePath;
    }

    // getters
    public int getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public String getCategory() { return category; }
    public String getImagePath() { return imagePath; }

    // setters if needed
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}
