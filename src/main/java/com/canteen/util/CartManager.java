package com.canteen.util;

import com.canteen.model.CartItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CartManager {

    private static final ObservableList<CartItem> CART = FXCollections.observableArrayList();

    // Add item to cart
    public static void addItem(String name, double price, int qty) {

        // If item exists, update qty
        for (CartItem ci : CART) {
            if (ci.getName().equals(name)) {
                ci.setQuantity(ci.getQuantity() + qty);
                return;
            }
        }

        // Otherwise add new
        CART.add(new CartItem(name, price, qty));
    }

    // Get all items
    public static ObservableList<CartItem> getItems() {
        return CART;
    }

    // Remove specific item
    public static void removeItem(CartItem item) {
        CART.remove(item);
    }

    // Clear cart
    public static void clear() {
        CART.clear();
    }

    // Grand total
    public static double getGrandTotal() {
        double total = 0;
        for (CartItem ci : CART) {
            total += ci.getTotal();
        }
        return total;
    }

    // Summary for QR or bill
    public static String generateSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order Summary:\n");

        for (CartItem ci : CART) {
            sb.append(ci.getName())
                    .append(" × ")
                    .append(ci.getQuantity())
                    .append(" = ₹")
                    .append(ci.getTotal())
                    .append("\n");
        }

        sb.append("Grand Total: ₹").append(getGrandTotal());
        return sb.toString();
    }
}
