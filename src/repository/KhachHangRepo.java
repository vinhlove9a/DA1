/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

/**
 *
 * @author Admin
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.KhachHang;
import utility.DBConnect;

public class KhachHangRepo {

    // Phương thức lấy danh sách khách hàng
    public List<KhachHang> getAllKhachHang() {
        List<KhachHang> khachHangs = new ArrayList<>();
        String query = "SELECT * FROM KhachHang";

        try ( Connection conn = DBConnect.getConnection();  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int maKhachHang = rs.getInt("MaKhachHang");
                String tenKhachHang = rs.getString("TenKhachHang");
                String soDienThoai = rs.getString("SoDienThoai");
                boolean gioiTinh = rs.getBoolean("GioiTinh");

                khachHangs.add(new KhachHang(maKhachHang, tenKhachHang, soDienThoai, gioiTinh));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return khachHangs;
    }

    public List<KhachHang> searchKhachHangByName(String tenKhachHang) {
        List<KhachHang> khachHangs = new ArrayList<>();
        String query = "SELECT * FROM KhachHang WHERE TenKhachHang LIKE ?";

        try ( Connection conn = DBConnect.getConnection();  PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + tenKhachHang + "%");
            try ( ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int maKhachHang = rs.getInt("MaKhachHang");
                    String ten = rs.getString("TenKhachHang");
                    String soDienThoai = rs.getString("SoDienThoai");
                    boolean gioiTinh = rs.getBoolean("GioiTinh");

                    khachHangs.add(new KhachHang(maKhachHang, ten, soDienThoai, gioiTinh));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return khachHangs;
    }

    public boolean addKhachHang(KhachHang kh) {
        String query = "INSERT INTO KhachHang (TenKhachHang, SoDienThoai, GioiTinh) VALUES (?, ?, ?)";
        try ( Connection conn = DBConnect.getConnection();  PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, kh.getTenKhachHang());
            pstmt.setString(2, kh.getSoDienThoai());
            pstmt.setBoolean(3, kh.isGioiTinh());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu thêm thành công
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteKhachHang(int maKhachHang) {
        String query = "DELETE FROM KhachHang WHERE MaKhachHang = ?";
        try ( Connection conn = DBConnect.getConnection();  PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, maKhachHang);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateKhachHang(KhachHang kh) {
        String query = "UPDATE KhachHang SET TenKhachHang = ?, SoDienThoai = ?, GioiTinh = ? WHERE MaKhachHang = ?";
        try ( Connection conn = DBConnect.getConnection();  PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, kh.getTenKhachHang());
            pstmt.setString(2, kh.getSoDienThoai());
            pstmt.setBoolean(3, kh.isGioiTinh());
            pstmt.setInt(4, kh.getMaKhachHang());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
