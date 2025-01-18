/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import java.util.List;
import utility.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class TimKiemBanHangTheoLoai {

    public List<String> getAllProductCategories() {
    List<String> categories = new ArrayList<>();
    String query = "SELECT DISTINCT Loai FROM SanPham"; // Truy vấn lấy tất cả loại sản phẩm
    try (Connection conn = DBConnect.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        while (rs.next()) {
            categories.add(rs.getString("Loai").toLowerCase()); // Thêm loại vào danh sách
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return categories;
}
    public String getCategoryByProductName(String productName) {
        String category = "";
        String query = "SELECT Loai FROM SanPham WHERE TenSanPham = ?";
        try ( Connection conn = DBConnect.getConnection();  PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, productName);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    category = rs.getString("Loai").toLowerCase(); // Lấy loại sản phẩm
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }
}
