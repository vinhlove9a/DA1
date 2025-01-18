/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import javax.swing.*;
import java.io.*;

public class LoadAnhFromDB {

    public static ImageIcon getImageFromDatabase(int maNhanVien) {
        ImageIcon imageIcon = null;
        try {
            // Kết nối đến cơ sở dữ liệu
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=DB_NhaHangThree", "sa", "123");

            // Câu lệnh SQL để lấy ảnh
            String sql = "SELECT Anh FROM NhanVien WHERE MaNhanVien = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, maNhanVien);

            // Thực thi câu lệnh và lấy ảnh
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                byte[] imgData = rs.getBytes("Anh");
                if (imgData != null) {
                    imageIcon = new ImageIcon(imgData);
                }
            }

            // Đóng kết nối
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imageIcon;
    }

    public static ImageIcon getImageFromDatabase2(int maSanPham) {
        ImageIcon imageIcon = null;

        try ( Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=DB_NhaHangThree", "sa", "123")) {

            // Câu lệnh SQL để lấy ảnh từ cơ sở dữ liệu
            String sql = "SELECT Anh FROM SanPham WHERE MaSanPham = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, maSanPham);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Lấy ảnh từ cơ sở dữ liệu
                byte[] imgData = rs.getBytes("Anh");
                if (imgData != null) {
                    imageIcon = new ImageIcon(imgData);
                }
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return imageIcon;
    }

    public static ImageIcon getImageFromDatabaseByName(String tenSanPham) {
        ImageIcon imageIcon = null;
        try ( Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=DB_NhaHangThree", "sa", "123")) {
            String sql = "SELECT Anh FROM SanPham WHERE TenSanPham = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tenSanPham);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                byte[] imgBytes = rs.getBytes("Anh");
                if (imgBytes != null && imgBytes.length > 0) {
                    // Chuyển byte[] thành ImageIcon
                    imageIcon = new ImageIcon(imgBytes);
                    // Debug để kiểm tra
                    System.out.println("Ảnh được lấy thành công cho sản phẩm: " + tenSanPham);
                } else {
                    System.out.println("Không có ảnh cho sản phẩm: " + tenSanPham);
                }
            } else {
                System.out.println("Không tìm thấy sản phẩm: " + tenSanPham);
            }

            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imageIcon;
    }

}
