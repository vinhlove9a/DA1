/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import model.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utility.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.SanPham;
import utility.DBConnect;

public class SanPhamRepo {

    public ArrayList<SanPham> getAll() {
        ArrayList<SanPham> list = new ArrayList<>();
        try {
            String sql = "SELECT MaSanPham, TenSanPham, Loai, DonGia, SoLuongTon, MoTa, Anh FROM SanPham";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maSanPham = rs.getInt("MaSanPham");
                String tenSanPham = rs.getString("TenSanPham");
                String danhMuc = rs.getString("Loai");
                double giaBan = rs.getDouble("DonGia");
                int soLuongTon = rs.getInt("SoLuongTon");
                String moTa = rs.getString("MoTa");
                byte[] anh = rs.getBytes("Anh");  // Lấy ảnh từ cơ sở dữ liệu
                SanPham sanPham = new SanPham(maSanPham, tenSanPham, danhMuc, giaBan, soLuongTon, moTa, anh);
                list.add(sanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Phương thức thêm sản phẩm mới
    public int addSanPham(SanPham sp) {
        try {
            String sql = "INSERT INTO SanPham (MaSanPham, TenSanPham, Loai, DonGia, SoLuongTon, MoTa, Anh) VALUES (?, ?, ?, ?, ?, ?, ?)";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, sp.getMaSanPham());
            ps.setString(2, sp.getTenSanPham());
            ps.setString(3, sp.getDanhMuc());
            ps.setDouble(4, sp.getGiaBan());
            ps.setInt(5, sp.getSoLuongTon());
            ps.setString(6, sp.getMoTa());
            ps.setBytes(7, sp.getAnh());  // Lưu ảnh vào cơ sở dữ liệu
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Phương thức cập nhật thông tin sản phẩm
    public int updateSanPham(SanPham sp) {
        try {
            String sql = "UPDATE SanPham SET TenSanPham = ?, Loai = ?, DonGia = ?, SoLuongTon = ?, MoTa = ?, Anh = ? WHERE MaSanPham = ?";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sp.getTenSanPham());
            ps.setString(2, sp.getDanhMuc());
            ps.setDouble(3, sp.getGiaBan());
            ps.setInt(4, sp.getSoLuongTon());
            ps.setString(5, sp.getMoTa());
            ps.setBytes(6, sp.getAnh());  // Cập nhật ảnh
            ps.setInt(7, sp.getMaSanPham());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Phương thức xóa sản phẩm
    public int deleteSanPham(int maSanPham) {
        try {
            String sql = "DELETE FROM SanPham WHERE MaSanPham = ?";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, maSanPham);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Phương thức tìm sản phẩm theo tên và loại
    public ArrayList<SanPham> getAll(String tenten, String loailoai) {
        ArrayList<SanPham> list = new ArrayList<>();
        try {
            String sql = "SELECT MaSanPham, TenSanPham, Loai, DonGia, SoLuongTon, MoTa, Anh FROM SanPham WHERE 1=1";
            if (tenten != null && !tenten.trim().isEmpty()) {
                sql += " AND TenSanPham LIKE ?";
            }
            if (loailoai != null && !loailoai.trim().isEmpty()) {
                sql += " AND Loai = ?";
            }
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            int index = 1;
            if (tenten != null && !tenten.trim().isEmpty()) {
                ps.setString(index++, "%" + tenten.trim() + "%");
            }
            if (loailoai != null && !loailoai.trim().isEmpty()) {
                ps.setString(index++, loailoai);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maSanPham = rs.getInt("MaSanPham");
                String tenSanPham = rs.getString("TenSanPham");
                String danhMuc = rs.getString("Loai");
                double giaBan = rs.getDouble("DonGia");
                int soLuongTon = rs.getInt("SoLuongTon");
                String moTa = rs.getString("MoTa");
                byte[] anh = rs.getBytes("Anh");
                SanPham sanPham = new SanPham(maSanPham, tenSanPham, danhMuc, giaBan, soLuongTon, moTa, anh);
                list.add(sanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
