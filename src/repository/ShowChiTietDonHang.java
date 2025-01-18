/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utility.DBConnect;

/**
 *
 * @author Admin
 */
public class ShowChiTietDonHang {

    public void loadChiTietDonHang(String maDonHang) {
        // Chuẩn bị danh sách để hiển thị lên JTable
        List<Object[]> data = new ArrayList<>();

        String query = " SELECT sp.MaSanPham, sp.TenSanPham, ctdh.SoLuong, sp.DonGia, \n"
                + "        (ctdh.SoLuong * sp.DonGia) AS ThanhTien \n"
                + " FROM DonHangChiTiet ctdh \n"
                + " INNER JOIN SanPham sp ON ctdh.MaSanPham = sp.MaSanPham \n"
                + " WHERE ctdh.MaDonHang = ?";

        try ( Connection conn = DBConnect.getConnection(); // Kết nối đến DB
                  PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, maDonHang); // Gán Mã Đơn Hàng vào câu lệnh truy vấn

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Lấy từng dòng dữ liệu từ ResultSet
                    Object[] row = {
                        rs.getString("MaSanPham"), // Mã sản phẩm
                        rs.getString("TenSanPham"), // Tên sản phẩm
                        rs.getInt("SoLuong"), // Số lượng
                        rs.getDouble("DonGia"), // Đơn giá
                        rs.getDouble("ThanhTien") // Thành tiền
                    };
                    data.add(row); // Thêm vào danh sách
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public void loadChiTietDonHang2() {
        // Chuẩn bị danh sách để hiển thị lên JTable
        List<Object[]> data = new ArrayList<>();

        String query = " SELECT sp.MaSanPham, sp.TenSanPham, ctdh.SoLuong, sp.DonGia, \n"
                + "        (ctdh.SoLuong * sp.DonGia) AS ThanhTien \n"
                + " FROM DonHangChiTiet ctdh \n"
                + " INNER JOIN SanPham sp ON ctdh.MaSanPham = sp.MaSanPham \n";

        try ( Connection conn = DBConnect.getConnection(); // Kết nối đến DB
                  PreparedStatement ps = conn.prepareStatement(query)) {

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Lấy từng dòng dữ liệu từ ResultSet
                    Object[] row = {
                        rs.getString("MaSanPham"), // Mã sản phẩm
                        rs.getString("TenSanPham"), // Tên sản phẩm
                        rs.getInt("SoLuong"), // Số lượng
                        rs.getDouble("DonGia"), // Đơn giá
                        rs.getDouble("ThanhTien") // Thành tiền
                    };
                    data.add(row); // Thêm vào danh sách
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

}
