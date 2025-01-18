/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JLabel;
import utility.DBConnect;

/**
 *
 * @author Admin
 */
public class MonAnYeuThichNhat {

    private void hienThiTenSanPham() {
        // Giả sử bạn đã có kết nối cơ sở dữ liệu và câu lệnh SQL
        String query = "SELECT sp.TenSanPham, SUM(hdct.SoLuong) AS TongSoLuong "
                + "FROM HoaDonChiTiet hdct "
                + "JOIN SanPham sp ON hdct.MaSanPham = sp.MaSanPham "
                + "GROUP BY sp.TenSanPham "
                + "ORDER BY TongSoLuong DESC LIMIT 1"; // Lấy sản phẩm bán nhiều nhất

        try ( Connection con = DBConnect.getConnection();  PreparedStatement pst = con.prepareStatement(query);  ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                // Lấy tên sản phẩm từ ResultSet
                String tenSanPham = rs.getString("TenSanPham");

                // Cập nhật tên sản phẩm lên JLabel
//                lblSanPham.setText("Sản phẩm bán nhiều nhất: " + tenSanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
