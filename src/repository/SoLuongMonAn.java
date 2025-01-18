/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JLabel;
import utility.DBConnect;

/**
 *
 * @author Admin
 */
public class SoLuongMonAn {

    private JLabel label;

    public SoLuongMonAn(JLabel label) {
        this.label = label;
    }

    public void SoLuongMon() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnect.getConnection();
            String sql = "SELECT COUNT(MaSanPham) as SoMonAn\n"
                    + "FROM SanPham;"; // Truy vấn tùy theo nhu cầu
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                String tongHoaDon = rs.getString("SoMonAn");
                label.setText(tongHoaDon); // Hiển thị dữ liệu lên JLabel
            } else {
                label.setText("Không có món nào.");
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
