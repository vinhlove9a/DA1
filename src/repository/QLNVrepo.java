package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.NhanVien;
import utility.DBConnect;

public class QLNVrepo {

    // Fetch all employees
    public ArrayList<NhanVien> getAll() {
        ArrayList<NhanVien> list_NV = new ArrayList<>();
        String sql = "SELECT MaNhanVien, MaNguoiDung, TenNhanVien, GioiTinh, NgaySinh, SoDienThoai, DiaChi, Anh FROM NhanVien;";

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int maNhanVien = rs.getInt("MaNhanVien");
                int maNguoiDung = rs.getInt("MaNguoiDung");
                String tenNhanVien = rs.getString("TenNhanVien");
                boolean gioiTinh = rs.getBoolean("GioiTinh");
                Date ngaySinh = rs.getDate("NgaySinh");
                String soDienThoai = rs.getString("SoDienThoai");
                String diaChi = rs.getString("DiaChi");
                byte[] anh = rs.getBytes("Anh");  // Read the byte array for the employee's photo

                NhanVien nv = new NhanVien(maNhanVien, maNguoiDung, tenNhanVien, gioiTinh, ngaySinh, soDienThoai, diaChi, anh);
                list_NV.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_NV;
    }

    // Add a new employee
    public int addNV(NhanVien NV) {
        String sql = "INSERT INTO NhanVien (MaNhanVien, MaNguoiDung, TenNhanVien, GioiTinh, NgaySinh, SoDienThoai, DiaChi, Anh) VALUES (?,?,?,?,?,?,?,?);";

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, NV.getMaNhanVien());
            ps.setInt(2, NV.getMaNguoiDung());
            ps.setString(3, NV.getTenNhanVien());
            ps.setBoolean(4, NV.isGioiTinh());
            ps.setDate(5, NV.getNgaySinh());
            ps.setString(6, NV.getSoDienThoai());
            ps.setString(7, NV.getDiaChi());
            ps.setBytes(8, NV.getAnh());  // Set the photo byte array

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Update employee information
    public int updateNV(int maNhanVien, NhanVien NV) {
        String sql = "UPDATE NhanVien SET MaNguoiDung = ?, TenNhanVien = ?, GioiTinh = ?, NgaySinh = ?, SoDienThoai = ?, DiaChi = ?, Anh = ? WHERE MaNhanVien = ?;";

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, NV.getMaNguoiDung());
            ps.setString(2, NV.getTenNhanVien());
            ps.setBoolean(3, NV.isGioiTinh());
            ps.setDate(4, NV.getNgaySinh());
            ps.setString(5, NV.getSoDienThoai());
            ps.setString(6, NV.getDiaChi());
            ps.setBytes(7, NV.getAnh());  // Set the updated photo byte array
            ps.setInt(8, maNhanVien);

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Delete an employee
    public int deleteNV(int maNhanVien) {
        String sql = "DELETE FROM NhanVien WHERE MaNhanVien = ?;";

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maNhanVien);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Search for an employee by name
    public ArrayList<NhanVien> timNV(String tenNhanVien) {
        ArrayList<NhanVien> list_NV = new ArrayList<>();
        String sql = "SELECT MaNhanVien, MaNguoiDung, TenNhanVien, GioiTinh, NgaySinh, SoDienThoai, DiaChi, Anh FROM NhanVien WHERE TenNhanVien LIKE ?;";

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + tenNhanVien + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int maNhanVien = rs.getInt("MaNhanVien");
                int maNguoiDung = rs.getInt("MaNguoiDung");
                String tenNhanVienResult = rs.getString("TenNhanVien");
                boolean gioiTinh = rs.getBoolean("GioiTinh");
                Date ngaySinh = rs.getDate("NgaySinh");
                String soDienThoai = rs.getString("SoDienThoai");
                String diaChi = rs.getString("DiaChi");
                byte[] anh = rs.getBytes("Anh");  // Read the byte array for the employee's photo

                NhanVien nv = new NhanVien(maNhanVien, maNguoiDung, tenNhanVienResult, gioiTinh, ngaySinh, soDienThoai, diaChi, anh);
                list_NV.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_NV;
    }

    public NhanVien getNhanVienById(int maNhanVien) {
        String sql = "SELECT * FROM NhanVien WHERE MaNhanVien = ?";
        try ( Connection conn = DBConnect.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maNhanVien);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Lấy dữ liệu từ ResultSet
                int maNguoiDung = rs.getInt("MaNguoiDung");
                String tenNhanVien = rs.getString("TenNhanVien");
                boolean gioiTinh = rs.getBoolean("GioiTinh");
                Date ngaySinh = rs.getDate("NgaySinh");
                String soDienThoai = rs.getString("SoDienThoai");
                String diaChi = rs.getString("DiaChi");
                byte[] anh = rs.getBytes("Anh"); // Ảnh lưu dạng byte[]

                // Tạo và trả về đối tượng NhanVien
                return new NhanVien(maNhanVien, maNguoiDung, tenNhanVien, gioiTinh, ngaySinh, soDienThoai, diaChi, anh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Không tìm thấy
    }

}
