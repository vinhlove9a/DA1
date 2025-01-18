/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import model.HoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utility.DBConnect;

/**
 *
 * @author Admin
 */
public class HoaDonRepo {

    public ArrayList<HoaDon> getAll() {
        ArrayList<HoaDon> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT MaHoaDon, MaDonHang, MaNhanVien, NgayLap, TongTien, PhuongThucThanhToan FROM HoaDon;";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int maHoaDon = rs.getInt("MaHoaDon");
                int maDonHang = rs.getInt("MaDonHang");
                int maNhanVien = rs.getInt("MaNhanVien");
                Date ngayLap = rs.getDate("NgayLap");
                BigDecimal tongTien = rs.getBigDecimal("TongTien");
                String phuongThucThanhToan = rs.getString("PhuongThucThanhToan");

                HoaDon hoaDon = new HoaDon(maHoaDon, maDonHang, maNhanVien, (java.sql.Date) ngayLap, tongTien, phuongThucThanhToan);
                list.add(hoaDon);
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<HoaDon> searchByMaHoaDon(String maHoaDon) {
        List<HoaDon> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT MaHoaDon, MaDonHang, MaNhanVien, NgayLap, TongTien, PhuongThucThanhToan FROM HoaDon WHERE MaHoaDon = ?";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, maHoaDon);
            rs = ps.executeQuery();
            while (rs.next()) {
                int maHD = rs.getInt("MaHoaDon");
                int maDonHang = rs.getInt("MaDonHang");
                int maNhanVien = rs.getInt("MaNhanVien");
                Date ngayLap = rs.getDate("NgayLap");
                BigDecimal tongTien = rs.getBigDecimal("TongTien");
                String phuongThucThanhToan = rs.getString("PhuongThucThanhToan");
                HoaDon hoaDon = new HoaDon(maHD, maDonHang, maNhanVien, (java.sql.Date) ngayLap, tongTien, phuongThucThanhToan);
                list.add(hoaDon);
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public ArrayList<HoaDon> getHoaDonByDate(Date selectedDate) {
        ArrayList<HoaDon> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // Chuẩn bị SQL tìm kiếm theo ngày
            String sql = "SELECT MaHoaDon, MaDonHang, MaNhanVien, NgayLap, TongTien, PhuongThucThanhToan FROM HoaDon WHERE NgayLap = ?";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(selectedDate.getTime())); // Chuyển đổi Date thành java.sql.Date
            rs = ps.executeQuery();

            while (rs.next()) {
                int maHoaDon = rs.getInt("MaHoaDon");
                int maDonHang = rs.getInt("MaDonHang");
                int maNhanVien = rs.getInt("MaNhanVien");
                Date ngayLap = rs.getDate("NgayLap");
                BigDecimal tongTien = rs.getBigDecimal("TongTien");
                String phuongThucThanhToan = rs.getString("PhuongThucThanhToan");
                HoaDon hoaDon = new HoaDon(maHoaDon, maDonHang, maNhanVien, (java.sql.Date) ngayLap, tongTien, phuongThucThanhToan);
                list.add(hoaDon);
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public Map<String, Double> getDoanhThuTheoThang() {
        Map<String, Double> doanhThuMap = new HashMap<>();
        String query = "SELECT FORMAT(NgayLap, 'yyyy-MM') AS ThangNam, SUM(TongTien) AS TongDoanhThu "
                + "FROM HoaDon GROUP BY FORMAT(NgayLap, 'yyyy-MM') ORDER BY ThangNam";

        try ( Connection conn = DBConnect.getConnection();  PreparedStatement ps = conn.prepareStatement(query);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String thangNam = rs.getString("ThangNam");
                double tongDoanhThu = rs.getDouble("TongDoanhThu");
                doanhThuMap.put(thangNam, tongDoanhThu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doanhThuMap;
    }

    public Map<String, Integer> getSoHoaDonTheoThang() {
        Map<String, Integer> doanhThuData = new HashMap<>();
        String sql = "SELECT YEAR(NgayLap) AS Year, MONTH(NgayLap) AS Month, COUNT(*) AS SoHoaDon "
                + "FROM HoaDon "
                + "GROUP BY YEAR(NgayLap), MONTH(NgayLap) "
                + "ORDER BY Year, Month";

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int year = rs.getInt("Year");
                int month = rs.getInt("Month");
                int soHoaDon = rs.getInt("SoHoaDon");

                // Tạo key là "Tháng Năm"
                String key = String.format("%02d-%d", month, year);
                doanhThuData.put(key, soHoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doanhThuData;
    }

    public Map<String, Double> getTocDoTangTruong() {
        Map<String, Double> doanhThuData = new HashMap<>();
        String sql = "SELECT YEAR(NgayLap) AS Year, MONTH(NgayLap) AS Month, SUM(TongTien) AS DoanhThu "
                + "FROM HoaDon "
                + "GROUP BY YEAR(NgayLap), MONTH(NgayLap) "
                + "ORDER BY Year, Month";

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            double prevRevenue = 0;
            while (rs.next()) {
                int year = rs.getInt("Year");
                int month = rs.getInt("Month");
                double currentRevenue = rs.getDouble("DoanhThu");

                // Tính tỷ lệ tăng trưởng so với tháng trước
                String key = String.format("%02d-%d", month, year);
                if (prevRevenue == 0) {
                    doanhThuData.put(key, 0.0); // Đối với tháng đầu tiên, tỷ lệ tăng trưởng là 0
                } else {
                    double growthRate = ((currentRevenue - prevRevenue) / prevRevenue) * 100;
                    doanhThuData.put(key, growthRate);
                }

                prevRevenue = currentRevenue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doanhThuData;
    }

}
