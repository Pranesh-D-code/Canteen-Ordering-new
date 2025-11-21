package com.canteen.dao;

import com.canteen.model.User;
import com.canteen.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    /**
     * Fetch full user object after verifying credentials.
     * Returns null if login fails.
     */
    public User fetchUser(String username, String password) {
        String sql = "SELECT id, username, password FROM users WHERE username = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String storedPassword = rs.getString("password");

                // Compare passwords
                if (storedPassword.equals(password)) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(storedPassword);
                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // login failed
    }


    /**
     * Old validateUser kept for backward compatibility.
     */
    public boolean validateUser(String username, String password) {
        return fetchUser(username, password) != null;
    }


    /**
     * Register new user.
     */
    public boolean registerUser(String username, String password) {
        String check = "SELECT username FROM users WHERE username=?";
        String insert = "INSERT INTO users(username, password) VALUES(?, ?)";

        try (Connection conn = Database.getConnection()) {

            // Check if username exists
            try (PreparedStatement ps1 = conn.prepareStatement(check)) {
                ps1.setString(1, username);
                ResultSet rs = ps1.executeQuery();

                if (rs.next()) {
                    return false; // username already exists
                }
            }

            // Insert new user
            try (PreparedStatement ps2 = conn.prepareStatement(insert)) {
                ps2.setString(1, username);
                ps2.setString(2, password);
                return ps2.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
