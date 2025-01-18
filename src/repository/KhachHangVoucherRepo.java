/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import utility.DBConnect;

/**
 *
 * @author Admin
 */
public class KhachHangVoucherRepo {

    private Connection conn;

    public KhachHangVoucherRepo(Connection conn) {
        this.conn = conn;
    }

    // Cập nhật voucher cho khách hàng
    public boolean updateKhachHangVoucher(int maKhachHang, int maVoucher) throws SQLException {
        String query = "INSERT INTO KhachHangVoucher (maKhachHang, maVoucher, ngayCap) VALUES (?, ?, ?)";
        try ( PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, maKhachHang);
            stmt.setInt(2, maVoucher);
            stmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            return stmt.executeUpdate() > 0;
        }
    }

    public void updateSoHoaDon(int maKhachHang) {
        try {
            // Cập nhật số lượng hóa đơn đã thanh toán của khách hàng
            Connection connection = DBConnect.getConnection();
            String updateQuery = "UPDATE KhachHang SET SoHoaDonDaThanhToan = SoHoaDonDaThanhToan + 1 WHERE MaKhachHang = ?";
            PreparedStatement stmt = connection.prepareStatement(updateQuery);
            stmt.setInt(1, maKhachHang);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
