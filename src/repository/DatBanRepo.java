/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.DatBan;
import utility.DBConnect;

public class DatBanRepo {

    public ArrayList<DatBan> getAll() {
        ArrayList<DatBan> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Truy vấn SQL để lấy dữ liệu từ nhiều bảng
            String sql = "SELECT * FROM DatBan";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int maDatBan = rs.getInt("MaDatBan");
                String tenKhachHang = rs.getString("TenKhachHang");
                int maBan = rs.getInt("MaBan");
                Timestamp ngayGioDat = rs.getTimestamp("NgayGioDat");
                String thongTinLH = rs.getString("ThongTinLienHe");
                String tinhTrang = rs.getString("TinhTrang");

                // Đúng tham số truyền vào constructor DatBan
                DatBan db = new DatBan(maDatBan, maDatBan, tenKhachHang, tinhTrang, maBan, ngayGioDat, thongTinLH, tinhTrang);
                list.add(db);
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

    public int addDatBan(DatBan db) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            // Cập nhật câu lệnh SQL để thêm tên khách hàng và thời gian đặt
            String sql = "INSERT INTO DatBan(MaDatBan, TenKhachHang, MaBan, ThongTinLienHe, TinhTrang, NgayGioDat) VALUES (?, ?, ?, ?, ?, ?);";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            // Thiết lập các giá trị vào PreparedStatement
            ps.setInt(1, db.getMaDatBan());               // Mã đặt bàn           // Mã khách hàng
            ps.setString(2, db.getTenKhachHang());        // Tên khách hàng (thêm vào)
            ps.setInt(3, db.getMaBan());                   // Mã bàn
            ps.setString(4, db.getThongTinLienHe());      // Thông tin liên hệ
            ps.setString(5, db.getTinhTrang());           // Tình trạng
            ps.setTimestamp(6, db.getNgayGioDat());       // Thời gian đặt (Ngày giờ đặt)

            return ps.executeUpdate();  // Thực hiện thêm vào cơ sở dữ liệu
        } catch (Exception e) {
            e.printStackTrace();
            return 0;  // Trả về 0 nếu có lỗi xảy ra
        } finally {
            try {
                if (ps != null) {
                    ps.close();  // Đóng PreparedStatement
                }
                if (con != null) {
                    con.close();  // Đóng kết nối cơ sở dữ liệu
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int updateDatBan(int maDatBan, DatBan db) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            String sql = "UPDATE DatBan SET  MaBan = ?, TenKhachHang = ?, ThongTinLienHe = ?, TinhTrang = ? WHERE MaDatBan = ?;";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);     // Parameter 1
            ps.setInt(1, db.getMaBan());             // Parameter 2
            ps.setString(2, db.getTenKhachHang());   // Parameter 3
            ps.setString(3, db.getThongTinLienHe()); // Parameter 4
            ps.setString(4, db.getTinhTrang());      // Parameter 5
            ps.setInt(5, maDatBan);                  // Parameter 6 (for WHERE condition)
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
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
    }

    public int deleteDatBan(int maDatBan) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            String sql = "DELETE FROM DatBan WHERE MaDatBan = ?;";
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, maDatBan);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
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
    }

    public List<Integer> getBookedTables() {
        List<Integer> bookedTables = new ArrayList<>();
        String query = "SELECT DISTINCT maBan FROM DatBan WHERE tinhTrang = 'Đã đặt'";

        try ( Connection conn = DBConnect.getConnection();  PreparedStatement ps = conn.prepareStatement(query);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                bookedTables.add(rs.getInt("maBan"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookedTables;
    }

    public List<Integer> getReservedTables() {
        List<Integer> reservedTables = new ArrayList<>();
        String sql = "SELECT MaBan FROM DatBan WHERE TinhTrang = 'Đã đặt'";

        try ( Connection conn = DBConnect.getConnection();  PreparedStatement stmt = conn.prepareStatement(sql);  ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int maBan = rs.getInt("MaBan");
                reservedTables.add(maBan); // Thêm mã bàn vào danh sách
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservedTables; // Trả về danh sách các bàn đã được đặt
    }

}
