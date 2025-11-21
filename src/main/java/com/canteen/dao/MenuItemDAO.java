package com.canteen.dao;

import com.canteen.model.MenuItem;
import com.canteen.util.Database;

import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDAO {

    public List<MenuItem> getAllItems() {
        List<MenuItem> list = new ArrayList<>();
        String sql = "SELECT id, name, price, category, image_path FROM menu_items ORDER BY category, name";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                BigDecimal price = rs.getBigDecimal("price");
                String category = rs.getString("category");
                String imagePath = rs.getString("image_path"); // may be null

                // if imagePath null, fallback to default
                if (imagePath == null || imagePath.trim().isEmpty()) {
                    imagePath = "/images/default_food.png";
                }

                MenuItem item = new MenuItem(id, name, price, category, imagePath);
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
