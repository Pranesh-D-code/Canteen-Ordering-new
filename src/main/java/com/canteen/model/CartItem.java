package com.canteen.model;

import javafx.beans.property.*;

public class CartItem {

    private final StringProperty name = new SimpleStringProperty();
    private final DoubleProperty price = new SimpleDoubleProperty();
    private final IntegerProperty quantity = new SimpleIntegerProperty();
    private final DoubleProperty total = new SimpleDoubleProperty();

    public CartItem(String name, double price, int qty) {
        this.name.set(name);
        this.price.set(price);
        this.quantity.set(qty);
        this.total.set(price * qty);
    }

    // GETTERS
    public String getName() { return name.get(); }
    public double getPrice() { return price.get(); }
    public int getQuantity() { return quantity.get(); }
    public double getTotal() { return total.get(); }

    // PROPERTIES (required for TableView)
    public StringProperty nameProperty() { return name; }
    public DoubleProperty priceProperty() { return price; }
    public IntegerProperty quantityProperty() { return quantity; }
    public DoubleProperty totalProperty() { return total; }

    // Update Qty
    public void setQuantity(int q) {
        quantity.set(q);
        total.set(q * price.get());
    }
}
