/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import utility.DBConnect;

/**
 *
 * @author Admin
 */
public class VoucherRepo {

    public static double getVoucherDiscount(int maKhachHang) throws SQLException {
        Connection con = DBConnect.getConnection();
        try {
            String sql = "SELECT v.MucGiamGia "
                    + "FROM Voucher v "
                    + "JOIN KhachHangVoucher kv ON v.MaVoucher = kv.MaVoucher "
                    + "WHERE kv.MaKhachHang = ? "
                    + "AND v.TrangThai = 'Đang áp dụng' "
                    + "AND v.NgayHetHan >= GETDATE()";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, maKhachHang);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("MucGiamGia");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;

    }

    public static void applyVouchers() throws SQLException {
        Connection con = DBConnect.getConnection();
        try {
            String sql = "UPDATE DonHang "
                    + "SET MaVoucher = CASE "
                    + "    WHEN SoDonHangDaThanhToan > 25 THEN 5 "
                    + "    WHEN SoDonHangDaThanhToan > 20 THEN 4 "
                    + "    WHEN SoDonHangDaThanhToan > 15 THEN 3 "
                    + "    WHEN SoDonHangDaThanhToan > 10 THEN 2 "
                    + "    WHEN SoDonHangDaThanhToan > 5 THEN 1 "
                    + "    ELSE NULL "
                    + "END "
                    + "FROM ( "
                    + "    SELECT MaKhachHang, COUNT(*) AS SoDonHangDaThanhToan "
                    + "    FROM DonHang "
                    + "    WHERE TrangThai = 'Đã thanh toán' "
                    + "    GROUP BY MaKhachHang "
                    + ") AS ThongKe "
                    + "WHERE DonHang.MaKhachHang = ThongKe.MaKhachHang "
                    + "AND DonHang.TrangThai = 'Đang xử lý'";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Phương thức kiểm tra và cấp voucher cho tất cả khách hàng
    public static void refreshVouchersForAllCustomers() throws SQLException {
        Connection con = DBConnect.getConnection();
        try {
            // Lấy danh sách tất cả khách hàng
            String sql = "SELECT MaKhachHang FROM KhachHang";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int maKhachHang = rs.getInt("MaKhachHang");
                applyVoucherForCustomer(maKhachHang); // Kiểm tra và cấp voucher cho từng khách hàng
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Phương thức kiểm tra và cấp voucher cho một khách hàng cụ thể
    public static void applyVoucherForCustomer(int maKhachHang) throws SQLException {
        Connection con = DBConnect.getConnection();
        try {
            // Đếm số đơn hàng đã thanh toán cho khách hàng
            String sql = "SELECT COUNT(*) AS SoDonHangDaThanhToan "
                    + "FROM DonHang "
                    + "WHERE MaKhachHang = ? AND TrangThai = 'Đã thanh toán'";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, maKhachHang);
            ResultSet rs = ps.executeQuery();

            int soDonHangDaThanhToan = 0;
            if (rs.next()) {
                soDonHangDaThanhToan = rs.getInt("SoDonHangDaThanhToan");
            }

            // Kiểm tra và cấp voucher dựa trên số đơn hàng đã thanh toán
            int maVoucher = 0;
            if (soDonHangDaThanhToan > 25) {
                maVoucher = 5;
            } else if (soDonHangDaThanhToan > 20) {
                maVoucher = 4;
            } else if (soDonHangDaThanhToan > 15) {
                maVoucher = 3;
            } else if (soDonHangDaThanhToan > 10) {
                maVoucher = 2;
            } else if (soDonHangDaThanhToan > 5) {
                maVoucher = 1;
            }

            if (maVoucher > 0) {
                // Kiểm tra xem khách hàng đã có voucher này chưa
                sql = "SELECT COUNT(*) AS SoLuong "
                        + "FROM KhachHangVoucher "
                        + "WHERE MaKhachHang = ? AND MaVoucher = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, maKhachHang);
                ps.setInt(2, maVoucher);
                rs = ps.executeQuery();

                int soLuong = 0;
                if (rs.next()) {
                    soLuong = rs.getInt("SoLuong");
                }

                if (soLuong == 0) {
                    // Cấp voucher cho khách hàng
                    sql = "INSERT INTO KhachHangVoucher (MaKhachHang, SoHoaDon, MaVoucher, NgayCap) "
                            + "VALUES (?, ?, ?, GETDATE())";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, maKhachHang);
                    ps.setInt(2, soDonHangDaThanhToan);
                    ps.setInt(3, maVoucher);
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
