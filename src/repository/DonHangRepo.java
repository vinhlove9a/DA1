/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import model.DonHang;
import utility.DBConnect;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class DonHangRepo {

    public ArrayList<DonHang> getAll() {
        ArrayList<DonHang> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT MaDonHang, MaBan, TenKhachHang, NgayGioDat, TongTien, MaSanPham, SoLuong, ThanhTien, PhuongThucThanhToan, TrangThai FROM DonHang;";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int maDonHang = rs.getInt("MaDonHang");
                int maBan = rs.getInt("MaBan");
                String tenKhachHang = rs.getString("TenKhachHang");
                Timestamp ngayGioDat = rs.getTimestamp("NgayGioDat");
                BigDecimal tongTien = rs.getBigDecimal("TongTien");
                int maSanPham = rs.getInt("MaSanPham");
                int soLuong = rs.getInt("SoLuong");
                BigDecimal thanhTien = rs.getBigDecimal("ThanhTien");
                String phuongThucThanhToan = rs.getString("PhuongThucThanhToan");
                String trangThai = rs.getString("TrangThai");
                DonHang donHang = new DonHang(maDonHang, maBan, tenKhachHang, ngayGioDat, tongTien, maSanPham, soLuong, thanhTien, phuongThucThanhToan, trangThai);
                list.add(donHang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public ArrayList<DonHang> donHangCho() {
        ArrayList<DonHang> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = """
                         SELECT MaDonHang, MaBan, TenKhachHang, NgayGioDat, TongTien, TrangThai FROM DonHang 
                         where TrangThai = N'Chưa Thanh Toán'
                         """;
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int maDonHang = rs.getInt("MaDonHang");
                int maBan = rs.getInt("MaBan");
                String tenKhachHang = rs.getString("TenKhachHang");
                Timestamp ngayGioDat = rs.getTimestamp("NgayGioDat");
                BigDecimal tongTien = rs.getBigDecimal("TongTien");
                String trangThai = rs.getString("TrangThai");
                DonHang donHang = new DonHang(maDonHang, maBan, tenKhachHang, ngayGioDat, tongTien, trangThai);
                list.add(donHang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public boolean huyDonHang(int maDonHang) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean isSuccess = false;

        try {
            String sql = "UPDATE DonHang SET TrangThai = N'Hủy' WHERE MaDonHang = ?";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, maDonHang);

            int rowsUpdated = ps.executeUpdate();
            isSuccess = rowsUpdated > 0; // Nếu có hàng được cập nhật, trả về true
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    public ArrayList<DonHang> search(String maKhachHang, String trangThai) {
        ArrayList<DonHang> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Xây dựng câu SQL động để tìm kiếm theo MaKhachHang hoặc TrangThai
            StringBuilder sql = new StringBuilder(
                    "SELECT MaDonHang, MaBan, TenKhachHang, NgayGioDat, TongTien, MaSanPham, SoLuong, ThanhTien, PhuongThucThanhToan, TrangThai "
                    + "FROM DonHang WHERE 1=1"
            );

            // Thêm điều kiện tìm kiếm theo MaKhachHang nếu có
            if (maKhachHang != null && !maKhachHang.trim().isEmpty()) {
                sql.append(" AND TenKhachHang LIKE ?");
            }

            // Thêm điều kiện tìm kiếm theo TrangThai nếu có
            if (trangThai != null && !trangThai.trim().isEmpty()) {
                sql.append(" AND TrangThai = ?");
            }

            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql.toString());

            int paramIndex = 1;

            // Thêm tham số cho MaKhachHang nếu có
            if (maKhachHang != null && !maKhachHang.trim().isEmpty()) {
                ps.setString(paramIndex++, "%" + maKhachHang.trim() + "%");
            }

            // Thêm tham số cho TrangThai nếu có
            if (trangThai != null && !trangThai.trim().isEmpty()) {
                ps.setString(paramIndex++, trangThai.trim());
            }

            rs = ps.executeQuery();

            // Duyệt kết quả trả về và thêm vào danh sách
            while (rs.next()) {
                int maDonHang = rs.getInt("MaDonHang");
                int maBan = rs.getInt("MaBan");
                String tenKhachHang = rs.getString("TenKhachHang");
                Timestamp ngayGioDat = rs.getTimestamp("NgayGioDat");
                BigDecimal tongTien = rs.getBigDecimal("TongTien");
                int maSanPham = rs.getInt("MaSanPham");
                int soLuong = rs.getInt("SoLuong");
                BigDecimal thanhTien = rs.getBigDecimal("ThanhTien");
                String phuongThucThanhToan = rs.getString("PhuongThucThanhToan");
                String trangThaiDonHang = rs.getString("TrangThai");

                DonHang donHang = new DonHang(maDonHang, maBan, tenKhachHang, ngayGioDat, tongTien, maSanPham, soLuong, thanhTien, phuongThucThanhToan, trangThaiDonHang);
                list.add(donHang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    public double getTotalAmountForCustomer(int maKhachHang) {
        double totalAmount = 0;
        try {
            // Truy vấn tổng tiền các đơn hàng đã thanh toán
            Connection connection = DBConnect.getConnection();
            String query = "SELECT SUM(TongTien) FROM DonHang WHERE MaKhachHang = ? AND TrangThai = 'Đã Thanh Toán'";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, maKhachHang);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalAmount = rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalAmount;
    }

}
