package com.canteen.dao;

import com.canteen.model.Order;
import com.canteen.util.Database;

import java.sql.*;

public class OrderDAO {
    public int save(Order order) {
        String sql = "INSERT INTO orders (user_id, items, total_price, qr_path) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, order.getUserId());
            ps.setString(2, order.getItems());
            ps.setDouble(3, order.getTotalPrice());
            ps.setString(4, order.getQrPath());
            int affected = ps.executeUpdate();
            if (affected == 0) throw new SQLException("Creating order failed.");
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int id = keys.getInt(1);
                    order.setId(id);
                    return id;
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return -1;
    }

    public void updateQrPath(int orderId, String qrPath) {
        String sql = "UPDATE orders SET qr_path=? WHERE id=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, qrPath);
            ps.setInt(2, orderId);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
