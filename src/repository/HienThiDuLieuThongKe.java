/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import javax.swing.JLabel;
import utility.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JLabel;
import utility.DBConnect;

/**
 *
 * @author Admin
 */
public class HienThiDuLieuThongKe {

    private JLabel label;

    public HienThiDuLieuThongKe(JLabel label) {
        this.label = label;
    }

    public void tongHoaDon() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnect.getConnection();
            String sql = "SELECT COUNT(MaHoaDon) AS SoHoaDon\n"
                    + "FROM HoaDon;"; // Truy vấn tùy theo nhu cầu
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                String tongHoaDon = rs.getString("SoHoaDon");
                label.setText(tongHoaDon); // Hiển thị dữ liệu lên JLabel
            } else {
                label.setText("Không có hóa đơn.");
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
    }

    public void tongTien() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnect.getConnection();
            String sql = "SELECT SUM(DoanhThuSanPham) AS TongDoanhThu\n"
                    + "FROM HoaDonChiTiet;"; // Truy vấn tùy theo nhu cầu
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                String tongDoanhThu = rs.getString("TongDoanhThu");
                label.setText(tongDoanhThu); // Hiển thị dữ liệu lên JLabel
            } else {
                label.setText("Không có tiền.");
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
    }
}
