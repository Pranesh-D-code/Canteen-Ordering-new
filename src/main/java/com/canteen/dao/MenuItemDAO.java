package com.canteen.dao;

import com.canteen.model.MenuItem;
import com.canteen.util.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDAO {
    public List<MenuItem> findAll() {
        List<MenuItem> list = new ArrayList<>();
        String sql = "SELECT id, name, price FROM menu_items";
        try (Connection conn = Database.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new MenuItem(rs.getInt("id"), rs.getString("name"), rs.getDouble("price")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
